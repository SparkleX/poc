/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.next.odata4.processor;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.ContextURL.Suffix;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityProcessor;
import org.apache.olingo.server.api.serializer.EntitySerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriParameter;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.queryoption.ExpandOption;
import org.apache.olingo.server.api.uri.queryoption.SelectOption;

import com.next.odata4.jpa.data.ODataReader;
import com.next.odata4.jpa.model.ODataMetadata;

public class EntityProcessorImpl implements EntityProcessor/* , MediaEntityProcessor */ {

	private OData odata;
	private ServiceMetadata serviceMetadata;
	private EntityManager em;
	private ODataMetadata odataMetadata;
	private EntityManagerFactory emf;

	public EntityProcessorImpl(ODataMetadata odataMetadata, EntityManagerFactory emf, EntityManager em)
	{
		this.odataMetadata = odataMetadata;
		this.emf = emf;
		this.em = em;
	}
	public void init(OData odata, ServiceMetadata serviceMetadata) {
		this.odata = odata;
		this.serviceMetadata = serviceMetadata;
	}

	@Override
	public void readEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType responseFormat)
			throws ODataApplicationException, ODataLibraryException {
	    UriResource uriResource = uriInfo.getUriResourceParts().get(0);

	    if (uriResource instanceof UriResourceEntitySet) {
	      readEntityInternal(request, response, uriInfo, responseFormat);
	    } else {
	      throw new ODataApplicationException("Only EntitySet is supported",
	          HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ENGLISH);
	    }
	}
	  private void readEntityInternal(ODataRequest request, ODataResponse response, UriInfo uriInfo,
		      ContentType responseFormat)
		          throws ODataApplicationException, SerializerException {

		    Entity responseEntity = null; // required for serialization of the response body
		    EdmEntitySet responseEdmEntitySet = null; // we need this for building the contextUrl

		    // 1st step: retrieve the requested Entity: can be "normal" read operation, or navigation (to-one)
		    List<UriResource> resourceParts = uriInfo.getUriResourceParts();
		    int segmentCount = resourceParts.size();

		    UriResource uriResource = resourceParts.get(0); // in our example, the first segment is the EntitySet
		    if (!(uriResource instanceof UriResourceEntitySet)) {
		      throw new ODataApplicationException("Only EntitySet is supported",
		          HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ENGLISH);
		    }

		    UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) uriResource;
		    EdmEntitySet startEdmEntitySet = uriResourceEntitySet.getEntitySet();

		    // Analyze the URI segments
		    if (segmentCount == 1) { // no navigation
		      responseEdmEntitySet = startEdmEntitySet; // since we have only one segment
		      Class<?> javaType = odataMetadata.getEntitySet(startEdmEntitySet.getName()).getJavaType();
		      // 2. step: retrieve the data from backend
		      List<UriParameter> keyPredicates = uriResourceEntitySet.getKeyPredicates();
		      String key = keyPredicates.get(0).getText();
		      
		      ODataReader oDataReader = new ODataReader();
		      responseEntity = oDataReader.read(emf, em, javaType, key);
		      //responseEntity = oDataReader.read(em, );
		    } else if (segmentCount == 2) { // navigation
		    /*  UriResource navSegment = resourceParts.get(1); // in our example we don't support more complex URIs
		      if (navSegment instanceof UriResourceNavigation) {
		        UriResourceNavigation uriResourceNavigation = (UriResourceNavigation) navSegment;
		        EdmNavigationProperty edmNavigationProperty = uriResourceNavigation.getProperty();
		        // contextURL displays the last segment
		        responseEdmEntitySet = Util.getNavigationTargetEntitySet(startEdmEntitySet, edmNavigationProperty);

		        // 2nd: fetch the data from backend.
		        // e.g. for the URI: Products(1)/Category we have to find the correct Category entity
		        List<UriParameter> keyPredicates = uriResourceEntitySet.getKeyPredicates();
		        // e.g. for Products(1)/Category we have to find first the Products(1)
		        Entity sourceEntity = storage.readEntityData(startEdmEntitySet, keyPredicates);

		        responseEntity = storage.getRelatedEntity(sourceEntity, uriResourceNavigation);
		      }
		    } else {*/
		      // this would be the case for e.g. Products(1)/Category/Products(1)/Category
		      throw new ODataApplicationException("Not supported", HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ROOT);
		    }

		    if (responseEntity == null) {
		      // this is the case for e.g. DemoService.svc/Categories(4) or DemoService.svc/Categories(3)/Products(999)
		      throw new ODataApplicationException("Nothing found.", HttpStatusCode.NOT_FOUND.getStatusCode(), Locale.ROOT);
		    }

		    // 3. apply system query options

		    // handle $select
		    SelectOption selectOption = uriInfo.getSelectOption();
		    // in our example, we don't have performance issues, so we can rely upon the handling in the Olingo lib
		    // nothing else to be done

		    // handle $expand
		    ExpandOption expandOption = uriInfo.getExpandOption();
		    // Nested system query options are not implemented
		   // validateNestedExpxandSystemQueryOptions(expandOption);
		    
		    // 4. serialize
		    EdmEntityType edmEntityType = responseEdmEntitySet.getEntityType();
		    // we need the property names of the $select, in order to build the context URL
		    String selectList = odata.createUriHelper().buildContextURLSelectList(edmEntityType, expandOption, selectOption);
		    ContextURL contextUrl = ContextURL.with().entitySet(responseEdmEntitySet)
		        .selectList(selectList)
		        .suffix(Suffix.ENTITY).build();

		    // make sure that $expand and $select are considered by the serializer
		    // adding the selectOption to the serializerOpts will actually tell the lib to do the job
		    EntitySerializerOptions opts = EntitySerializerOptions.with()
		        .contextURL(contextUrl)
		        .select(selectOption)
		        .expand(expandOption)
		        .build();

		    ODataSerializer serializer = this.odata.createSerializer(responseFormat);
		    SerializerResult serializerResult = serializer.entity(serviceMetadata, edmEntityType, responseEntity, opts);

		    // 5. configure the response object
		    response.setContent(serializerResult.getContent());
		    response.setStatusCode(HttpStatusCode.OK.getStatusCode());
		    response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
		  }
	
	@Override
	public void createEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat,
			ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {
		throw new RuntimeException("NOT IMPL");

	}

	@Override
	public void updateEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo, ContentType requestFormat,
			ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {
		throw new RuntimeException("NOT IMPL");

	}

	@Override
	public void deleteEntity(ODataRequest request, ODataResponse response, UriInfo uriInfo)
			throws ODataApplicationException, ODataLibraryException 
	{
		throw new RuntimeException("NOT IMPL");
	}


}

package com.next.odata4.processor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.queryoption.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.next.odata4.jpa.data.ODataReader;
import com.next.odata4.jpa.model.MdOData;

@Component
@Scope("prototype")
public class EntityCollectionProcessorImpl implements EntityCollectionProcessor {

	
	private OData odata;
	private ServiceMetadata serviceMetadata;
	@Autowired
	private MdOData odataMetadata;
	@Autowired
	private EntityManagerFactory emf;
	@Autowired
	private EntityManager em;


	public void init(OData odata, ServiceMetadata serviceMetadata) {
		this.odata = odata;
		this.serviceMetadata = serviceMetadata;
	}

	public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
			ContentType responseFormat) throws ODataApplicationException, SerializerException {
		
		List<UriResource> resourceParts = uriInfo.getUriResourceParts();
		int segmentCount = resourceParts.size();

		UriResource uriResource = resourceParts.get(0); // in our example, the first segment is the EntitySet

		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) uriResource;
		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
		EdmEntityType edmEntityType = edmEntitySet.getEntityType();
		
		Class<?> javaType = odataMetadata.getEntitySets().getByName(edmEntitySet.getName()).getJavaType();
		
		EntityCollection retEntitySet = new EntityCollection();
	    ODataReader oDataReader = new ODataReader();
	    Collection<Entity> entityList = oDataReader.readAll(emf, em, javaType);
		retEntitySet.getEntities().addAll(entityList );
		
		final ContextURL contextUrl = ContextURL.with().asCollection().type(edmEntityType).build();
		SelectOption selectOption = uriInfo.getSelectOption();
		final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
		EntityCollectionSerializerOptions opts = EntityCollectionSerializerOptions.with().contextURL(contextUrl)
				.count(uriInfo.getCountOption()).select(selectOption).expand(uriInfo.getExpandOption()).id(id).build();

		ODataSerializer serializer = odata.createSerializer(responseFormat);
		SerializerResult serializerResult = serializer.entityCollection(serviceMetadata, edmEntityType,
				retEntitySet, opts);
		InputStream serializedContent = serializerResult.getContent();

		response.setContent(serializedContent);
		response.setStatusCode(HttpStatusCode.OK.getStatusCode());
		response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());

		/*
		 * final UriResource firstResourceSegment =
		 * uriInfo.getUriResourceParts().get(0);
		 * 
		 * if(firstResourceSegment instanceof UriResourceEntitySet) {
		 * readEntityCollectionInternal(request, response, uriInfo, responseFormat); }
		 * else if(firstResourceSegment instanceof UriResourceFunction) {
		 * readFunctionImportCollection(request, response, uriInfo, responseFormat); }
		 * else { throw new ODataApplicationException("Not implemented",
		 * HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(), Locale.ENGLISH); }
		 */
	}

}

package com.next.odata4.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.next.odata4.jpa.model.MdOData;
import com.next.odata4.processor.BatchProcessorImpl;
import com.next.odata4.processor.DebugProcessorImpl;
import com.next.odata4.processor.EdmProviderImpl;
import com.next.odata4.processor.EntityCollectionProcessorImpl;
import com.next.odata4.processor.EntityProcessorImpl;



public class ODataControllerBase
{
	ODataHttpHandler handler;

	@Autowired
	MdOData odataMetadata;
	
	public static Logger logger = LoggerFactory.getLogger(ODataControllerBase.class);
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	EntityManager em;
	@Autowired
	ApplicationContext appContext;

	
	
	protected void service(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException
	{
		OData odata = OData.newInstance();
		ServiceMetadata edm = odata.createServiceMetadata(appContext.getBean(EdmProviderImpl.class),
				new ArrayList<EdmxReference>());
		handler = odata.createHandler(edm);
		handler.register(appContext.getBean(DebugProcessorImpl.class));
		handler.register(appContext.getBean(BatchProcessorImpl.class));
		handler.register(appContext.getBean(EntityCollectionProcessorImpl.class));
		handler.register(appContext.getBean(EntityProcessorImpl.class));
		handler.process(new HttpServletRequestWapper(req,path), resp);
	}
}

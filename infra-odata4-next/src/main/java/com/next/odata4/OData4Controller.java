package com.next.odata4;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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

@WebServlet(urlPatterns = "/odata4test/*")
public class OData4Controller extends HttpServlet {
	private static final long serialVersionUID = -7591082520033406328L;
	ODataHttpHandler handler;

	@PostConstruct
	public void postConstruct() 
	{
		
	}

	@Autowired
	MdOData odataMetadata;
	
	public static Logger logger = LoggerFactory.getLogger(OData4Controller.class);
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	EntityManager em;
	@Autowired
	ApplicationContext appContext;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			OData odata = OData.newInstance();
			ServiceMetadata edm = odata.createServiceMetadata(appContext.getBean(EdmProviderImpl.class),
					new ArrayList<EdmxReference>());
			//EntityManager em = emf.createEntityManager();
			handler = odata.createHandler(edm);
			handler.register(appContext.getBean(DebugProcessorImpl.class));
			handler.register(appContext.getBean(BatchProcessorImpl.class));
			handler.register(appContext.getBean(EntityCollectionProcessorImpl.class,em));
			handler.register(appContext.getBean(EntityProcessorImpl.class, em));
			handler.process(req, resp);
			//em.close();
		} catch (RuntimeException e) {
			logger.error("Server Error occurred in ExampleServlet", e);
			throw new ServletException(e);
		}
	}
}

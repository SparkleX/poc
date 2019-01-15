package com.next.odata4;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
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

import com.next.odata4.jpa.model.ODataMetadata;
import com.next.odata4.processor.DebugProcessorImpl;
import com.next.odata4.processor.BatchProcessorImpl;
import com.next.odata4.processor.JpaEdmProvider;
import com.next.odata4.processor.JpaEntityCollectionProcessor;
import com.next.odata4.processor.EntityProcessorImpl;

import gen.table.BmoORDR;

@WebServlet(urlPatterns = "/odata4/*")
public class OData4Controller extends HttpServlet {
	private static final long serialVersionUID = -7591082520033406328L;
	ODataHttpHandler handler;

	ODataMetadata odataMetadata;

	@PostConstruct
	public void postConstruct() {
		odataMetadata = new ODataMetadata(emf);
	}

	public static Logger logger = LoggerFactory.getLogger(OData4Controller.class);
	@Autowired
	EntityManagerFactory emf;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			ManagedType<BmoORDR> m = emf.getMetamodel().managedType(BmoORDR.class);
			
			OData odata = OData.newInstance();
			ServiceMetadata edm = odata.createServiceMetadata(new JpaEdmProvider(odataMetadata, emf),
					new ArrayList<EdmxReference>());
			EntityManager em = emf.createEntityManager();
			handler = odata.createHandler(edm);
			handler.register(new DebugProcessorImpl());
			handler.register(new BatchProcessorImpl());
			handler.register(new JpaEntityCollectionProcessor(odataMetadata));
			handler.register(new EntityProcessorImpl(odataMetadata, emf, em));
			handler.process(req, resp);
			em.close();
		} catch (RuntimeException e) {
			logger.error("Server Error occurred in ExampleServlet", e);
			throw new ServletException(e);
		}
	}
}

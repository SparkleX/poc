package com.next.odata4;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
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

import com.next.odata4.processor.DebugProcessor;
import com.next.odata4.processor.JpaBatchProcessor;
import com.next.odata4.processor.JpaEdmProvider;
import com.next.odata4.processor.JpaEntityCollectionProcessor;

@WebServlet(urlPatterns="/odata4/*")
public class OData4Controller extends HttpServlet
{
	private static final long serialVersionUID = -7591082520033406328L;
	ODataHttpHandler handler;
	@PostConstruct
	public void postConstruct()
	{
		OData odata = OData.newInstance();
		ServiceMetadata edm = odata.createServiceMetadata(new JpaEdmProvider(emf), new ArrayList<EdmxReference>());
		handler = odata.createHandler(edm);
		handler.register(new DebugProcessor());
		handler.register(new JpaBatchProcessor());
		handler.register(new JpaEntityCollectionProcessor());

	}
	public static Logger logger = LoggerFactory.getLogger(OData4Controller.class);
	@Autowired
	EntityManagerFactory emf;

	  @Override
	  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	//	ServiceMetadata edm = odata.createServiceMetadata(new JpaEdmProvider(emf), new ArrayList<EdmxReference>());
		try {
			/*HttpSession session = req.getSession(true);
			Storage storage = (Storage) session.getAttribute(Storage.class.getName());
			if (storage == null) {
				storage = new Storage(odata, edm.getEdm());
				session.setAttribute(Storage.class.getName(), storage);
			}*/

			// create odata handler and configure it with EdmProvider and Processor
			//ODataHttpHandler handler = odata.createHandler(edm);
			//handler.register(new DebugProcessor());
		/*	handler.register(new DemoEntityCollectionProcessor(storage));
			handler.register(new DemoEntityProcessor(storage));
			handler.register(new DemoPrimitiveProcessor(storage));
			handler.register(new DemoActionProcessor(storage));
			*/

			// let the handler do the work
			handler.process(req, resp);
		} catch (RuntimeException e) {
			logger.error("Server Error occurred in ExampleServlet", e);
			throw new ServletException(e);
		}
	}
}

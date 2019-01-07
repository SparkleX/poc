package com.next.infra.odata2;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;


@WebServlet(urlPatterns="/odata2/*")
public class OData2Servlet extends ODataServlet 
{
	private static final long serialVersionUID = -4563879895896080797L;

	@Autowired
	EntityManager em;
	@Autowired
	EntityManagerFactory emf;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	
		ODataJPACarServiceFactory oDataServiceFactory = new ODataJPACarServiceFactory(emf);
		
		req.setAttribute("org.apache.olingo.odata2.service.factory.instance", oDataServiceFactory);
		super.service(req, resp);
	}


}

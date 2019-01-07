package com.next.infra.odata2;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.odata2.api.ODataCallback;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;

import com.next.infra.odata2.function.SalesOrderProcessingExtension;

public class ODataJPACarServiceFactory extends ODataJPAServiceFactory {

	private static final int PAGE_SIZE = 5;

	private EntityManagerFactory emf;

	public ODataJPACarServiceFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDataJPAContext = getODataJPAContext();
		oDataJPAContext.setEntityManagerFactory(emf);
		oDataJPAContext.setPersistenceUnitName("default");

		oDataJPAContext.setPageSize(PAGE_SIZE);
		setDetailErrors(true);
		oDataJPAContext.setJPAEdmExtension((JPAEdmExtension) new SalesOrderProcessingExtension());
		return oDataJPAContext;
	}

	@Override
	public <T extends ODataCallback> T getCallback(final Class<T> callbackInterface) {
		{
			if (callbackInterface.isAssignableFrom(ODataErrorCallback.class)) {
				return (T) new ODataCallbackImpl();
			}
		}

		return super.getCallback(callbackInterface);
	}
}

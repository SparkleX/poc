package com.next.infra.odata4;

import javax.sql.DataSource;

import org.apache.olingo.commons.api.ex.ODataException;

import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataGetHandler;

public final class ODataHandlerImpl extends JPAODataGetHandler {

	public ODataHandlerImpl(String pUnit) throws ODataException {
		super(pUnit);
		getJPAODataContext().setCUDRequestHandler(new ODataRequestHandler());
	}

	public ODataHandlerImpl(final String pUnit, final DataSource ds) throws ODataException {
		super(pUnit, ds);
		getJPAODataContext().setCUDRequestHandler(new ODataRequestHandler());
	}

	@Override
	public JPAODataCRUDContext getJPAODataContext() {
		return (JPAODataCRUDContext) super.getJPAODataContext();
	}

}

package com.next.infra.odata2.function;

import java.io.InputStream;

import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmSchemaView;

public class SalesOrderProcessingExtension implements JPAEdmExtension {
	@Override
	  public void extendJPAEdmSchema(final JPAEdmSchemaView arg0 ){
	    // TODO Auto-generated method stub
	  }

	@Override
	public void extendWithOperation(final JPAEdmSchemaView view) {
		view.registerOperations(SalesOrderHeaderProcessor.class, null);
	}

	@Override
	public InputStream getJPAEdmMappingModelStream() {
		// TODO Auto-generated method stub
		return null;
	}
}
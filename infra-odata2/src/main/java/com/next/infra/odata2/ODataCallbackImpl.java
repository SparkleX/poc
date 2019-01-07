package com.next.infra.odata2;

import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.processor.ODataErrorContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPAErrorCallback;

public class ODataCallbackImpl extends ODataJPAErrorCallback
{
	  @Override
	  public ODataResponse handleError(final ODataErrorContext context) throws ODataApplicationException 
	  {
		  context.getException().printStackTrace();
		  return super.handleError(context);
	  }
}

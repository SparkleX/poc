package com.next.odata4.processor;

import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.debug.DebugInformation;
import org.apache.olingo.server.api.debug.DefaultDebugSupport;

public class DebugProcessorImpl extends DefaultDebugSupport {
	@Override
	public ODataResponse createDebugResponse(final String debugFormat, final DebugInformation debugInfo) {
		Exception exception = debugInfo.getException();
		if(exception!=null)
		{
			exception.printStackTrace();
		}
		return super.createDebugResponse(debugFormat, debugInfo);
	}
}

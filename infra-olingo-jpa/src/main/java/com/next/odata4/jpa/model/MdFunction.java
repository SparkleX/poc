package com.next.odata4.jpa.model;

import org.apache.olingo.commons.api.edm.provider.CsdlFunction;
import org.apache.olingo.commons.api.edm.provider.CsdlFunctionImport;

public class MdFunction 
{
	public CsdlFunction getOlingoFunction() {
		return olingoFunction;
	}

	public void setOlingoFunction(CsdlFunction olingoFunction) {
		this.olingoFunction = olingoFunction;
	}

	CsdlFunction olingoFunction;
	public CsdlFunctionImport getOlingoFunctionImport() {
		return olingoFunctionImport;
	}

	public void setOlingoFunctionImport(CsdlFunctionImport olingoFunctionImport) {
		this.olingoFunctionImport = olingoFunctionImport;
	}

	CsdlFunctionImport olingoFunctionImport;
}

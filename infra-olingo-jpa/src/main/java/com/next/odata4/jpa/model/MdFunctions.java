package com.next.odata4.jpa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlFunction;
import org.apache.olingo.commons.api.edm.provider.CsdlFunctionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlParameter;
import org.apache.olingo.commons.api.edm.provider.CsdlReturnType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.next.odata4.jpa.utils.EntityTypeUtil;

@Component
public class MdFunctions extends ArrayList<MdFunction>   implements InitializingBean 
{
	private static final long serialVersionUID = -8631748444780835168L;
	HashMap<String,MdFunction> nameMap = new HashMap<String,MdFunction>();
	
	@Autowired
	MdEntitySets entitySets;
	
	@Override
	public boolean add(MdFunction o)
	{
		nameMap.put(o.getOlingoFunction().getName(), o);
		return super.add(o);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
/*		final CsdlParameter parameterAmount = new CsdlParameter();
		parameterAmount.setName(PARAMETER_AMOUNT);
		parameterAmount.setNullable(false);
		parameterAmount.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());*/

		// Create the return type of the function
		final CsdlReturnType returnType = new CsdlReturnType();
		//returnType.setCollection(true);
		//returnType.setType(ET_CATEGORY_FQN);
		returnType.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

		// Create the function
		final CsdlFunction function = new CsdlFunction();
		FullQualifiedName fqnName = new FullQualifiedName(MdOData.NAMESPACE,"function");
		String functionName = fqnName.getName();
		function.setName(functionName)//.setParameters(Arrays.asList(parameterAmount))
				.setReturnType(returnType);

		
		
		CsdlFunctionImport functionImport = new CsdlFunctionImport();
		functionImport.setName(functionName).setFunction(fqnName);
		//.setEntitySet(ES_CATEGORIES_NAME).setIncludeInServiceDocument(true);
		
		
		MdFunction o = new MdFunction();
		o.setOlingoFunction(function);
		o.setOlingoFunctionImport(functionImport);
		this.add(o);
		
	}


	public MdFunction get(String name) 
	{
		return nameMap.get(name);
	}


	public Collection<CsdlFunction> getOlingoList() {
		Collection<CsdlFunction> rt =new ArrayList<CsdlFunction>();
		this.forEach(a->rt.add(a.getOlingoFunction()));
		return rt;
	}


	public List<CsdlFunctionImport> getOlingoFunctionImportList() {
		List<CsdlFunctionImport> rt =new ArrayList<CsdlFunctionImport>();
		this.forEach(a->rt.add(a.getOlingoFunctionImport()));		
		return rt;
	}
}

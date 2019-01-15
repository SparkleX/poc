package com.next.odata4.jpa.utils;

import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import com.next.odata4.annotation.ODataEntityType;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataTransient;

public class EntityTypeUtil 
{
	
	public <X, Y> CsdlProperty getProperty(Attribute<X, Y> attr)
	{
		Member javaMember = attr.getJavaMember();
		ODataTransient ignore = MemberUtil.getAnnotation(javaMember, ODataTransient.class);
		if(ignore!=null){
			return null;
		}
		ODataProperty odataProperty = MemberUtil.getAnnotation(javaMember, ODataProperty.class);
		CsdlProperty csdlProperty = new CsdlProperty();
		String name = odataProperty.alias();
		csdlProperty.setName(name);
		Class<Y> javaType = attr.getJavaType();
		EdmPrimitiveTypeKind edmType = getEdmPrimitiveType(javaType);
		csdlProperty.setType(edmType.getFullQualifiedName());
		return csdlProperty;
		
	}
	
	static public <Y> EdmPrimitiveTypeKind getEdmPrimitiveType(Class<Y> javaType)
	{
		if(javaType.equals(String.class))
			return EdmPrimitiveTypeKind.String;
		if(javaType.equals(Integer.class))
			return EdmPrimitiveTypeKind.Int32;
		if(javaType.equals(BigDecimal.class))
			return EdmPrimitiveTypeKind.Decimal;
		if(javaType.equals(Timestamp.class))
			return EdmPrimitiveTypeKind.Date;
		if(javaType.equals(Date.class))
			return EdmPrimitiveTypeKind.Date;
		
		throw new RuntimeException();
	}
	public <X> CsdlEntityType getEntityType(EntityType<X> entityType)
	{
		CsdlEntityType csdlEntityType = new CsdlEntityType();
		String name = entityType.getJavaType().getAnnotation(ODataEntityType.class).name();
		csdlEntityType.setName(name);
		List<CsdlProperty> properties = new ArrayList<>();
		for(Attribute<?, ?> attr: entityType.getAttributes())
		{
			CsdlProperty csdlProperty = getProperty(attr);
			if(csdlProperty==null)
			{
				continue;
			}
			properties.add(csdlProperty);
		}
		csdlEntityType.setProperties(properties);
		
		List<CsdlPropertyRef> keys = new ArrayList<>();
		for(Attribute<?, ?> attr: entityType.getAttributes())
		{
			SingularAttribute<?, ?> sattr =  (SingularAttribute<?, ?>) attr;
			if(sattr.isId()==false) continue;
			CsdlProperty csdlProperty = getProperty(attr);
			CsdlPropertyRef propertyRef = new CsdlPropertyRef();
			propertyRef.setName(csdlProperty.getName());
			keys.add(propertyRef);
		}
		csdlEntityType.setKey(keys);		
		
		
		return csdlEntityType;
	}
	public List<CsdlEntityType> getEntityTypes(EntityManagerFactory emf)
	{
		List<CsdlEntityType> rt = new ArrayList<CsdlEntityType>();
		for( EntityType<?> e:emf.getMetamodel().getEntities())
		{
			System.out.println(e.getJavaType());
			CsdlEntityType entityType = getEntityType(e);
			rt.add(entityType);

		}
		return rt;
		/*CsdlEntityType entityType;
		CsdlProperty id = new CsdlProperty().setName("ID")
				.setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		CsdlProperty name = new CsdlProperty().setName("Name")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
		CsdlProperty description = new CsdlProperty().setName("Description")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		// create PropertyRef for Key element
		CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		propertyRef.setName("ID");

		// navigation property: many-to-one, null not allowed (product must have a
		// category)
		CsdlNavigationProperty navProp = new CsdlNavigationProperty().setName(NAV_TO_CATEGORY)
				.setType(ET_CATEGORY_FQN).setNullable(true).setPartner("Products");
		List<CsdlNavigationProperty> navPropList = new ArrayList<CsdlNavigationProperty>();
		navPropList.add(navProp);

		// configure EntityType
		entityType.setName(ET_PRODUCT_NAME);
		entityType.setProperties(Arrays.asList(id, name, description));
		entityType.setKey(Arrays.asList(propertyRef));
		entityType.setNavigationProperties(navPropList);
		return entityType;*/
		//return null;
	}
}

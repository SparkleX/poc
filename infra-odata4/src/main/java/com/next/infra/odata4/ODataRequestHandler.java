package com.next.infra.odata4;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.apache.olingo.commons.api.http.HttpMethod;

import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAEntityType;
import com.sap.olingo.jpa.processor.core.api.JPAAbstractCUDRequestHandler;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAInvocationTargetException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException;
import com.sap.olingo.jpa.processor.core.modify.JPAUpdateResult;
import com.sap.olingo.jpa.processor.core.processor.JPARequestEntity;
import com.sap.olingo.jpa.processor.core.processor.JPARequestLink;

public class ODataRequestHandler extends JPAAbstractCUDRequestHandler 
{
	@Override
	public Object createEntity(final JPARequestEntity requestEntity, final EntityManager em)
			throws ODataJPAProcessException {

		final JPAEntityType et = requestEntity.getEntityType();

		/*if (et.getExternalName().equals("AdministrativeDivision")) {
			final Map<String, Object> jpaAttributes = requestEntity.getData();
			AdministrativeDivision result = new AdministrativeDivision();

			result.setCodeID((String) jpaAttributes.get("codeID"));
			result.setCodePublisher((String) jpaAttributes.get("codePublisher"));
			result.setDivisionCode((String) jpaAttributes.get("divisionCode"));

			result.setCountryCode((String) jpaAttributes.get("countryCode"));
			result.setParentCodeID((String) jpaAttributes.get("parentCodeID"));
			result.setParentDivisionCode((String) jpaAttributes.get("parentDivisionCode"));

			result.setAlternativeCode((String) jpaAttributes.get("alternativeCode"));
			result.setArea((Integer) jpaAttributes.get("area"));
			result.setPopulation((Long) jpaAttributes.get("population"));

			em.persist(result);
			return result;
		}*/
		return super.createEntity(requestEntity, em);
	}
	
	@Override
	public void deleteEntity(JPARequestEntity requestEntity, EntityManager em) throws ODataJPAProcessException {

	  final Object instance = em.find(requestEntity.getEntityType().getTypeClass(),
	      requestEntity.getModifyUtil().createPrimaryKey(requestEntity.getEntityType(), requestEntity.getKeys(),
	          requestEntity.getEntityType()));
	  if (instance != null)
	    em.remove(instance);
	}
	  @Override
	  public JPAUpdateResult updateEntity(final JPARequestEntity requestEntity, final EntityManager em,
			final HttpMethod method) throws ODataJPAProcessException {
			
	    if (method == HttpMethod.PATCH || method == HttpMethod.DELETE) {
	      final Object instance = em.find(requestEntity.getEntityType().getTypeClass(), requestEntity.getModifyUtil()
	          .createPrimaryKey(requestEntity.getEntityType(), requestEntity.getKeys(), requestEntity.getEntityType()));
	      requestEntity.getModifyUtil().setAttributesDeep(requestEntity.getData(), instance, requestEntity.getEntityType());
	      updateLinks(requestEntity, em, instance);
	      return new JPAUpdateResult(false, instance);
	    }
	    return super.updateEntity(requestEntity, em, method);
	  }

	  private void updateLinks(final JPARequestEntity requestEntity, final EntityManager em, final Object instance)
	      throws ODataJPAProcessorException, ODataJPAInvocationTargetException {
	    if (requestEntity.getRelationLinks() != null) {
	      for (Entry<JPAAssociationPath, List<JPARequestLink>> links : requestEntity.getRelationLinks().entrySet()) {
	        for (JPARequestLink link : links.getValue()) {
	          final Object related = em.find(link.getEntityType().getTypeClass(), requestEntity.getModifyUtil()
	              .createPrimaryKey(link.getEntityType(), link.getRelatedKeys(), link.getEntityType()));
	          requestEntity.getModifyUtil().linkEntities(instance, related, links.getKey());
	        }
	      }
	    }
	  }
}

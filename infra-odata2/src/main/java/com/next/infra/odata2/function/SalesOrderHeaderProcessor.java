package com.next.infra.odata2.function;

import org.apache.olingo.odata2.api.annotation.edm.EdmFacets;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.HttpMethod;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType.Type;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImportParameter;

public class SalesOrderHeaderProcessor 
{

	//private EntityManager em;

	public SalesOrderHeaderProcessor() {
		//em = Persistence.createEntityManagerFactory("salesorderprocessing").createEntityManager();
	}

	/*@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "FindAllSalesOrders", entitySet = "SalesOrders", returnType = @ReturnType(type = Type.ENTITY, isCollection = true))
	public List<SalesOrderr> findAllSalesOrders(
			@EdmFunctionImportParameter(name = "DeliveryStatusCode", facets = @EdmFacets(maxLength = 2)) final String status) {

		Query q = em.createQuery("SELECT E1 from SalesOrderHeader E1 WHERE E1.deliveryStatus = '" + status + "'");
		List<SalesOrder> soList = (List<SalesOrderHeader>) q.getResultList();
		return soList;
	}*/

	@EdmFunctionImport(name = "CheckATP", returnType = @ReturnType(type = Type.SIMPLE, isCollection = false), httpMethod = HttpMethod.GET)
	public boolean checkATP(
			@EdmFunctionImportParameter(name = "SoID", facets = @EdmFacets(nullable = false)) final Long soID,
			@EdmFunctionImportParameter(name = "LiId", facets = @EdmFacets(nullable = false)) final Long lineItemID) {
		if (soID == 2L) {
			return false;
		} else {
			return true;
		}
	}


}
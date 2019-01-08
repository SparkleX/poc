package gen.table;

import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import javax.persistence.Id;

//@EdmEntityType(name="SalesOrder",namespace="com.next")
@EdmEntitySet(name="SalesOrders")
@SuppressWarnings("all")
@Entity
@Table(name="ORDR")
public class BmoORDR
{
	@Id
    @Column
    Integer Id;
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
    @Column
    Integer BpId;
	public Integer getBpId(){return BpId;}
	public void setBpId(Integer val){BpId=val;}
    @Column
    String Remarks;
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
    @Column
    BigDecimal DocTotal;
	public BigDecimal getDocTotal(){return DocTotal;}
	public void setDocTotal(BigDecimal val){DocTotal=val;}
    @Column
    Date CreateDate;
	public Date getCreateDate(){return CreateDate;}
	public void setCreateDate(Date val){CreateDate=val;}
}

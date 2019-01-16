package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataTransient;
import com.next.odata4.annotation.ODataEntitySet;
import com.next.odata4.annotation.ODataEntityType;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="ORDR")
@ODataEntityType(name="Document")
public class BmoORDR
{
	@Id
    @Column
	@ODataProperty(alias="Id") 
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
	Integer Id;
    @Column
	@ODataProperty(alias="BusinessPartnerId") 
	public Integer getBpId(){return BpId;}
	public void setBpId(Integer val){BpId=val;}
	Integer BpId;
    @Column
	@ODataProperty(alias="Remarks") 
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
	String Remarks;
    @Column
	@ODataProperty(alias="DocumentTotal") 
	public BigDecimal getDocTotal(){return DocTotal;}
	public void setDocTotal(BigDecimal val){DocTotal=val;}
	BigDecimal DocTotal;
    @Column(precision=10)
	@ODataProperty(alias="CreateDate") 
	public Date getCreateDate(){return CreateDate;}
	public void setCreateDate(Date val){CreateDate=val;}
	Date CreateDate;
}

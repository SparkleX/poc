package gen.table;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
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
	@ODataProperty
	@GeneratedValue
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
	Integer Id;
    @Column
	@ODataProperty
	public Integer getBpId(){return BpId;}
	public void setBpId(Integer val){BpId=val;}
	Integer BpId;
    @Column
	@ODataProperty
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
	String Remarks;
    @Column
	@ODataProperty
	public BigDecimal getDocTotal(){return DocTotal;}
	public void setDocTotal(BigDecimal val){DocTotal=val;}
	BigDecimal DocTotal;
    @Column
	@ODataProperty
	public Date getCreateDate(){return CreateDate;}
	public void setCreateDate(Date val){CreateDate=val;}
	Date CreateDate;
}

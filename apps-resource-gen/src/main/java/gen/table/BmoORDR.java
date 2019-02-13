package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="ORDR")
public class BmoORDR
{
	@Id
	@GeneratedValue	
    @Column
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
	Integer Id;
    @Column
	public Integer getBpId(){return BpId;}
	public void setBpId(Integer val){BpId=val;}
	Integer BpId;
    @Column
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
	String Remarks;
    @Column
	public BigDecimal getDocTotal(){return DocTotal;}
	public void setDocTotal(BigDecimal val){DocTotal=val;}
	BigDecimal DocTotal;
    @Column
	public Date getCreateDate(){return CreateDate;}
	public void setCreateDate(Date val){CreateDate=val;}
	Date CreateDate;
    @Column
	public String getDocStatus(){return DocStatus;}
	public void setDocStatus(String val){DocStatus=val;}
	String DocStatus;
}

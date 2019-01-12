package gen.table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="ORDR")
public class BmoORDR
{
    @Column
    @Id
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
    @Column(precision=10)
    Date CreateDate;
	public Date getCreateDate(){return CreateDate;}
	public void setCreateDate(Date val){CreateDate=val;}
	
	public Collection<BmoRDR1> getRDR1() {
		return RDR1;
	}
	public void setRDR1(Collection<BmoRDR1> rDR1) {
		RDR1 = rDR1;
	}
	@OneToMany(mappedBy="doc",fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	Collection<BmoRDR1> RDR1 = new ArrayList<>();

}

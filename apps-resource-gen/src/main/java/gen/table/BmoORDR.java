package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

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
    String BpId;
	public String getBpId(){return BpId;}
	public void setBpId(String val){BpId=val;}
}

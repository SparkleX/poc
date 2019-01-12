package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="RDR1")
public class BmoRDR1
{
    @Column
    @Id
    Integer Id;
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
    @Column
    Integer ParentId;
	public Integer getParentId(){return ParentId;}
	public void setParentId(Integer val){ParentId=val;}
    @Column
    Integer LineId;
	public Integer getLineId(){return LineId;}
	public void setLineId(Integer val){LineId=val;}
}

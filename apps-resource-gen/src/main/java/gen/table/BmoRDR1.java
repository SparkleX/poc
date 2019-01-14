package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataTransient;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="RDR1")
public class BmoRDR1
{
	@Id
    @Column
	@ODataProperty(alias="Id") 
    Integer Id;
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
    @Column
    @ODataTransient
    Integer ParentId;
	public Integer getParentId(){return ParentId;}
	public void setParentId(Integer val){ParentId=val;}
    @Column
	@ODataProperty(alias="LineNumber") 
    Integer LineId;
	public Integer getLineId(){return LineId;}
	public void setLineId(Integer val){LineId=val;}
}

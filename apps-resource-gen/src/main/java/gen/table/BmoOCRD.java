package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataEntityType;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="OCRD")
@ODataEntityType
public class BmoOCRD
{
	@Id
	@GeneratedValue	
    @Column
	@ODataProperty
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
	Integer Id;
    @Column
	@ODataProperty
	public String getCardCode(){return CardCode;}
	public void setCardCode(String val){CardCode=val;}
	String CardCode;
    @Column
	@ODataProperty
	public String getCardName(){return CardName;}
	public void setCardName(String val){CardName=val;}
	String CardName;
    @Column
	@ODataProperty
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
	String Remarks;
}

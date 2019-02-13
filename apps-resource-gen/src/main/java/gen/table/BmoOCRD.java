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
@Table(name="OCRD")
public class BmoOCRD
{
	@Id
	@GeneratedValue	
    @Column
	public Integer getId(){return Id;}
	public void setId(Integer val){Id=val;}
	Integer Id;
    @Column
	public String getCardCode(){return CardCode;}
	public void setCardCode(String val){CardCode=val;}
	String CardCode;
    @Column
	public String getCardName(){return CardName;}
	public void setCardName(String val){CardName=val;}
	String CardName;
    @Column
	public String getRemarks(){return Remarks;}
	public void setRemarks(String val){Remarks=val;}
	String Remarks;
}

package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataEntityType;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="${name}")
@ODataEntityType(name="${odataEntityType}")
public class Bmo${name}
{
    <#list column as column>
	<#if isKey(column.name)>
	@Id
	</#if>
    @Column
    <#if column.odataName=="">
    <#else> 
	@ODataProperty
	</#if>
	public ${column.javaType} get${column.name}(){return ${column.name};}
	public void set${column.name}(${column.javaType} val){${column.name}=val;}
	${column.javaType} ${column.name};
    </#list>
}

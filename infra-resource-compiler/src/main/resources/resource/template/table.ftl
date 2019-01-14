package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import com.next.odata4.annotation.ODataProperty;
import com.next.odata4.annotation.ODataTransient;
import com.next.odata4.annotation.ODataEntitySets;
import com.next.odata4.annotation.ODataEntityType;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("all")
@Entity
@Table(name="${name}")
@ODataEntityType(name="${odataEntityType}")
<#if odataEntitySets??>
@ODataEntitySets(name="${odataEntitySets.name}",type="${odataEntitySets.type}")
</#if>
public class Bmo${name}
{
    <#list column as column>
	<#if isKey(column.name)>
	@Id
	</#if>
    @Column
    <#if column.odataName=="">
    @ODataTransient
    <#else> 
	@ODataProperty(alias="${column.odataName}") 
	</#if>
    ${column.javaType} ${column.name};
	public ${column.javaType} get${column.name}(){return ${column.name};}
	public void set${column.name}(${column.javaType} val){${column.name}=val;}
    </#list>
}

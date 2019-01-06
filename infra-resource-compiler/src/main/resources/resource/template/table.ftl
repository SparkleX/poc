package gen.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="${name}")
public class Bmo${name}
{
    <#list column as column>
    @Column
    Object ${column.name};
	public Object get${column.name}(){return ${column.name};}
	public void set${column.name}(Object val){${column.name}=val;}
    </#list>
}

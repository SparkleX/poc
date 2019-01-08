package compiler;

import com.next.infra.schema.Column;

public class ColumnImpl extends Column
{
	public String getJavaType()
	{
		return ColumnTypeImpl.getJavaType(this.getType());
	}
}

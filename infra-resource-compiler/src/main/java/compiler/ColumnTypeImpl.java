package compiler;

import com.next.infra.schema.ColumnType;

public class ColumnTypeImpl 
{
	static String getJavaType(ColumnType type)
	{
		switch(type)
		{
		case STRING: return "String";
		case INT: return "Integer";
		case DECIMAL: return "BigDecimal";
		case DATETIME: return "Date";
		}
		throw new RuntimeException(type.value());
	}
}

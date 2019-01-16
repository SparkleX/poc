package com.next.odata4.config;

public interface ODataCrudService<T, ID>
{
	T create(T o);
	void update(ID id, T o);
	void delete(ID id);
}

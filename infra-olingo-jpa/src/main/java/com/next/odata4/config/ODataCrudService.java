package com.next.odata4.config;

public interface ODataCrudService<T, ID>
{
	void create(T o);
	void update(ID id, T o);
	void delete(ID id);
}

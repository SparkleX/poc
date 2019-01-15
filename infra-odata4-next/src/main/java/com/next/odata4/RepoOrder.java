package com.next.odata4;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gen.table.BmoORDR;

public interface RepoOrder extends CrudRepository<BmoORDR, Integer> 
{
	@Query("select t from BmoORDR t where t.id = ?1")
	List<BmoORDR> findByMyId(Integer integer);
}

package com.next.odata4;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.CrudRepository;

import gen.table.BmoORDR;

public interface RepoOrder extends JpaRepository<BmoORDR, Integer>, QuerydslPredicateExecutor<BmoORDR>
{


	@Query("select t from BmoORDR t where t.id = ?1")
	List<BmoORDR> findByMyId(Integer integer);
}

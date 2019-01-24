package com.next.apps.repo.query;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import gen.table.BmoORDR;

public interface RepoORDR extends JpaRepository<BmoORDR, Integer>, QuerydslPredicateExecutor<BmoORDR>
{


	@Query("select t from BmoORDR t where t.id = ?1")
	List<BmoORDR> findByMyId(Integer integer);
}

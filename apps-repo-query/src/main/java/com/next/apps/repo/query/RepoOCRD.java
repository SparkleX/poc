package com.next.apps.repo.query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import gen.table.BmoOCRD;

public interface RepoOCRD extends JpaRepository<BmoOCRD, Integer>, QuerydslPredicateExecutor<BmoOCRD>
{

}

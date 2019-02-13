package com.next.apps.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestBody;

import com.querydsl.core.types.Predicate;

public class ServiceBase<T_Bean, T_Repo extends QuerydslPredicateExecutor<T_Bean>> 
{
	@Autowired
	T_Repo repo;
	
	
	public Iterable<T_Bean> findAll(@QuerydslPredicate Predicate predicate) 
	{
	    return repo.findAll(predicate);
	}
	
	
	public Optional<T_Bean> get(Integer id) 
	{
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		Optional<T_Bean> data = repoJpa.findById(id);

		if (!data.isPresent())
			throw new RuntimeException("no data found id:" + id);

		return data;
	}
	
	public void create(@RequestBody T_Bean data) {
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		T_Bean savedData = repoJpa.save(data);

	/*	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(100).toUri();

		return ResponseEntity.created(location).build();*/

	}
	
	
	public void update(T_Bean data, Integer id) {
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		Optional<T_Bean> dataOptional = repoJpa.findById(id);

//		if (!dataOptional.isPresent())
	//		return ResponseEntity.notFound().build();
		
		repoJpa.save(data);

//		return ResponseEntity.noContent().build();
	}
	
	public void delete(Integer id) 
	{
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		repoJpa.deleteById(id);
	}
}

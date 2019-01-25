package com.next.apps.bo;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.querydsl.core.types.Predicate;

public class BoBase<T_Bean, T_Repo extends QuerydslPredicateExecutor<T_Bean>> 
{
	@Autowired
	T_Repo repo;
	
	
	@GetMapping("")
	@ResponseBody
	protected Iterable<T_Bean> search(@QuerydslPredicate Predicate predicate) {
	    return repo.findAll(predicate);
	}
	
	@GetMapping("/{id}")
	public T_Bean get(@PathVariable Integer id) 
	{
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		Optional<T_Bean> data = repoJpa.findById(id);

		if (!data.isPresent())
			throw new RuntimeException("no data found id:" + id);

		return data.get();
	}
	
	
	@PostMapping("")
	public ResponseEntity<Object> createStudent(@RequestBody T_Bean data) {
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		T_Bean savedData = repoJpa.save(data);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(100).toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<T_Bean> update(@RequestBody T_Bean data, @PathVariable Integer id) {
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		Optional<T_Bean> dataOptional = repoJpa.findById(id);

		if (!dataOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		repoJpa.save(data);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		JpaRepository<T_Bean,Object> repoJpa = (JpaRepository<T_Bean,Object>)repo;
		repoJpa.deleteById(id);
	}
}

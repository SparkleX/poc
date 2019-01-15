package com.next.odata4;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public class RepoOrderImpl<T, ID> extends SimpleJpaRepository<T, ID>
{
	public RepoOrderImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Transactional
	public void deleteById(ID id) 
	{
		System.out.println("123");
		super.deleteById(id);
	}
}

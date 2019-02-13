package com.next.apps.repo.query.test;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.next.apps.repo.query.RepoORDR;
import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepoTest {

	//@Autowired
	//private TestEntityManager entityManager;

	@Autowired
	private RepoORDR repository;

	@Test
	public void testExample() throws Exception {
		//this.entityManager.persist(new User("sboot", "1234"));
		this.repository.findByMyId(1);
//		assertThat(user.getUsername()).isEqualTo("sboot");
//		assertThat(user.getVin()).isEqualTo("1234");
	}

}
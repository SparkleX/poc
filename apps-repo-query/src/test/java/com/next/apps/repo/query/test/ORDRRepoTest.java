package com.next.apps.repo.query.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.*;

import com.next.apps.repo.query.RepoORDR;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ORDRRepoTest extends RepoTestBase<RepoORDR>{




	@Test
	public void testExample() throws Exception {
		//this.entityManager.persist(new User("sboot", "1234"));
		this.repository.findByMyId(1);
//		assertThat(user.getUsername()).isEqualTo("sboot");
//		assertThat(user.getVin()).isEqualTo("1234");
	}

}
package com.next.apps.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.apps.repo.query.RepoORDR;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OCRDServiceTest  extends ServiceTestBase
{

	@Autowired
	private ORDRService svcOrder;
	
	@MockBean
	private OCRDService svcOCRD;	

	@Test
	public void test() throws Exception 
	{
		/*given(this.svcOCRD.checkBalance())
			.willReturn(BigDecimal.ONE)
			.willReturn(BigDecimal.valueOf(2));*/
		
		when(this.svcOCRD.checkBalance())
			.thenReturn(BigDecimal.ONE)
			.thenReturn(BigDecimal.valueOf(2));
		
		BigDecimal credit = svcOrder.checkCredit();
		assertThat(credit).isEqualTo(BigDecimal.valueOf(3));		

	}

}
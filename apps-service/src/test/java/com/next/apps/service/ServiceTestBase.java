package com.next.apps.service;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.next.apps.repo.query.RepoOCRD;
import com.next.apps.repo.query.RepoORDR;

public class ServiceTestBase {
	@MockBean
	protected RepoORDR repoORDR;
	@MockBean
	protected RepoOCRD repoOCRD;
}

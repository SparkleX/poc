package com.next.apps.repo.query.test;

import org.springframework.beans.factory.annotation.Autowired;


public class RepoTestBase <T_Repo>
{
	@Autowired
	protected T_Repo repository;
}

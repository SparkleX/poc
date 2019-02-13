package com.next.apps.repo.query.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.next.apps.repo.query.RepoORDR;

import gen.table.BmoORDR;

@SpringBootApplication
@EntityScan(basePackageClasses = BmoORDR.class)
@EnableJpaRepositories(basePackageClasses = RepoORDR.class)
public class AppTest {

    public static void main(String[] args) {
        SpringApplication.run(AppTest.class, args);
    }
}
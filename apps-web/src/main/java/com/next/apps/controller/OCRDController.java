package com.next.apps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.apps.service.OCRDService;
import com.querydsl.core.types.Predicate;

import gen.table.BmoOCRD;
import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api/ocrd")
@Api(tags= "Business Partners")
public class OCRDController extends ControllerBase<BmoOCRD,OCRDService>
{
	

	@Override
	protected Iterable<BmoOCRD> search(Predicate predicate) {
	    return super.search(predicate);
	}

}

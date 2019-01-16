package com.next.odata4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.odata4.config.ODataControllerBase;

@RestController
public class ODataControllerImpl extends ODataControllerBase
{
	@RequestMapping("/odata4/**")
	void service(HttpServletRequest req, HttpServletResponse resp)
	{
		super.service(req, resp,"/odata4");
	}
}

package com.next.odata4;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.odata4.config.ODataControllerBase;

@RestController
public class ODataControllerImpl extends ODataControllerBase
{
	@RequestMapping(path="/odata4test/**",consumes = "*/*")
	protected void service(HttpServletRequest req, HttpServletResponse resp, 
			@RequestBody byte[] body) throws IOException
	{
		System.out.println(body);
		super.service(req, resp,"/odata4");
	}
}

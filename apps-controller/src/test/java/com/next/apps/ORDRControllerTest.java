package com.next.apps;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.next.apps.controller.OCRDController;
import com.next.apps.controller.ORDRController;
import com.next.apps.service.OCRDService;
import com.next.apps.service.ORDRService;

import gen.table.BmoOCRD;
import gen.table.BmoORDR;

@RunWith(SpringRunner.class)
@WebMvcTest(ORDRController.class)
public class ORDRControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper json;
	
	@MockBean
	private ORDRService svcOCRD;

	@Test
	public void testExample() throws Exception 
	{
		BmoORDR bmoOCRD = new BmoORDR();
		Optional<BmoORDR> rt = Optional.of(bmoOCRD); 
		
		given(this.svcOCRD.get(1)).willReturn(rt);
		String str = this.json.writeValueAsString(bmoOCRD);
		this.mvc.perform(
					get("/api/ordr/1")
					.accept(MediaType.APPLICATION_JSON)
					)
				.andExpect(status().isOk())
				.andExpect(content().string(str));
	}

}
package com.dev.anonymiser.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.anonymiser.bean.CODS;

@RestController
public class AnonymiserController {
	
	@RequestMapping("/cods")
	public CODS getCODSdataREST(@RequestParam(value="description", defaultValue="World")String description) {
		
		CODS data = new CODS();
		data.setId(1);
		data.setContent(description);		
		return data;
		
	}

}

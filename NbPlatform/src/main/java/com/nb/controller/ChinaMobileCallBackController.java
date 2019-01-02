package com.nb.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChinaMobileCallBackController {

	@RequestMapping(value = "URLVerification", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String URLVerification(String msg, String nonce, String signature) {
		System.out.println(msg + "  " + nonce + "  " + signature);
		
		return msg;
	}
}

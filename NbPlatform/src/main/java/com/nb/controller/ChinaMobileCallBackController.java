package com.nb.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nb.utils.AuthenticationUtils;

@RestController
public class ChinaMobileCallBackController {

	@RequestMapping(value = "receivingPushMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String URLVerification(String msg, String nonce, String signature) {
		System.out.println();
		return AuthenticationUtils.verificationToken(msg, nonce, signature);
	}

	@RequestMapping(value = "receivingPushMessages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> URLVerification(@RequestBody Object pushMessages) {
		System.out.println("pushMessages : " + pushMessages);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

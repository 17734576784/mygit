package com.protobuftest.protobuf;

import com.protobuftest.protobuf.PersonProbuf.Person;
import com.protobuftest.protobuf.PersonProbuf.Person.PhoneNumber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
		builder.setEmail("kkk@email.com");
		builder.setId(1);
		builder.setName("TestName");
		builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("131111111")
				.setType(PersonProbuf.Person.PhoneType.MOBILE));
		builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("011111")
				.setType(PersonProbuf.Person.PhoneType.HOME));

		Person person = builder.build();
		byte[] buf = person.toByteArray();
		/*
		 * try { Person person2 = PersonProbuf.Person.parseFrom(buf);
		 * System.out.println(person2.getName() + ", " + person2.getEmail());
		 * List<PhoneNumber> lstPhones = person2.getPhoneList(); for (PhoneNumber
		 * phoneNumber : lstPhones) { System.out.println(phoneNumber.getNumber()); } }
		 * catch (InvalidProtocolBufferException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		System.out.println(buf);

		FileInputStream fs = null;
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream("D:\\test3");
			fo.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
//			fs = new FilenpIutStream("D:\\test3");
			InputStream is = new ByteArrayInputStream(buf);
			Person person2 = PersonProbuf.Person.parseFrom(is);
			System.out.println("ok" + person2.getName() + ", " + person2.getEmail());
			List<PhoneNumber> lstPhones = person2.getPhoneList();
			for (PhoneNumber phoneNumber : lstPhones) {
				System.out.println("ok:" + phoneNumber.getNumber());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}
}

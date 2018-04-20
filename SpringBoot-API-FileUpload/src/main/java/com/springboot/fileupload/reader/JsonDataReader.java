package com.springboot.fileupload.reader;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.springboot.fileupload.bo.Customer;

@Service
public class JsonDataReader {

	public Customer generateData(String file) {
		JSONParser parser = new JSONParser();

		Object object = null;
		try {
			object = parser.parse(new FileReader(file));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		// convert Object to JSONObject
		JSONObject jsonObject = (JSONObject) object;

		// Reading the String
		String customerID = (String) jsonObject.get("customerID");
		String name = (String) jsonObject.get("name");
		String customerType = (String) jsonObject.get("customerType");
		String address = (String) jsonObject.get("address");

		Customer customer = new Customer();
		customer.setCustomerID(customerID);
		customer.setName(name);
		customer.setCustomerType(customerType);
		customer.setAddress(address);

		return customer;
	}
}

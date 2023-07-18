package com.test.helpers;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.test.models.CreateUserPOJO;
import com.test.utility.*;

import io.restassured.response.Response;

public class ReusableMethods {
	static String name="Jonny";
	static int age=23;
	static int sal = 12323;
	public static CreateUserPOJO newUserData() {
	
	CreateUserPOJO data = new CreateUserPOJO();
	data.setName(name);
	data.setSalary(sal);
	data.setAge(age);
	
	return data;
	
	}
	
	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	public String getContenttype(Response response) {
		return response.getContentType();
	}
	public long getResponseTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}

	public static void verifyCreatedUserDetailsResponse(String responseName, int responseAge,
			int responseSalary) {
		if((responseName.equalsIgnoreCase(name))&& responseAge==age && responseSalary==sal ) {
			System.out.println("The response details match with input details");
		}
		else {
			System.out.println("The response details doesnot match with input details");
		}
	}

}

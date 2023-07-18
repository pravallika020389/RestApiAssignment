package com.test.tests;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;


import com.test.helpers.ReusableMethods;
import com.test.helpers.UserSerciveHelper;
import com.test.models.CreatedUserResponsePOJO;
import com.test.models.DeleteUserResponsePOJO;
import com.test.models.GetUserPOJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class DummyApiEndToEnd extends UserSerciveHelper {

	static int id;


	@BeforeClass
	public void getUri() {
		RestAssured.baseURI = getBaseUri();
		System.out.println("Got Base Uri");
	}

	@Test
	public void TC_001_getUserData() {
		System.out.println("Retriving data");

		Response res = getUserData();

		res.then().statusCode(200).body("status", is("success"));
		
		GetUserPOJO userData = res.as(GetUserPOJO.class);
		int totalRecords = userData.getData().length;
				//res.body().jsonPath().get("data.size()");

		System.out.println("Number of records are :" + totalRecords);
	
		res.prettyPrint();

		System.out.println("//////////////////////////////////////////////////////////////////////");

	}

	@Test
	public static void TC_002_addUserData() {
		System.out.println("Creating an User");

		Response res = addUserData();

		res.then().statusCode(200).body("status", is("success"));

		res.prettyPrint();

		CreatedUserResponsePOJO data = res.as(CreatedUserResponsePOJO.class);
		id = data.getData().getId();
		System.out.println("Created Id : " + id);
		String responseName = data.getData().getName();
		int responseAge = data.getData().getAge();
		int responseSalary = data.getData().getSalary();
		ReusableMethods.verifyCreatedUserDetailsResponse(responseName, responseAge, responseSalary);

		System.out.println("//////////////////////////////////////////////////////////////////////");

	}

	@Test(dependsOnMethods = "TC_002_addUserData")
	public void TC_003_deleteCreatedUser() {
		System.out.println("Delete the created user");
		System.out.println(id);
		Response res = deleteCreatedUser(id);
		res.prettyPrint();
		res.then().statusCode(200);
		DeleteUserResponsePOJO data = res.as(DeleteUserResponsePOJO.class);
		System.out.println("Response data field" + data.getData());

		assertEquals(id, data.getData());
		System.out.println("Response message : " + data.getMessage());
		System.out.println("//////////////////////////////////////////////////////////////////////");

	}

	@Test
	public void TC_004_deleteUserDataError() {
		System.out.println("Deleting with id as zero");
		System.out.println(0);
		Response res = deleteCreatedUser(0);
		res.prettyPrint();
		res.then().statusCode(400).body("status", is("error"));
		String resMessage = res.body().jsonPath().get("message");
		System.out.println("Response message : " + resMessage);
		System.out.println("//////////////////////////////////////////////////////////////////////");

	}

	@Test
	public void TC_005_getSpecificUserData() {
		System.out.println("Retriving specific user data ");

		int specificId = 2;
		Response res = getUserData(specificId);

		res.then().statusCode(200).contentType(ContentType.JSON);
		
		String responseName = res.body().jsonPath().get("data.employee_name");
		String actualName = "Garrett Winters";
		int responseSalary=res.body().jsonPath().get("data.employee_salary");
		int actualSalary=170750;
		int responseAge=res.body().jsonPath().get("data.employee_age");
		int actualAge=63;
		assertEquals(responseName, actualName);
		assertEquals(actualSalary, responseSalary);
		assertEquals(actualAge, responseAge);
		res.prettyPrint();
		System.out.println("//////////////////////////////////////////////////////////////////////");

	}

}

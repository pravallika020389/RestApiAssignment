package com.test.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;


import com.test.constants.EndPoints;
import com.test.models.CreateUserPOJO;
import com.test.models.CreatedUserResponsePOJO;
import com.test.models.GetUserPOJO;

import com.test.utility.PropertiesUtility;
import com.test.models.DeleteUserResponsePOJO;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
public class UserSerciveHelper {
	static PropertiesUtility pro = new PropertiesUtility();
	static Properties appProp = pro.loadFile("applicationDataProperties");
	static Response response;
	static String extractedToken= null;
	
	public static String getBaseUri() { 
			String baseUri = appProp.getProperty("baseUri");
			return baseUri;
		}
	
	
	
	public static Response getUserData() {
	
		response=RestAssured.given()
		.when()
		.get(EndPoints.GET_DATA);
		return response;

	}
	public static Response getUserData(int id) {
		
		response=RestAssured.given()
		.when()
		.get(EndPoints.GET_DATA_OF +"/" +id);
		return response;

	}
	
	public static Response addUserData() {
		CreateUserPOJO data = ReusableMethods.newUserData();
		
		response=RestAssured.given()
		.contentType(ContentType.JSON)
		.body(data)
		.when()
		.post(EndPoints.ADD_DATA);
		return response;
		
	}
	
	public static CreatedUserResponsePOJO createdUserDetailsResponse() {
		
		
		response=addUserData();
		CreatedUserResponsePOJO data = response.as(CreatedUserResponsePOJO.class);
		return data;
		
	}

	
	public static Response deleteCreatedUser(int id) {
		
		DeleteUserResponsePOJO data = new DeleteUserResponsePOJO();
		
		response=RestAssured.given()
		.contentType(ContentType.JSON)
		.body(data)
		.when()
		.delete(EndPoints.DELETE_DATA + "/"+ id);
		
		return response;
	}

}

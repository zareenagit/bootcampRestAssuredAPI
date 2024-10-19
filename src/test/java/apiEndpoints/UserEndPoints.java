package apiEndpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import apiPayload.User;
import apiUtilities.PropertiesFile;

public class UserEndPoints {
	
	private static PropertiesFile config = new PropertiesFile();

	// Create user
	public static Response createUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.body(payload)
				.when()
				.post(Routes.POST_URL);

		return response;
	}

	// Get all users
	public static Response getAllUsers() {
		Response response = given()
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.get(Routes.GET_ALL_USERS_URL);
		return response;
	}

	// Get user by ID
	public static Response getUserById(String userId) {
		Response response = given()
				.pathParam("userId", userId)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.get(Routes.GET_BY_ID_URL);
		return response;
	}

	// Get user by first name
	public static Response getUserByFirstName(String firstName) {
		Response response = given()
				.pathParam("userFirstName", firstName)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.get(Routes.GET_BY_FIRST_NAME_URL);
		return response;
	}

	// Update user by stored ID
	public static Response updateUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.put(Routes.UPDATE_URL);
		return response;
	}

	// Delete user by ID
	public static Response deleteUserById(String userId) {
		Response response = given()
				.pathParam("userId", userId)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.delete(Routes.DELETE_BY_ID_URL);
		return response;
	}

	// Delete user by first name
	public static Response deleteUserByFirstName(String firstName) {
		Response response = given()
				.pathParam("userFirstName", firstName)
				.auth().preemptive().basic(config.getUsername(), config.getPassword())
				.when()
				.delete(Routes.DELETE_BY_FIRST_NAME_URL);
		return response;
	}

}

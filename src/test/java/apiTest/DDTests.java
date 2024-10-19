package apiTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import apiEndpoints.UserEndPoints;
import apiPayload.User;
import apiUtilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	 private String userId; 
    
    // Test for creating a user (POST)
    @Test(priority = 1, dataProvider = "PostData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String firstName, String lastName, String contactNumber, String emailId, String plotNumber, String street, String state, String country, String zipcode) {
        User userPayload = new User.Builder()
                .userFirstName(firstName)
                .userLastName(lastName)
                .userContactNumber(contactNumber)
                .userEmail(emailId) 
                .plotNumber(plotNumber)
                .street(street)
                .state(state)
                .country(country) 
                .zipCode(zipcode)
                .build();

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 201);

        // Extract the user ID from the response
        userId = response.jsonPath().getString("user_id");
        Assert.assertNotNull(userId, "User ID should not be null");
    }
    
    @Test(priority = 2)
    public void testGetAllUsers() {
        Response response = UserEndPoints.getAllUsers();
        response.then().log().all();  // Log the response for visibility
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 3)
    public void testGetUserById(String userId) {
        Response response = UserEndPoints.getUserById(userId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 4, dataProvider = "GetData", dataProviderClass = DataProviders.class)
    public void testGetUserByFirstName(String firstName) {
        Response response = UserEndPoints.getUserByFirstName(firstName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

   
    @Test(priority = 5, dataProvider = "PutData", dataProviderClass = DataProviders.class)
    public void testUpdateUser(String firstName, String lastName, String contactNumber, String emailId, String plotNumber, String street, String state, String country, String zipcode) {
        User userPayload = new User.Builder()
        		.userFirstName(firstName)
                .userLastName(lastName)
                .userContactNumber(contactNumber)
                .userEmail(emailId)
                .plotNumber(plotNumber)
                .street(street)
                .state(state)
                .country(country)
                .zipCode(zipcode)
                .build();

        Response response = UserEndPoints.updateUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    

    
    @Test(priority = 6)
    public void testDeleteUserById(String userId) {
        Response response = UserEndPoints.deleteUserById(userId);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
    
    @Test(priority = 7, dataProvider = "PostData", dataProviderClass = DataProviders.class)
    public void testRecreateUser(String firstName, String lastName, String contactNumber, String emailId, String plotNumber, String street, String state, String country, String zipcode) {
        User userPayload = new User.Builder()
                .userFirstName(firstName)
                .userLastName(lastName)
                .userContactNumber(contactNumber)
                .userEmail(emailId)
                .plotNumber(plotNumber)
                .street(street)
                .state(state)
                .country(country)
                .zipCode(zipcode)
                .build();

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 201);
    }
    
    @Test(priority = 8, dataProvider = "DeleteData", dataProviderClass = DataProviders.class)
    public void testDeleteUserByFirstName(String firstName) {
        Response response = UserEndPoints.deleteUserByFirstName(firstName);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}

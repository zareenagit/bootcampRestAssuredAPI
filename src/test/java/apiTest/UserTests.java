package apiTest;

import com.github.javafaker.Faker;
import apiEndpoints.UserEndPoints;
import apiPayload.User;
import apiUtilities.PropertiesFile;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    private Faker faker;
    private User userPayload;
    private String userId; 

    @BeforeClass
    public void setUpData() {
        faker = new Faker(); 
        userPayload = new User.Builder() 
                .userFirstName(faker.name().firstName())
                .userLastName(faker.name().lastName())
                .userContactNumber(faker.phoneNumber().phoneNumber())
                .userEmail(faker.internet().emailAddress()) 
                .plotNumber(faker.address().buildingNumber())
                .street(faker.address().streetName())
                .state(faker.address().state())
                .country(faker.address().country())
                .zipCode(faker.address().zipCode())
                .build(); // Build the User object
    }

    @Test(priority = 1)
    public void testCreateUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);

        // Extract the user ID from the response
        userId = response.jsonPath().getString("user_id");
        Assert.assertNotNull(userId, "User ID should not be null");
        System.out.println("Created User ID: " + userId);
    }

    @Test(priority = 2)
    public void testGetAllUsers() {
        Response response = UserEndPoints.getAllUsers();
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testGetUserById() {
        Assert.assertNotNull(userId, "User ID should not be null");
        Response response = UserEndPoints.getUserById(userId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200); 
    }

    @Test(priority = 4)
    public void testGetUserByFirstName() {
        Response response = UserEndPoints.getUserByFirstName(this.userPayload.getUserFirstName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 5)
    public void testUpdateUser() {
        // Create a new User instance for the update
        userPayload = new User.Builder() 
                .userLastName(faker.name().lastName()) // Update last name
                .build();

        Response response = UserEndPoints.updateUser(this.userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        Response updatedResponse = UserEndPoints.getUserById(userId);
        Assert.assertEquals(updatedResponse.jsonPath().getString("userLastName"), userPayload.getUserLastName());
    }

    @Test(priority = 6)
    public void testDeleteUserById() {
        Response response = UserEndPoints.deleteUserById(userId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204);

        Response fetchResponse = UserEndPoints.getUserById(userId);
        Assert.assertEquals(fetchResponse.getStatusCode(), 404, "Deleted user should not be found");
    }
 

    @Test(priority = 8)
    public void testDeleteUserByFirstName() {
        Response response = UserEndPoints.deleteUserByFirstName(this.userPayload.getUserFirstName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204); 

        Response fetchResponse = UserEndPoints.getUserById(userId);
        Assert.assertEquals(fetchResponse.getStatusCode(), 404, "Deleted user should not be found");
    }
}

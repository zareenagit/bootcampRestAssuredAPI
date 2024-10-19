package apiEndpoints;

public class Routes {

    public static final String BASE_URL = "https://userserviceapp-f5a54828541b.herokuapp.com/uap";

    public static final String POST_URL = BASE_URL + "/createusers"; // POST
    public static final String UPDATE_URL = BASE_URL + "/user/{userId}"; // PUT
    public static final String GET_ALL_USERS_URL = BASE_URL + "/users"; // GET
    public static final String GET_BY_ID_URL = BASE_URL + "/user/{userId}"; // GET
    public static final String GET_BY_FIRST_NAME_URL = BASE_URL + "/users/username/{userFirstName}"; // GET
    public static final String DELETE_BY_ID_URL = BASE_URL + "/deleteuser/{userId}"; // DELETE
    public static final String DELETE_BY_FIRST_NAME_URL = BASE_URL + "/deleteuser/username/{userFirstName}"; // DELETE
}

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Task11 {
    @Test
    //post scenario
    public void testPostCreate(){
        String baseUri = "https://reqres.in/";
        String postUser = "/api/users";
        String testBody = "{\n" +
                "   \"name\": \"Alex\",\n" +
                "   \"job\": \"leader\"\n" +
                "}";
        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(testBody)
                .log().all()
                .post(postUser)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
    }
    @Test
    //get scenario without 200 status code
    public void testGetUserNotFound(){
        given()
                .baseUri("https://reqres.in/")
                .pathParam("user_id", 23)
                .log().all()
                .get("api/users/{user_id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    @Test
    //get scenario with 200 status code
    public void testGetSingleUser(){
        given()
                .baseUri("https://reqres.in/")
                .pathParam("user_id", 2)
                .log().all()
                .get("api/users/{user_id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    //get scenario with 200 status code
    public void testGetListUsers(){
        given()
                .baseUri("https://reqres.in/")
                .pathParam("page", 2)
                .log().all()
                .get("api/users?page={page}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}

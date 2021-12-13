import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class ImgurApiTest extends BaseApiTest {

    public ImgurApiTest() throws IOException {}

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = getBaseUri();
    }

    @Test
    @DisplayName("get response info account")
    void testGetAccountBase() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .expect()
                .body("data.url", is(getUserName()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .when()
                .get("3/account/{username}", getUserName());

    }

    @Test
    @DisplayName("get response comment")
    void testGetComment() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .expect()
                .body("data.album_cover", is(getImageHash()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .when()
                .get("3/account/{username}/comment/{commentId}", getUserName(), getCommentId());
    }

    @Test
    @DisplayName("get response image")
    void testGetImage() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .expect()
                .body("data.account_url", contains(getUserName()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .when()
                .get("3/account/{username}/images/", getUserName());

    }
}

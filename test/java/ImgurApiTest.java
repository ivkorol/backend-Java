import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class ImgurApiTest extends BaseApiTest {

    public ImgurApiTest() throws IOException {}

    public ImgurApiTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = getBaseUri();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(parseInt(getStatusCode()))
                .expectStatusLine(getStatusLine())
                .expectResponseTime(Matchers.lessThan(parseLong(getResponseTime())))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addHeader(getHeaderAuthName(), getToken())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    @DisplayName("get response info account")
    void testGetAccountBase() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .spec(requestSpecification)
                .expect()
                .body("data.url", is(getUserName()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .body(getHeaderDataUrl(), is(getUserName()))
                .spec(responseSpecification)
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
                .spec(requestSpecification)
                .expect()
                .body("data.album_cover", is(getImageHash()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .body(getDataAlbumCover(), is(getImageHash()))
                .spec(responseSpecification)
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
                .spec(requestSpecification)
                .expect()
                .body("data.account_url", contains(getUserName()))
                .log()
                .all()
                .statusCode(200)
                .time(lessThan(4000L))
                .body(getHeaderDataAccUrl(), contains(getUserName()))
                .spec(responseSpecification)
                .when()
                .get("3/account/{username}/images/", getUserName());

    }
}
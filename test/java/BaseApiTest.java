import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.IOException;

abstract class BaseApiTest {
    private final String token;
    private final String baseUri;
    private final String userName;
    private final String commentId;
    private final String imageHash;
    private final String statusCode;
    private final String headerDataAccUrl;
    private final String headerDataUrl;
    private final String headerAuthName;
    private final String dataAlbumCover;
    private final String statusLine;
    private final String responseTime;
    private final PropertyScanner scanner;
    public ResponseSpecification responseSpecification = null;
    public RequestSpecification requestSpecification = null;


    public String getToken() {
        return token;
        @@ -21,10 +35,6 @@ public String getUserName() {
            return userName;
        }

        public PropertyScanner getScanner() {
            return scanner;
        }

        public String getCommentId() {
            return commentId;
        }
        @@ -33,12 +43,47 @@ public String getImageHash() {
            return imageHash;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getHeaderDataAccUrl() {
            return headerDataAccUrl;
        }

        public String getHeaderAuthName() {
            return headerAuthName;
        }

        public String getStatusLine() {
            return statusLine;
        }

        public String getResponseTime() {
            return responseTime;
        }

        public String getHeaderDataUrl() {
            return headerDataUrl;
        }

        public String getDataAlbumCover() {
            return dataAlbumCover;
        }

    public BaseApiTest() throws IOException {
            scanner = new PropertyScanner();
            token = scanner.getProperty("imgur.auth.token");;
            token = scanner.getProperty("imgur.auth.token");
            baseUri = scanner.getProperty("imgur.api.url");
            userName = scanner.getProperty("imgur.username");
            commentId = scanner.getProperty("imgur.commentId");
            imageHash = scanner.getProperty("imgur.imageHash");
            statusCode = scanner.getProperty("status.code");
            headerDataAccUrl = scanner.getProperty("header.query.param.data.account.url");
            headerAuthName = scanner.getProperty("header.auth.name");
            statusLine = scanner.getProperty("status.line");
            responseTime = scanner.getProperty("response.time");
            headerDataUrl = scanner.getProperty("header.query.param.data.url");
            dataAlbumCover = scanner.getProperty("header.data.album.cover");
        }
    }
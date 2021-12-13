import java.io.IOException;

abstract class BaseApiTest {

    private final String token;
    private final String baseUri;
    private final String userName;
    private final String commentId;
    private final String imageHash;
    private final PropertyScanner scanner;

    public String getToken() {
        return token;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public String getUserName() {
        return userName;
    }

    public PropertyScanner getScanner() {
        return scanner;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getImageHash() {
        return imageHash;
    }

    public BaseApiTest() throws IOException {
        scanner = new PropertyScanner();
        token = scanner.getProperty("imgur.auth.token");;
        baseUri = scanner.getProperty("imgur.api.url");
        userName = scanner.getProperty("imgur.username");
        commentId = scanner.getProperty("imgur.commentId");
        imageHash = scanner.getProperty("imgur.imageHash");
    }
}

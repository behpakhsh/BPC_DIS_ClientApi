package clientapi.bpc.bpc_dis_clientapi.Api;

public class BaseParamManager {
    public static final String authorization = "Authorization";
    public static final String contentType = "Content-Type";
    public static final String apiVersion = "api-version";
    public static final String apiKey = "Api-Key";
    public static String getBearer() {
        return "bearer";
    }
    public static String getContentType() {
        return "application/json; charset=utf-8";
    }
    public static String getRefreshToken() {
        return "refreshToken";
    }
}

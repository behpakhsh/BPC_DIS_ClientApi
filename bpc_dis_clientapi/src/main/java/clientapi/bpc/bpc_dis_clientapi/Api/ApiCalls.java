package clientapi.bpc.bpc_dis_clientapi.Api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;


public interface ApiCalls {
    @GET
    Call<JsonElement> get(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.authorization) String token, @Url String url);

    @GET
    Call<JsonElement> get(@Header(BaseParamManager.apiVersion) String apiVersion, @Url String url);


    @FormUrlEncoded
    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Url String url, @FieldMap HashMap<String, Object> hashFields);

    @FormUrlEncoded
    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.authorization) String token, @Url String url, @FieldMap HashMap<String, Object> hashFields);

    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Url String url, @Body JsonObject jsonFields);

    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Header(BaseParamManager.authorization) String token, @Url String url, @Body JsonObject jsonFields);


    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Url String url, @Body JsonArray jsonArray);

    @POST
    Call<JsonElement> post(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Header(BaseParamManager.authorization) String token, @Url String url, @Body JsonArray jsonArray);


    @FormUrlEncoded
    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Url String url, @FieldMap HashMap<String, Object> hashFields);

    @FormUrlEncoded
    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.authorization) String token, @Url String url, @FieldMap HashMap<String, Object> hashFields);

    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Url String url, @Body JsonObject jsonFields);

    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Header(BaseParamManager.authorization) String token, @Url String url, @Body JsonObject jsonFields);


    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Url String url, @Body JsonArray jsonArray);

    @PUT
    Call<JsonElement> put(@Header(BaseParamManager.apiVersion) String apiVersion, @Header(BaseParamManager.contentType) String contentType, @Header(BaseParamManager.authorization) String token, @Url String url, @Body JsonArray jsonArray);

    @GET
    Call<JsonElement> getSub(@Header(BaseParamManager.apiKey) String apiKey, @Url String url);

}

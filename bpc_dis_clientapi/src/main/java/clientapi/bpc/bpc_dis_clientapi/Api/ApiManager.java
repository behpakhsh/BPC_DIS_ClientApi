package clientapi.bpc.bpc_dis_clientapi.Api;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.squareup.moshi.Moshi;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import bpc.dis.utilities.StringUtilities.StringUtilities;
import clientapi.bpc.bpc_dis_clientapi.Api.Models.ServerResponse;
import clientapi.bpc.bpc_dis_clientapi.Api.Models.Token;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import serialization.bpc.bpc_dis_serialization.Gson.GSONManager;


public abstract class ApiManager {

    private static Retrofit retrofit = null;
    private IAuthorizationManager authorizationManager;
    private ITokenManager tokenManger;
    private IApiVersionManager apiVersionManger;
    private IUrlManager urlManager;
    private Context context;

    public ApiManager(Context context) {
        this.context = context;
        apiVersionManger = getApiVersionManger();
        tokenManger = getTokenManager();
        urlManager = getUrlManager();
        authorizationManager = getAuthorizationManager();
    }

    public abstract IApiVersionManager getApiVersionManger();

    public abstract ITokenManager getTokenManager();

    public abstract IUrlManager getUrlManager();

    public abstract int getTimeOut();

    public abstract IAuthorizationManager getAuthorizationManager();

    public void get(String url, ApiConfig apiConfig, IApiRequestListener listener) {
        try {
            listener.onStart();
            String apiVersion = apiVersionManger.getApiVersion(url);
            StringBuilder absoluteUrl = new StringBuilder();
            absoluteUrl.append(url);
            HashMap<String, Object> urlParams = apiConfig.getParams();
            if (urlParams != null && urlParams.size() > 0) {
                urlParams.size();
                apiConfig.setParams(convertParamsToEnglish(urlParams));
                absoluteUrl.append("?");
                for (Map.Entry<String, Object> entry : urlParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue().toString();
                    absoluteUrl.append(key).append("=").append(value).append("&");
                }
                absoluteUrl.deleteCharAt(absoluteUrl.length() - 1);
            }

            String token;
            if (apiConfig.isUseSub()) {
                token = tokenManger.getSubToken(context);
                (getClient(apiConfig.isUseSub()).getSub(token, url)).enqueue(getCallback(RequestMethod.GET, url, apiConfig, listener));
            } else {
                token = tokenManger.getTokenWithBearer(context);
                if (!StringUtilities.isNullOrEmpty(token)) {
                    (getClient(apiConfig.isUseSub()).get(apiVersion, token, absoluteUrl.toString())).enqueue(getCallback(RequestMethod.GET, url, apiConfig, listener));
                } else {
                    (getClient(apiConfig.isUseSub()).get(apiVersion, absoluteUrl.toString())).enqueue(getCallback(RequestMethod.GET, url, apiConfig, listener));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void post(String url, ApiConfig apiConfig, IApiRequestListener listener) {
        listener.onStart();
        String apiVersion = apiVersionManger.getApiVersion(url);
        HashMap urlParams = apiConfig.getParams();
        if (urlParams != null)
            if (urlParams.size() > 0)
                apiConfig.setParams(convertParamsToEnglish(urlParams));
        String token;
        if (apiConfig.isUseSub()) {
            token = tokenManger.getSubToken(context);
            (getClient(apiConfig.isUseSub()).getSub(token, url)).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
        } else {
            token = tokenManger.getTokenWithBearer(context);
            if (!StringUtilities.isNullOrEmpty(token)) {
                if (apiConfig.getJsonArrays() != null) {
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), token, url, apiConfig.getJsonArrays())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                } else if (apiConfig.getJsonParams() != null)
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), token, url, apiConfig.getJsonParams())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));

                else
                    (getClient(apiConfig.isUseSub()).put(apiVersion, token, url, urlParams)).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
            } else {
                if (apiConfig.getJsonArrays() != null) {
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), url, apiConfig.getJsonArrays())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                } else if (apiConfig.getJsonParams() != null)
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), url, apiConfig.getJsonParams())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                else
                    (getClient(apiConfig.isUseSub()).put(apiVersion, url, urlParams)).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));

            }
        }
    }

    public void put(String url, ApiConfig apiConfig, IApiRequestListener listener) {
        listener.onStart();
        String apiVersion = apiVersionManger.getApiVersion(url);
        HashMap urlParams = apiConfig.getParams();
        if (urlParams != null)
            if (urlParams.size() > 0)
                apiConfig.setParams(convertParamsToEnglish(urlParams));
        String token;
        if (apiConfig.isUseSub()) {
            token = tokenManger.getSubToken(context);
            (getClient(apiConfig.isUseSub()).getSub(token, url)).enqueue(getCallback(RequestMethod.PUT, url, apiConfig, listener));
        } else {
            token = tokenManger.getTokenWithBearer(context);
            if (!StringUtilities.isNullOrEmpty(token)) {
                if (apiConfig.getJsonArrays() != null) {
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), token, url, apiConfig.getJsonArrays())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                } else if (apiConfig.getJsonParams() != null)
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), token, url, apiConfig.getJsonParams())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));

                else
                    (getClient(apiConfig.isUseSub()).put(apiVersion, token, url, urlParams)).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
            } else {
                if (apiConfig.getJsonArrays() != null) {
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), url, apiConfig.getJsonArrays())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                } else if (apiConfig.getJsonParams() != null)
                    (getClient(apiConfig.isUseSub()).put(apiVersion, BaseParamManager.getContentType(), url, apiConfig.getJsonParams())).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));
                else
                    (getClient(apiConfig.isUseSub()).put(apiVersion, url, urlParams)).enqueue(getCallback(RequestMethod.POST, url, apiConfig, listener));

            }
        }
    }


    @SuppressWarnings("unchecked")
    private ApiCalls getClient(boolean useSubBaseUrl) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(getTimeOut(), TimeUnit.SECONDS)
                    .readTimeout(getTimeOut(), TimeUnit.SECONDS)
                    .writeTimeout(getTimeOut(), TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Moshi moshi = new Moshi.Builder().build();
            String baseUrl = urlManager.getBaseUrl();
            if (useSubBaseUrl) {
                baseUrl = urlManager.getSubBaseUrl();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(MoshiConverterFactory.create(moshi))//todo -> test this
                    .build();
            return retrofit.create(ApiCalls.class);
        } catch (Exception ex) {
            return null;
        }

    }

    public HashMap<String, Object> convertParamsToEnglish(HashMap<String, Object> urlParams) {
        HashMap<String, Object> params = new HashMap<>();
        if (urlParams != null && urlParams.size() > 0) {
            for (Map.Entry<String, Object> entry : urlParams.entrySet()) {
                String key = entry.getKey();
                Object value = StringUtilities.convertPersianNumberToEnglishNumber(entry.getValue().toString());
                params.put(key, value);
            }
        }
        return params;
    }

    private <T> Callback getCallback(final RequestMethod requestMethod, final String url, final ApiConfig config, final IApiRequestListener<T> listener) {

        return new Callback<JsonElement>() {

            @Override
            public void onResponse(final Call<JsonElement> call, Response<JsonElement> response) {

                int responseCode = response.code();
                if (responseCode == 401) {
                    manageRefreshToken();
                    return;
                }
                if (responseCode != 200) {
                    listener.onFailure();
                    listener.onFinish();
                    return;
                }

                if (config.getResponseTyp() == null) {
                    config.setResponseTyp(ServerResponse.class);
                }
                T objects = (T) GSONManager.fromJson(response.body().getAsJsonObject().toString(), config.getResponseTyp());
                listener.onSuccess(objects);
                listener.onFinish();
            }

            private void manageRefreshToken() {
                String refreshToken = tokenManger.getRefreshToken(context);
                if (StringUtilities.isNullOrEmpty(refreshToken)) {
                    onRefreshTokenFailure();
                    return;
                }
                ApiConfig tokenConfig = new ApiConfig();
                HashMap<String, Object> params = new HashMap<>();
                params.put(BaseParamManager.getRefreshToken(), refreshToken);
                tokenConfig.setParams(params);
                get(urlManager.getRefreshTokenUrl(), tokenConfig, new IApiRequestListener<ServerResponse>() {
                    @Override
                    public void onSuccess(ServerResponse response) {
                        if (response.getResult() != null && response.getResult().isValid() == false) {
                            onRefreshTokenFailure();
                            return;
                        }
                        Token token = GSONManager.fromJson(response.getData(), Token.class);
                        if (!TextUtils.isEmpty(token.getAccessToken())) {
                            tokenManger.setToken(context, token.getAccessToken());
                            if (!TextUtils.isEmpty(token.getRefreshToken())) {
                                tokenManger.setRefreshToken(context, token.getRefreshToken());
                            }
                        }
                        switch (requestMethod) {
                            case GET:
                                get(url, config, listener);
                                break;
                            case POST:
                                post(url, config, listener);
                                break;
                            case PUT:
                                put(url, config, listener);
                                break;
                        }
                    }

                    @Override
                    public void onFailure() {
                        onRefreshTokenFailure();
                        return;
                    }

                    @Override
                    public void onFailure(ApiRequestExceptionType requestExceptionType) {
                        onRefreshTokenFailure();
                        return;
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onStart() {

                    }

                });
                return;
            }

            private void onRefreshTokenFailure() {
                authorizationManager.onUnauthorized(context);
                listener.onFailure();
                listener.onFinish();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                ApiRequestExceptionType exceptionType = ApiRequestExceptionType.Default;

                if (t instanceof HttpException) {
                    exceptionType = ApiRequestExceptionType.InternalServer;
                }

                if (t instanceof NetworkErrorException)
                    exceptionType = ApiRequestExceptionType.Network;

                if (t instanceof TimeoutException || t instanceof SocketTimeoutException)
                    exceptionType = ApiRequestExceptionType.TimeOut;

                listener.onFailure(exceptionType);
                listener.onFinish();
            }

        };

    }

}
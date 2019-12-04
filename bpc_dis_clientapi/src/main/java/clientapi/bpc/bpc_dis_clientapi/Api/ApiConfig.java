package clientapi.bpc.bpc_dis_clientapi.Api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class ApiConfig {

    private IParallelRequestListener parallelSuccessListener;

    public ApiConfig(){
        //responseTyp = ServerResponse.class;
        jsonParams = null;
        jsonArrays = null;
    }
    private String url;
    private HashMap<String, Object> params;
    private JsonObject jsonParams ;
    private JsonArray jsonArrays ;
    private Class responseTyp;
    private RequestMethod requestMethod;
    private String failureMessage;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public JsonObject getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(JsonObject jsonParams) {
        this.jsonParams = jsonParams;
    }

    public Class getResponseTyp() {
        return responseTyp;
    }

    public void setResponseTyp(Class responseTyp) {
        this.responseTyp = responseTyp;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public JsonArray getJsonArrays() {
        return jsonArrays;
    }

    public void setJsonArrays(JsonArray jsonArrays) {
        this.jsonArrays = jsonArrays;
    }


    public IParallelRequestListener getParallelSuccessListener() {
        return parallelSuccessListener;
    }

    public void setParallelSuccessListener(IParallelRequestListener parallelSuccessListener) {
        this.parallelSuccessListener = parallelSuccessListener;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }
}

package clientapi.bpc.bpc_dis_clientapi.Api.Models;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    private String data;
    private ValidationResult result;

    public ServerResponse() {

    }

    public ServerResponse(ValidationResult result, String data) {
        this.result = result;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ValidationResult getResult() {
        return result;
    }

    public void setResult(ValidationResult result) {
        this.result = result;
    }
}

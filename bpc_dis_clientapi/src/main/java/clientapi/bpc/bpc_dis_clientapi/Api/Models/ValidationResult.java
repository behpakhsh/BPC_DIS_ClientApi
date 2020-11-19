package clientapi.bpc.bpc_dis_clientapi.Api.Models;

import com.google.gson.annotations.SerializedName;

public class ValidationResult {
    private boolean isValid;
    private String validationMessage;

    public ValidationResult(boolean isValid, String validationMessage) {
        this.isValid = isValid;
        this.validationMessage = validationMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}

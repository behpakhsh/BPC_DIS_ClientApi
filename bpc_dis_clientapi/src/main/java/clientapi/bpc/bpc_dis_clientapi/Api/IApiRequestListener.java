package clientapi.bpc.bpc_dis_clientapi.Api;

public interface IApiRequestListener<T> {
    void onSuccess(T response);
    void onFailure();
    void onFailure(ApiRequestExceptionType requestExceptionType);
    void onFinish();
    void onStart();
}

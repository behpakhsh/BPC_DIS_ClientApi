package clientapi.bpc.bpc_dis_clientapi.Api;

public interface IParallelRequestListener<T> {
    void onSuccess(T response);
}

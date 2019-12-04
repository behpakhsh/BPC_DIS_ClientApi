package clientapi.bpc.bpc_dis_clientapi.Api;

public interface IPageOnlineStateManager {
    PageOnlineStateDetail getConfigDetail(String actUrl);
    PageOnlineStateDetail getConfigDetail(Class actClass);
}

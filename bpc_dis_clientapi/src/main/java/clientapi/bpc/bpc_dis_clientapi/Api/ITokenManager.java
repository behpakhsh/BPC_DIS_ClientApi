package clientapi.bpc.bpc_dis_clientapi.Api;

import android.content.Context;

public interface ITokenManager {
    String getToken(Context context);
    String getTokenWithBearer(Context context);
    String getRefreshToken(Context context);
    void setToken(Context context, String token);
    void setRefreshToken(Context context, String refreshToken);
}

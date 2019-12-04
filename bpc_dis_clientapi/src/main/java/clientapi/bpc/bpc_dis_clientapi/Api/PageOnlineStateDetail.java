package clientapi.bpc.bpc_dis_clientapi.Api;
import java.io.Serializable;

public class PageOnlineStateDetail implements Serializable {

    public boolean isOnline;
    public boolean loadDataOnline;
    public boolean isOnlineIfEmpty;

    public PageOnlineStateDetail(boolean isOnline, boolean loadDataOnline, boolean isOnlineIfEmpty) {
        this.isOnline = isOnline;
        this.loadDataOnline = loadDataOnline;
        this.isOnlineIfEmpty = isOnlineIfEmpty;
    }

}

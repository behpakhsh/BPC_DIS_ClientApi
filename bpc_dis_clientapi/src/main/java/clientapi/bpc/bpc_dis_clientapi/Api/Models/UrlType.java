package clientapi.bpc.bpc_dis_clientapi.Api.Models;

public enum UrlType {
    GET(0),
    POST(1),
    PUT(2);

    private final int value;

    UrlType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

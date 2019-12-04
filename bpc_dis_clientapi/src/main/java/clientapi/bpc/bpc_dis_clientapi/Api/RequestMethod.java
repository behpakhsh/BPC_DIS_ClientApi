package clientapi.bpc.bpc_dis_clientapi.Api;

public enum  RequestMethod {
    GET(0),
    POST(1),
    PUT(2);

    private final int value;

    RequestMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

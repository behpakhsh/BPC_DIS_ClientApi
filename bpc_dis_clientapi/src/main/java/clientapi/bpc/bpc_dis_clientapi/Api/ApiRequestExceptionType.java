package clientapi.bpc.bpc_dis_clientapi.Api;

public enum ApiRequestExceptionType {
    Default(0),
    Network(1),
    TimeOut(2),
    InternalServer(3);

    private final int value;

    ApiRequestExceptionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

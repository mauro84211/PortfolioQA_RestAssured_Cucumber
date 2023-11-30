package dataModel;

public enum Status {
    available("available"),
    pending("pending"),
    sold("sold");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}

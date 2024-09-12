package com.rest.book.Utils;

public enum Cons {

    RECORD_SAVE_SUCCESSFULLY("RECORD_SAVE", "RECORD SAVE SUCCESSFULLY", 200),
    RECORD_NOT_SAVE("RECORD_NOT_SAVE", "FAILED TO SAVE RECORD", 500);

    Cons(String name, String value, int code) {
        this.name = name;
        this.value = value;
        this.code = code;
    }

    private String name;
    private String value;
    private int code;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

}

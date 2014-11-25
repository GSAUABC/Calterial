package com.zurce.calterial.database;

public class Result {

    private long id;
    private String result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String r) {
        this.result = r;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return result;
    }

}
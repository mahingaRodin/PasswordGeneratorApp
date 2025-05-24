package com.securePassword.secPassGen.configuration;

public interface Configuration {
    void load(String[] args);
    String getValue(String key);
    int getValueAsInt(String key);
    boolean validate();
}

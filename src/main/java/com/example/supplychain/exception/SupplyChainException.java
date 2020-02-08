package com.example.supplychain.exception;

import java.util.Map;

public class SupplyChainException extends Exception {
    private Map<String, Object> properties;

    public SupplyChainException(String s) {
        super();
    }

    public SupplyChainException(String message, Throwable error) {
        super(message, error);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}

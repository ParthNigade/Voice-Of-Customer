package com.client.client.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class content {
     @JsonProperty("systeminstruction")
    private String systeminstruction;

    @JsonProperty("otherField")
    private String otherField;

    // Constructors
    public content() {}

    public content(String systeminstruction, String otherField) {
        this.systeminstruction = systeminstruction;
        this.otherField = otherField;
    }

    // Getters and Setters
    public String getSystemInstruction() {
        return systeminstruction;
    }

    public void setSystemInstruction(String systeminstruction) {
        this.systeminstruction = systeminstruction;
    }

    public String getOtherField() {
        return otherField;
    }

    public void setOtherField(String otherField) {
        this.otherField = otherField;
    }
    
}

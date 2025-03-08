package com.ach;

import java.util.regex.Pattern;

public class Addenda98 {
    private String id;
    private String typeCode;
    private String changeCode;
    private String originalTrace;
    private String originalDFI;
    private String correctedData;
    private String traceNumber;

    public Addenda98() {
        this.typeCode = "98";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.changeCode = record.substring(3, 6).trim();
        this.originalTrace = record.substring(6, 21).trim();
        this.originalDFI = record.substring(27, 35).trim();
        this.correctedData = record.substring(35, 64).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        return String.format("798%-3s%-15s      %-8s%-29s               %-15s",
                changeCode, originalTrace, originalDFI, correctedData, traceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("98")) {
            return false;
        }
        if (changeCode == null || changeCode.isEmpty()) {
            return false;
        }
        if (correctedData == null || correctedData.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

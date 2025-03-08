package com.ach;

import java.util.regex.Pattern;

public class Addenda99 {
    private String id;
    private String typeCode;
    private String returnCode;
    private String originalTrace;
    private String dateOfDeath;
    private String originalDFI;
    private String addendaInformation;
    private String traceNumber;

    public Addenda99() {
        this.typeCode = "99";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.returnCode = record.substring(3, 6).trim();
        this.originalTrace = record.substring(6, 21).trim();
        this.dateOfDeath = record.substring(21, 27).trim();
        this.originalDFI = record.substring(27, 35).trim();
        this.addendaInformation = record.substring(35, 79).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        return String.format("799%-3s%-15s%-6s%-8s%-44s%-15s",
                returnCode, originalTrace, dateOfDeath, originalDFI, addendaInformation, traceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("99")) {
            return false;
        }
        if (returnCode == null || returnCode.isEmpty()) {
            return false;
        }
        if (originalTrace == null || originalTrace.isEmpty()) {
            return false;
        }
        if (originalDFI == null || originalDFI.isEmpty()) {
            return false;
        }
        if (traceNumber == null || traceNumber.isEmpty()) {
            return false;
        }
        if (!isAlphanumeric(returnCode) || !isAlphanumeric(originalTrace) ||
                !isAlphanumeric(dateOfDeath) || !isAlphanumeric(originalDFI) ||
                !isAlphanumeric(addendaInformation) || !isAlphanumeric(traceNumber)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

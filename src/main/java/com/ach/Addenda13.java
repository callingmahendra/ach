package com.ach;

import java.util.regex.Pattern;

public class Addenda13 {
    private String id;
    private String typeCode;
    private String ODFIName;
    private String ODFIIDNumberQualifier;
    private String ODFIIdentification;
    private String ODFIBranchCountryCode;
    private int entryDetailSequenceNumber;

    public Addenda13() {
        this.typeCode = "13";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.ODFIName = record.substring(3, 38).trim();
        this.ODFIIDNumberQualifier = record.substring(38, 40).trim();
        this.ODFIIdentification = record.substring(40, 74).trim();
        this.ODFIBranchCountryCode = record.substring(74, 77).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("713%-35s%-2s%-34s%-3s          %07d",
                ODFIName, ODFIIDNumberQualifier, ODFIIdentification, ODFIBranchCountryCode, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("13")) {
            return false;
        }
        if (ODFIName == null || ODFIName.isEmpty()) {
            return false;
        }
        if (ODFIIDNumberQualifier == null || ODFIIDNumberQualifier.isEmpty()) {
            return false;
        }
        if (ODFIIdentification == null || ODFIIdentification.isEmpty()) {
            return false;
        }
        if (ODFIBranchCountryCode == null || ODFIBranchCountryCode.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isAlphanumeric(ODFIName) || !isIDNumberQualifier(ODFIIDNumberQualifier) ||
                !isAlphanumeric(ODFIIdentification) || !isAlphanumeric(ODFIBranchCountryCode)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }

    private boolean isIDNumberQualifier(String value) {
        String[] validQualifiers = {"01", "02", "03"};
        for (String qualifier : validQualifiers) {
            if (qualifier.equals(value)) {
                return true;
            }
        }
        return false;
    }
}

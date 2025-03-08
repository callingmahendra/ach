package com.ach;

import java.util.regex.Pattern;

public class Addenda14 {
    private String id;
    private String typeCode;
    private String RDFIName;
    private String RDFIIDNumberQualifier;
    private String RDFIIdentification;
    private String RDFIBranchCountryCode;
    private int entryDetailSequenceNumber;

    public Addenda14() {
        this.typeCode = "14";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.RDFIName = record.substring(3, 38).trim();
        this.RDFIIDNumberQualifier = record.substring(38, 40).trim();
        this.RDFIIdentification = record.substring(40, 74).trim();
        this.RDFIBranchCountryCode = record.substring(74, 77).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("714%-35s%-2s%-34s%-3s          %07d",
                RDFIName, RDFIIDNumberQualifier, RDFIIdentification, RDFIBranchCountryCode, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("14")) {
            return false;
        }
        if (RDFIName == null || RDFIName.isEmpty()) {
            return false;
        }
        if (RDFIIDNumberQualifier == null || RDFIIDNumberQualifier.isEmpty()) {
            return false;
        }
        if (RDFIIdentification == null || RDFIIdentification.isEmpty()) {
            return false;
        }
        if (RDFIBranchCountryCode == null || RDFIBranchCountryCode.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isIDNumberQualifier(RDFIIDNumberQualifier)) {
            return false;
        }
        if (!isAlphanumeric(RDFIName) || !isAlphanumeric(RDFIIdentification) || !isAlphanumeric(RDFIBranchCountryCode)) {
            return false;
        }
        return true;
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

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

package com.ach;

import java.util.regex.Pattern;

public class Addenda18 {
    private String id;
    private String typeCode;
    private String foreignCorrespondentBankName;
    private String foreignCorrespondentBankIDNumberQualifier;
    private String foreignCorrespondentBankIDNumber;
    private String foreignCorrespondentBankBranchCountryCode;
    private int sequenceNumber;
    private int entryDetailSequenceNumber;

    public Addenda18() {
        this.typeCode = "18";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.foreignCorrespondentBankName = record.substring(3, 38).trim();
        this.foreignCorrespondentBankIDNumberQualifier = record.substring(38, 40).trim();
        this.foreignCorrespondentBankIDNumber = record.substring(40, 74).trim();
        this.foreignCorrespondentBankBranchCountryCode = record.substring(74, 77).trim();
        this.sequenceNumber = Integer.parseInt(record.substring(83, 87).trim());
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("718%-35s%-2s%-34s%-3s      %04d%07d",
                foreignCorrespondentBankName, foreignCorrespondentBankIDNumberQualifier,
                foreignCorrespondentBankIDNumber, foreignCorrespondentBankBranchCountryCode,
                sequenceNumber, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("18")) {
            return false;
        }
        if (foreignCorrespondentBankName == null || foreignCorrespondentBankName.isEmpty()) {
            return false;
        }
        if (foreignCorrespondentBankIDNumberQualifier == null || foreignCorrespondentBankIDNumberQualifier.isEmpty()) {
            return false;
        }
        if (foreignCorrespondentBankIDNumber == null || foreignCorrespondentBankIDNumber.isEmpty()) {
            return false;
        }
        if (foreignCorrespondentBankBranchCountryCode == null || foreignCorrespondentBankBranchCountryCode.isEmpty()) {
            return false;
        }
        if (sequenceNumber == 0) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isAlphanumeric(foreignCorrespondentBankName) || !isAlphanumeric(foreignCorrespondentBankIDNumberQualifier) ||
                !isAlphanumeric(foreignCorrespondentBankIDNumber) || !isAlphanumeric(foreignCorrespondentBankBranchCountryCode)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

package com.ach;

import java.util.regex.Pattern;

public class Addenda11 {
    private String id;
    private String typeCode;
    private String originatorName;
    private String originatorStreetAddress;
    private int entryDetailSequenceNumber;

    public Addenda11() {
        this.typeCode = "11";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getOriginatorName() {
        return originatorName;
    }

    public void setOriginatorName(String originatorName) {
        this.originatorName = originatorName;
    }

    public String getOriginatorStreetAddress() {
        return originatorStreetAddress;
    }

    public void setOriginatorStreetAddress(String originatorStreetAddress) {
        this.originatorStreetAddress = originatorStreetAddress;
    }

    public int getEntryDetailSequenceNumber() {
        return entryDetailSequenceNumber;
    }

    public void setEntryDetailSequenceNumber(int entryDetailSequenceNumber) {
        this.entryDetailSequenceNumber = entryDetailSequenceNumber;
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.originatorName = record.substring(3, 38).trim();
        this.originatorStreetAddress = record.substring(38, 73).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("7%-2s%-35s%-35s%-7s", typeCode, originatorName, originatorStreetAddress, String.format("%07d", entryDetailSequenceNumber));
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("11")) {
            return false;
        }
        if (originatorName == null || originatorName.isEmpty() || !isAlphanumeric(originatorName)) {
            return false;
        }
        if (originatorStreetAddress == null || originatorStreetAddress.isEmpty() || !isAlphanumeric(originatorStreetAddress)) {
            return false;
        }
        return entryDetailSequenceNumber > 0;
    }

    private boolean isAlphanumeric(String value) {
        return Pattern.matches("^[a-zA-Z0-9 ]*$", value);
    }
}

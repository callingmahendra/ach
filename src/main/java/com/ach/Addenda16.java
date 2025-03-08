package com.ach;

import java.util.regex.Pattern;

public class Addenda16 {
    private String id;
    private String typeCode;
    private String receiverCityStateProvince;
    private String receiverCountryPostalCode;
    private int entryDetailSequenceNumber;

    public Addenda16() {
        this.typeCode = "16";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.receiverCityStateProvince = record.substring(3, 38).trim();
        this.receiverCountryPostalCode = record.substring(38, 73).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("716%-35s%-35s              %07d",
                receiverCityStateProvince, receiverCountryPostalCode, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("16")) {
            return false;
        }
        if (receiverCityStateProvince == null || receiverCityStateProvince.isEmpty()) {
            return false;
        }
        if (receiverCountryPostalCode == null || receiverCountryPostalCode.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isAlphanumeric(receiverCityStateProvince) || !isAlphanumeric(receiverCountryPostalCode)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

package com.ach;

import java.util.regex.Pattern;

public class Addenda12 {
    private String id;
    private String typeCode;
    private String originatorCityStateProvince;
    private String originatorCountryPostalCode;
    private int entryDetailSequenceNumber;

    public Addenda12() {
        this.typeCode = "12";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.originatorCityStateProvince = record.substring(3, 38).trim();
        this.originatorCountryPostalCode = record.substring(38, 73).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("712%-35s%-35s              %07d",
                originatorCityStateProvince, originatorCountryPostalCode, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("12")) {
            return false;
        }
        if (originatorCityStateProvince == null || originatorCityStateProvince.isEmpty()) {
            return false;
        }
        if (originatorCountryPostalCode == null || originatorCountryPostalCode.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isAlphanumeric(originatorCityStateProvince) || !isAlphanumeric(originatorCountryPostalCode)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

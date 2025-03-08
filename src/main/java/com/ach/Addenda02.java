package com.ach;

import java.util.regex.Pattern;

public class Addenda02 {
    private String id;
    private String typeCode;
    private String referenceInformationOne;
    private String referenceInformationTwo;
    private String terminalIdentificationCode;
    private String transactionSerialNumber;
    private String transactionDate;
    private String authorizationCodeOrExpireDate;
    private String terminalLocation;
    private String terminalCity;
    private String terminalState;
    private String traceNumber;

    public Addenda02() {
        this.typeCode = "02";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.referenceInformationOne = record.substring(3, 10).trim();
        this.referenceInformationTwo = record.substring(10, 13).trim();
        this.terminalIdentificationCode = record.substring(13, 19).trim();
        this.transactionSerialNumber = record.substring(19, 25).trim();
        this.transactionDate = record.substring(25, 29).trim();
        this.authorizationCodeOrExpireDate = record.substring(29, 35).trim();
        this.terminalLocation = record.substring(35, 62).trim();
        this.terminalCity = record.substring(62, 77).trim();
        this.terminalState = record.substring(77, 79).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        return String.format("702%-7s%-3s%-6s%-6s%-4s%-6s%-27s%-15s%-2s%-15s",
                referenceInformationOne, referenceInformationTwo, terminalIdentificationCode,
                transactionSerialNumber, transactionDate, authorizationCodeOrExpireDate,
                terminalLocation, terminalCity, terminalState, traceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("02")) {
            return false;
        }
        if (transactionSerialNumber == null || transactionSerialNumber.isEmpty()) {
            return false;
        }
        if (transactionDate == null || transactionDate.isEmpty()) {
            return false;
        }
        if (terminalLocation == null || terminalLocation.isEmpty()) {
            return false;
        }
        if (terminalCity == null || terminalCity.isEmpty()) {
            return false;
        }
        if (terminalState == null || terminalState.isEmpty()) {
            return false;
        }
        if (!isAlphanumeric(referenceInformationOne) || !isAlphanumeric(referenceInformationTwo) ||
                !isAlphanumeric(terminalIdentificationCode) || !isAlphanumeric(transactionSerialNumber) ||
                !isAlphanumeric(authorizationCodeOrExpireDate) || !isAlphanumeric(terminalLocation) ||
                !isAlphanumeric(terminalCity) || !isAlphanumeric(terminalState)) {
            return false;
        }
        if (!isValidDate(transactionDate)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }

    private boolean isValidDate(String date) {
        if (date.length() != 4) {
            return false;
        }
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(2, 4));
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        if (month == 2 && day > 29) {
            return false;
        }
        return true;
    }
}

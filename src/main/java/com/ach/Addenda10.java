package com.ach;

import java.util.regex.Pattern;

public class Addenda10 {
    private String id;
    private String typeCode;
    private String transactionTypeCode;
    private int foreignPaymentAmount;
    private String foreignTraceNumber;
    private String name;
    private int entryDetailSequenceNumber;

    public Addenda10() {
        this.typeCode = "10";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.transactionTypeCode = record.substring(3, 6).trim();
        this.foreignPaymentAmount = Integer.parseInt(record.substring(6, 24).trim());
        this.foreignTraceNumber = record.substring(24, 46).trim();
        this.name = record.substring(46, 81).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("710%-3s%018d%-22s%-35s      %07d",
                transactionTypeCode, foreignPaymentAmount, foreignTraceNumber, name, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("10")) {
            return false;
        }
        if (transactionTypeCode == null || transactionTypeCode.isEmpty()) {
            return false;
        }
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isTransactionTypeCode(transactionTypeCode)) {
            return false;
        }
        if (!isAlphanumeric(foreignTraceNumber) || !isAlphanumeric(name)) {
            return false;
        }
        return true;
    }

    private boolean isTransactionTypeCode(String value) {
        String[] validCodes = {"ANN", "BUS", "DEP", "LOA", "MIS", "MOR", "PEN", "RLS", "REM", "SAL", "TAX", "TEL", "WEB", "ARC", "BOC", "POP", "RCK"};
        for (String code : validCodes) {
            if (code.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

package com.ach;

import java.util.regex.Pattern;

public class Addenda17 {
    private String id;
    private String typeCode;
    private String paymentRelatedInformation;
    private int sequenceNumber;
    private int entryDetailSequenceNumber;

    public Addenda17() {
        this.typeCode = "17";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.paymentRelatedInformation = record.substring(3, 83).trim();
        this.sequenceNumber = Integer.parseInt(record.substring(83, 87).trim());
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        return String.format("717%-80s%04d%07d",
                paymentRelatedInformation, sequenceNumber, entryDetailSequenceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("17")) {
            return false;
        }
        if (sequenceNumber == 0) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        if (!isAlphanumeric(paymentRelatedInformation)) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

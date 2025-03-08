package com.ach;

import java.util.regex.Pattern;

public class Addenda99Dishonored {
    private String id;
    private String typeCode;
    private String dishonoredReturnReasonCode;
    private String originalEntryTraceNumber;
    private String originalReceivingDFIIdentification;
    private String returnTraceNumber;
    private String returnSettlementDate;
    private String returnReasonCode;
    private String addendaInformation;
    private String traceNumber;

    public Addenda99Dishonored() {
        this.typeCode = "99";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.dishonoredReturnReasonCode = record.substring(3, 6).trim();
        this.originalEntryTraceNumber = record.substring(6, 21).trim();
        this.originalReceivingDFIIdentification = record.substring(27, 35).trim();
        this.returnTraceNumber = record.substring(35, 53).trim();
        this.returnSettlementDate = record.substring(53, 56).trim();
        this.returnReasonCode = record.substring(56, 58).trim();
        this.addendaInformation = record.substring(58, 79).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        return String.format("799%-3s%-15s      %-8s%-15s%-3s%-2s%-21s%-15s",
                dishonoredReturnReasonCode, originalEntryTraceNumber, originalReceivingDFIIdentification,
                returnTraceNumber, returnSettlementDate, returnReasonCode, addendaInformation, traceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("99")) {
            return false;
        }
        if (dishonoredReturnReasonCode == null || dishonoredReturnReasonCode.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

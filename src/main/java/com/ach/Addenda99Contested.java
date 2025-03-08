package com.ach;

import java.util.regex.Pattern;

public class Addenda99Contested {
    private String id;
    private String typeCode;
    private String contestedReturnCode;
    private String originalEntryTraceNumber;
    private String dateOriginalEntryReturned;
    private String originalReceivingDFIIdentification;
    private String originalSettlementDate;
    private String returnTraceNumber;
    private String returnSettlementDate;
    private String returnReasonCode;
    private String dishonoredReturnTraceNumber;
    private String dishonoredReturnSettlementDate;
    private String dishonoredReturnReasonCode;
    private String traceNumber;

    public Addenda99Contested() {
        this.typeCode = "99";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.contestedReturnCode = record.substring(3, 6).trim();
        this.originalEntryTraceNumber = record.substring(6, 21).trim();
        this.dateOriginalEntryReturned = record.substring(21, 27).trim();
        this.originalReceivingDFIIdentification = record.substring(27, 35).trim();
        this.originalSettlementDate = record.substring(35, 38).trim();
        this.returnTraceNumber = record.substring(38, 53).trim();
        this.returnSettlementDate = record.substring(53, 56).trim();
        this.returnReasonCode = record.substring(56, 58).trim();
        this.dishonoredReturnTraceNumber = record.substring(58, 73).trim();
        this.dishonoredReturnSettlementDate = record.substring(73, 76).trim();
        this.dishonoredReturnReasonCode = record.substring(76, 78).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        return String.format("799%-3s%-15s%-6s%-8s%-3s%-15s%-3s%-2s%-15s%-3s%-2s %-15s",
                contestedReturnCode, originalEntryTraceNumber, dateOriginalEntryReturned,
                originalReceivingDFIIdentification, originalSettlementDate, returnTraceNumber,
                returnSettlementDate, returnReasonCode, dishonoredReturnTraceNumber,
                dishonoredReturnSettlementDate, dishonoredReturnReasonCode, traceNumber);
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("99")) {
            return false;
        }
        if (contestedReturnCode == null || contestedReturnCode.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isAlphanumeric(String value) {
        return value != null && Pattern.matches("^[a-zA-Z0-9]*$", value);
    }
}

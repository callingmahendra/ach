package com.ach;

import java.nio.charset.StandardCharsets;

public class ADVEntryDetail {
    private String id;
    private int transactionCode;
    private String RDFIIdentification;
    private String checkDigit;
    private String DFIAccountNumber;
    private int amount;
    private String adviceRoutingNumber;
    private String fileIdentification;
    private String ACHOperatorData;
    private String individualName;
    private String discretionaryData;
    private int addendaRecordIndicator;
    private String ACHOperatorRoutingNumber;
    private int julianDay;
    private int sequenceNumber;
    private Addenda99 addenda99;
    private String category;

    public ADVEntryDetail() {
        this.category = "Forward";
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.transactionCode = Integer.parseInt(record.substring(1, 3));
        this.RDFIIdentification = record.substring(3, 11);
        this.checkDigit = record.substring(11, 12);
        this.DFIAccountNumber = record.substring(12, 27);
        this.amount = Integer.parseInt(record.substring(27, 39));
        this.adviceRoutingNumber = record.substring(39, 48);
        this.fileIdentification = record.substring(48, 53);
        this.ACHOperatorData = record.substring(53, 54);
        this.individualName = record.substring(54, 76);
        this.discretionaryData = record.substring(76, 78);
        this.addendaRecordIndicator = Integer.parseInt(record.substring(78, 79));
        this.ACHOperatorRoutingNumber = record.substring(79, 87);
        this.julianDay = Integer.parseInt(record.substring(87, 90));
        this.sequenceNumber = Integer.parseInt(record.substring(90, 94));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("6");
        sb.append(String.format("%02d", this.transactionCode));
        sb.append(this.RDFIIdentification);
        sb.append(this.checkDigit);
        sb.append(String.format("%-15s", this.DFIAccountNumber));
        sb.append(String.format("%012d", this.amount));
        sb.append(this.adviceRoutingNumber);
        sb.append(this.fileIdentification);
        sb.append(this.ACHOperatorData);
        sb.append(String.format("%-22s", this.individualName));
        sb.append(String.format("%-2s", this.discretionaryData));
        sb.append(this.addendaRecordIndicator);
        sb.append(this.ACHOperatorRoutingNumber);
        sb.append(String.format("%03d", this.julianDay));
        sb.append(String.format("%04d", this.sequenceNumber));
        return sb.toString();
    }

    public boolean validate() {
        if (this.transactionCode == 0 || this.RDFIIdentification == null || this.DFIAccountNumber == null ||
            this.individualName == null || this.ACHOperatorRoutingNumber == null || this.julianDay <= 0 ||
            this.sequenceNumber == 0) {
            return false;
        }
        return true;
    }

    // Getters and setters for all fields
    // ...
}

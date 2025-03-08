package com.ach;

public class BatchControl {
    private String id;
    private int serviceClassCode;
    private int entryAddendaCount;
    private int entryHash;
    private int totalDebitEntryDollarAmount;
    private int totalCreditEntryDollarAmount;
    private String companyIdentification;
    private String messageAuthenticationCode;
    private String ODFIIdentification;
    private int batchNumber;

    public BatchControl() {
        this.serviceClassCode = 200;
        this.entryHash = 1;
        this.batchNumber = 1;
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.serviceClassCode = Integer.parseInt(record.substring(1, 4));
        this.entryAddendaCount = Integer.parseInt(record.substring(4, 10));
        this.entryHash = Integer.parseInt(record.substring(10, 20));
        this.totalDebitEntryDollarAmount = Integer.parseInt(record.substring(20, 32));
        this.totalCreditEntryDollarAmount = Integer.parseInt(record.substring(32, 44));
        this.companyIdentification = record.substring(44, 54).trim();
        this.messageAuthenticationCode = record.substring(54, 73).trim();
        this.ODFIIdentification = record.substring(79, 87).trim();
        this.batchNumber = Integer.parseInt(record.substring(87, 94));
    }

    @Override
    public String toString() {
        return String.format("8%03d%06d%010d%012d%012d%-10s%-19s      %-8s%07d",
                serviceClassCode, entryAddendaCount, entryHash, totalDebitEntryDollarAmount,
                totalCreditEntryDollarAmount, companyIdentification, messageAuthenticationCode,
                ODFIIdentification, batchNumber);
    }

    public boolean validate() {
        if (serviceClassCode == 0 || ODFIIdentification.equals("00000000")) {
            return false;
        }
        return true;
    }

    // Getters and setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getServiceClassCode() {
        return serviceClassCode;
    }

    public void setServiceClassCode(int serviceClassCode) {
        this.serviceClassCode = serviceClassCode;
    }

    public int getEntryAddendaCount() {
        return entryAddendaCount;
    }

    public void setEntryAddendaCount(int entryAddendaCount) {
        this.entryAddendaCount = entryAddendaCount;
    }

    public int getEntryHash() {
        return entryHash;
    }

    public void setEntryHash(int entryHash) {
        this.entryHash = entryHash;
    }

    public int getTotalDebitEntryDollarAmount() {
        return totalDebitEntryDollarAmount;
    }

    public void setTotalDebitEntryDollarAmount(int totalDebitEntryDollarAmount) {
        this.totalDebitEntryDollarAmount = totalDebitEntryDollarAmount;
    }

    public int getTotalCreditEntryDollarAmount() {
        return totalCreditEntryDollarAmount;
    }

    public void setTotalCreditEntryDollarAmount(int totalCreditEntryDollarAmount) {
        this.totalCreditEntryDollarAmount = totalCreditEntryDollarAmount;
    }

    public String getCompanyIdentification() {
        return companyIdentification;
    }

    public void setCompanyIdentification(String companyIdentification) {
        this.companyIdentification = companyIdentification;
    }

    public String getMessageAuthenticationCode() {
        return messageAuthenticationCode;
    }

    public void setMessageAuthenticationCode(String messageAuthenticationCode) {
        this.messageAuthenticationCode = messageAuthenticationCode;
    }

    public String getODFIIdentification() {
        return ODFIIdentification;
    }

    public void setODFIIdentification(String ODFIIdentification) {
        this.ODFIIdentification = ODFIIdentification;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}

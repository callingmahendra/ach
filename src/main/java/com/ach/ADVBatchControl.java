package com.ach;

import java.util.Objects;

public class ADVBatchControl {
    private String id;
    private int serviceClassCode;
    private int entryAddendaCount;
    private int entryHash;
    private int totalDebitEntryDollarAmount;
    private int totalCreditEntryDollarAmount;
    private String achOperatorData;
    private String odfiIdentification;
    private int batchNumber;

    public ADVBatchControl() {
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
        this.totalDebitEntryDollarAmount = Integer.parseInt(record.substring(20, 40));
        this.totalCreditEntryDollarAmount = Integer.parseInt(record.substring(40, 60));
        this.achOperatorData = record.substring(60, 79).trim();
        this.odfiIdentification = record.substring(79, 87).trim();
        this.batchNumber = Integer.parseInt(record.substring(87, 94));
    }

    @Override
    public String toString() {
        return String.format("8%03d%06d%010d%020d%020d%-19s%-8s%07d",
                serviceClassCode, entryAddendaCount, entryHash, totalDebitEntryDollarAmount,
                totalCreditEntryDollarAmount, achOperatorData, odfiIdentification, batchNumber);
    }

    public boolean validate() {
        if (serviceClassCode == 0 || odfiIdentification.equals("000000000") || odfiIdentification.isEmpty()) {
            return false;
        }
        return true;
    }

    // Getters and Setters

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

    public String getAchOperatorData() {
        return achOperatorData;
    }

    public void setAchOperatorData(String achOperatorData) {
        this.achOperatorData = achOperatorData;
    }

    public String getOdfiIdentification() {
        return odfiIdentification;
    }

    public void setOdfiIdentification(String odfiIdentification) {
        this.odfiIdentification = odfiIdentification;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ADVBatchControl that = (ADVBatchControl) o;
        return serviceClassCode == that.serviceClassCode &&
                entryAddendaCount == that.entryAddendaCount &&
                entryHash == that.entryHash &&
                totalDebitEntryDollarAmount == that.totalDebitEntryDollarAmount &&
                totalCreditEntryDollarAmount == that.totalCreditEntryDollarAmount &&
                batchNumber == that.batchNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(achOperatorData, that.achOperatorData) &&
                Objects.equals(odfiIdentification, that.odfiIdentification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceClassCode, entryAddendaCount, entryHash, totalDebitEntryDollarAmount,
                totalCreditEntryDollarAmount, achOperatorData, odfiIdentification, batchNumber);
    }
}

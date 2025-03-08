package com.ach;

public class ADVFileControl {
    private String id;
    private int batchCount;
    private int blockCount;
    private int entryAddendaCount;
    private int entryHash;
    private int totalDebitEntryDollarAmountInFile;
    private int totalCreditEntryDollarAmountInFile;

    public ADVFileControl() {
    }

    public void parse(String record) {
        if (record.length() < 71) {
            return;
        }

        this.batchCount = Integer.parseInt(record.substring(1, 7).trim());
        this.blockCount = Integer.parseInt(record.substring(7, 13).trim());
        this.entryAddendaCount = Integer.parseInt(record.substring(13, 21).trim());
        this.entryHash = Integer.parseInt(record.substring(21, 31).trim());
        this.totalDebitEntryDollarAmountInFile = Integer.parseInt(record.substring(31, 51).trim());
        this.totalCreditEntryDollarAmountInFile = Integer.parseInt(record.substring(51, 71).trim());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("9");
        sb.append(String.format("%06d", batchCount));
        sb.append(String.format("%06d", blockCount));
        sb.append(String.format("%08d", entryAddendaCount));
        sb.append(String.format("%010d", entryHash));
        sb.append(String.format("%020d", totalDebitEntryDollarAmountInFile));
        sb.append(String.format("%020d", totalCreditEntryDollarAmountInFile));
        sb.append("                       ");
        return sb.toString();
    }

    public boolean validate() {
        if (batchCount == 0 || blockCount == 0 || entryAddendaCount == 0 || entryHash == 0) {
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

    public int getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(int batchCount) {
        this.batchCount = batchCount;
    }

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
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

    public int getTotalDebitEntryDollarAmountInFile() {
        return totalDebitEntryDollarAmountInFile;
    }

    public void setTotalDebitEntryDollarAmountInFile(int totalDebitEntryDollarAmountInFile) {
        this.totalDebitEntryDollarAmountInFile = totalDebitEntryDollarAmountInFile;
    }

    public int getTotalCreditEntryDollarAmountInFile() {
        return totalCreditEntryDollarAmountInFile;
    }

    public void setTotalCreditEntryDollarAmountInFile(int totalCreditEntryDollarAmountInFile) {
        this.totalCreditEntryDollarAmountInFile = totalCreditEntryDollarAmountInFile;
    }
}

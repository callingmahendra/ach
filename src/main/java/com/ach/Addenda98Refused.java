package com.ach;

public class Addenda98Refused {
    private String id;
    private String typeCode;
    private String refusedChangeCode;
    private String originalTrace;
    private String originalDFI;
    private String correctedData;
    private String changeCode;
    private String traceSequenceNumber;
    private String traceNumber;

    public Addenda98Refused() {
        this.typeCode = "98";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getRefusedChangeCode() {
        return refusedChangeCode;
    }

    public void setRefusedChangeCode(String refusedChangeCode) {
        this.refusedChangeCode = refusedChangeCode;
    }

    public String getOriginalTrace() {
        return originalTrace;
    }

    public void setOriginalTrace(String originalTrace) {
        this.originalTrace = originalTrace;
    }

    public String getOriginalDFI() {
        return originalDFI;
    }

    public void setOriginalDFI(String originalDFI) {
        this.originalDFI = originalDFI;
    }

    public String getCorrectedData() {
        return correctedData;
    }

    public void setCorrectedData(String correctedData) {
        this.correctedData = correctedData;
    }

    public String getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(String changeCode) {
        this.changeCode = changeCode;
    }

    public String getTraceSequenceNumber() {
        return traceSequenceNumber;
    }

    public void setTraceSequenceNumber(String traceSequenceNumber) {
        this.traceSequenceNumber = traceSequenceNumber;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3).trim();
        this.refusedChangeCode = record.substring(3, 6).trim();
        this.originalTrace = record.substring(6, 21).trim();
        this.originalDFI = record.substring(27, 35).trim();
        this.correctedData = record.substring(35, 64).trim();
        this.changeCode = record.substring(64, 67).trim();
        this.traceSequenceNumber = record.substring(67, 74).trim();
        this.traceNumber = record.substring(79, 94).trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("7");
        sb.append(typeCode);
        sb.append(refusedChangeCode);
        sb.append(String.format("%-15s", originalTrace));
        sb.append("      ");
        sb.append(String.format("%-8s", originalDFI));
        sb.append(String.format("%-29s", correctedData));
        sb.append(changeCode);
        sb.append(String.format("%-7s", traceSequenceNumber));
        sb.append("     ");
        sb.append(String.format("%-15s", traceNumber));
        return sb.toString();
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("98")) {
            return false;
        }

        if (refusedChangeCode == null || !changeCodeDict.containsKey(refusedChangeCode)) {
            return false;
        }

        if (correctedData == null || correctedData.isEmpty()) {
            return false;
        }

        if (changeCode == null || !changeCodeDict.containsKey(changeCode)) {
            return false;
        }

        if (traceSequenceNumber == null || traceSequenceNumber.isEmpty()) {
            return false;
        }

        return true;
    }
}

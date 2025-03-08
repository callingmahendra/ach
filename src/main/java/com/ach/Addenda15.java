package com.ach;

public class Addenda15 {
    private String id;
    private String typeCode;
    private String receiverIDNumber;
    private String receiverStreetAddress;
    private int entryDetailSequenceNumber;

    public Addenda15() {
        this.typeCode = "15";
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

    public String getReceiverIDNumber() {
        return receiverIDNumber;
    }

    public void setReceiverIDNumber(String receiverIDNumber) {
        this.receiverIDNumber = receiverIDNumber;
    }

    public String getReceiverStreetAddress() {
        return receiverStreetAddress;
    }

    public void setReceiverStreetAddress(String receiverStreetAddress) {
        this.receiverStreetAddress = receiverStreetAddress;
    }

    public int getEntryDetailSequenceNumber() {
        return entryDetailSequenceNumber;
    }

    public void setEntryDetailSequenceNumber(int entryDetailSequenceNumber) {
        this.entryDetailSequenceNumber = entryDetailSequenceNumber;
    }

    public void parse(String record) {
        if (record.length() != 94) {
            return;
        }

        this.typeCode = record.substring(1, 3);
        this.receiverIDNumber = record.substring(3, 18).trim();
        this.receiverStreetAddress = record.substring(18, 53).trim();
        this.entryDetailSequenceNumber = Integer.parseInt(record.substring(87, 94).trim());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("7");
        sb.append(typeCode);
        sb.append(String.format("%-15s", receiverIDNumber));
        sb.append(String.format("%-35s", receiverStreetAddress));
        sb.append("                                  ");
        sb.append(String.format("%07d", entryDetailSequenceNumber));
        return sb.toString();
    }

    public boolean validate() {
        if (typeCode == null || !typeCode.equals("15")) {
            return false;
        }
        if (receiverStreetAddress == null || receiverStreetAddress.isEmpty()) {
            return false;
        }
        if (entryDetailSequenceNumber == 0) {
            return false;
        }
        return true;
    }
}

package com.ach;

import java.util.ArrayList;
import java.util.List;

public class BatchCCD extends Batch {
    public BatchCCD(BatchHeader header) {
        super(header);
    }

    @Override
    public void parse(String record) {
        // Implement the parse logic here
    }

    @Override
    public String toString() {
        // Implement the string representation logic here
        return "";
    }

    @Override
    public void validate() throws Exception {
        // Implement the validation logic here
    }

    public static BatchCCD createMockBatch() {
        BatchHeader header = new BatchHeader();
        header.setServiceClassCode(225);
        header.setStandardEntryClassCode("CCD");
        header.setCompanyName("Your Company, inc");
        header.setCompanyIdentification("121042882");
        header.setCompanyEntryDescription("Vndr Pay");
        header.setODFIIdentification("121042882");

        BatchCCD batch = new BatchCCD(header);

        EntryDetail entry = new EntryDetail();
        entry.setTransactionCode(27);
        entry.setRDFIIdentification("231380104");
        entry.setDFIAccountNumber("744-5678-99");
        entry.setAmount(5000000);
        entry.setIdentificationNumber("location #23");
        entry.setReceivingCompany("Best Co. #23");
        entry.setTraceNumber(header.getODFIIdentification(), 1);
        entry.setDiscretionaryData("S");

        Addenda05 addenda = new Addenda05();
        addenda.setPaymentRelatedInformation("Payment info");
        addenda.setSequenceNumber(1);
        addenda.setEntryDetailSequenceNumber(1);

        entry.addAddenda05(addenda);
        entry.setAddendaRecordIndicator(1);

        batch.addEntry(entry);

        return batch;
    }

    public static void main(String[] args) {
        BatchCCD batch = createMockBatch();
        System.out.println(batch);
    }
}

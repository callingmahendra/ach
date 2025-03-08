package com.ach;

import java.util.ArrayList;
import java.util.List;

public class BatchCIE extends Batch {
    public BatchCIE() {
        super();
    }

    public BatchCIE(BatchHeader bh) {
        super();
        this.setControl(new BatchControl());
        this.setHeader(bh);
        this.setID(bh.getID());
    }

    @Override
    public void validate() throws Exception {
        // basic verification of the batch before we validate specific rules.
        this.verify();

        // Add configuration based validation for this type.

        // Add type specific validation.
        if (!this.getHeader().getStandardEntryClassCode().equals("CIE")) {
            throw new Exception("StandardEntryClassCode must be CIE");
        }

        // CIE detail entries can only be a credit, ServiceClassCode must allow credit
        if (this.getHeader().getServiceClassCode() == 225) {
            throw new Exception("ServiceClassCode must allow credit");
        }

        for (EntryDetail entry : this.getEntries()) {
            // CIE detail entries must be a credit
            if (!entry.getCreditOrDebit().equals("C")) {
                throw new Exception("TransactionCode must be a credit");
            }
            // CIE must have a maximum of one Addenda05 record
            if (entry.getAddenda05().size() > 1) {
                throw new Exception("AddendaCount must be 1");
            }
            // Verify the Amount is valid for SEC code and TransactionCode
            this.validAmountForCodes(entry);
            // Verify the TransactionCode is valid for a ServiceClassCode
            this.validTranCodeForServiceClassCode(entry);
            // Verify Addenda* FieldInclusion based on entry.Category and batchHeader.StandardEntryClassCode
            this.addendaFieldInclusion(entry);
        }
    }

    @Override
    public void create() throws Exception {
        // generates sequence numbers and batch control
        this.build();
        // Additional steps specific to batch type
        // ...
        this.validate();
    }

    public static void main(String[] args) {
        try {
            BatchHeader bh = new BatchHeader();
            bh.setServiceClassCode(220);
            bh.setStandardEntryClassCode("CIE");
            bh.setCompanyName("Payee Name");
            bh.setCompanyIdentification("121042882");
            bh.setCompanyEntryDescription("ACH CIE");
            bh.setODFIIdentification("12104288");

            BatchCIE batch = new BatchCIE(bh);

            EntryDetail entry = new EntryDetail();
            entry.setTransactionCode(22);
            entry.setRDFI("231380104");
            entry.setDFIAccountNumber("744-5678-99");
            entry.setAmount(25000);
            entry.setIdentificationNumber("45689033");
            entry.setIndividualName("Receiver Account Name");
            entry.setTraceNumber(bh.getODFIIdentification(), 1);
            entry.setDiscretionaryData("01");
            entry.setCategory("Forward");

            Addenda05 addenda05 = new Addenda05();
            addenda05.setPaymentRelatedInformation("Payment related information");
            addenda05.setSequenceNumber(1);
            addenda05.setEntryDetailSequenceNumber(1);

            List<Addenda05> addenda05List = new ArrayList<>();
            addenda05List.add(addenda05);
            entry.setAddenda05(addenda05List);
            entry.setAddendaRecordIndicator(1);

            batch.addEntry(entry);
            batch.create();

            System.out.println("BatchCIE created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

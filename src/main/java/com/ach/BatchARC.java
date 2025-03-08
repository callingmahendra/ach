package com.ach;

public class BatchARC extends Batch {

    public BatchARC(BatchHeader bh) {
        super();
        this.setControl(new BatchControl());
        this.setHeader(bh);
        this.setID(bh.getID());
    }

    @Override
    public void validate() throws Exception {
        super.validate();

        if (!this.getHeader().getStandardEntryClassCode().equals("ARC")) {
            throw new Exception("StandardEntryClassCode must be ARC");
        }

        if (this.getHeader().getServiceClassCode() == ServiceClassCode.CREDITS_ONLY) {
            throw new Exception("ServiceClassCode must allow debits");
        }

        for (EntryDetail entry : this.getEntries()) {
            if (!entry.isDebit()) {
                throw new Exception("TransactionCode must be a debit");
            }

            if (entry.getAmount() > 2500000) {
                throw new Exception("Amount must be 25,000 or less");
            }

            if (entry.getIdentificationNumber().isEmpty()) {
                throw new Exception("CheckSerialNumber must be defined");
            }

            this.validAmountForCodes(entry);
            this.validTranCodeForServiceClassCode(entry);
            this.addendaFieldInclusion(entry);
        }
    }

    @Override
    public void create() throws Exception {
        super.create();
        this.validate();
    }
}

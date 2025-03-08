package com.ach;

import java.util.List;

public class BatchCTX extends Batch {

    public BatchCTX(BatchHeader bh) {
        super();
        this.setControl(new BatchControl());
        this.setHeader(bh);
        this.setID(bh.ID);
    }

    @Override
    public void validate() throws Exception {
        // basic verification of the batch before we validate specific rules.
        this.verify();

        // Add configuration and type specific validation for this type.
        if (!this.getHeader().getStandardEntryClassCode().equals("CTX")) {
            throw new Exception("StandardEntryClassCode must be CTX");
        }

        for (EntryDetail entry : this.getEntries()) {
            int addendaCount = entry.getAddenda05().size();

            // Trapping this error, as entry.CTXAddendaRecordsField() can not be greater than 9999
            if (addendaCount > 9999) {
                throw new Exception("AddendaCount cannot be greater than 9999");
            }

            // Add to addendaCount so Corrections and Returns compare AddendaRecordIndicator correctly
            if (entry.getAddenda98() != null) {
                addendaCount += 1;
            }
            if (entry.getAddenda99() != null) {
                addendaCount += 1;
            }

            // validate CTXAddendaRecord Field is equal to the actual number of Addenda records
            // use 0 value if there is no Addenda records
            int indicator = Integer.parseInt(entry.CATXAddendaRecordsField());
            if (addendaCount != indicator) {
                if (this.getValidateOpts() == null || !this.getValidateOpts().isUnequalAddendaCounts()) {
                    throw new Exception("AddendaCount does not match expected count");
                }
            }
            // Verify TransactionCode is valid for CTX
            switch (entry.getTransactionCode()) {
                case 23: // CheckingPrenoteCredit
                case 28: // CheckingPrenoteDebit
                case 33: // SavingsPrenoteCredit
                case 38: // SavingsPrenoteDebit
                case 43: // GLPrenoteCredit
                case 48: // GLPrenoteDebit
                case 53: // LoanPrenoteCredit
                    if (entry.getAmount() != 0) {
                        throw new Exception("TransactionCode amount must be zero for prenotes");
                    }
                    break;
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
}

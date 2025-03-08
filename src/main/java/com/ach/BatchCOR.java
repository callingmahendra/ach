package com.ach;

public class BatchCOR extends Batch {

    public BatchCOR(BatchHeader bh) {
        setControl(new BatchControl());
        setHeader(bh);
        setID(bh.ID);
    }

    @Override
    public void validate() throws Exception {
        // basic verification of the batch before we validate specific rules.
        verify();

        // Add configuration based validation for this type.
        // COR Addenda must be Addenda98
        isAddenda98();

        // Add type specific validation.
        if (!getHeader().getStandardEntryClassCode().equals("COR")) {
            throw new Exception("StandardEntryClassCode must be COR");
        }

        // The Amount field must be zero
        // batch.verify calls batch.isBatchAmount which ensures the batch.Control values are accurate.
        if (getControl().getTotalCreditEntryDollarAmount() != 0) {
            throw new Exception("TotalCreditEntryDollarAmount must be zero");
        }
        if (getControl().getTotalDebitEntryDollarAmount() != 0) {
            throw new Exception("TotalDebitEntryDollarAmount must be zero");
        }

        for (EntryDetail entry : getEntries()) {
            /* COR TransactionCode must be a Return or NOC transaction Code
               Return/NOC
               Credit:  21, 31, 41, 51
               Debit: 26, 36, 46, 56
            */
            switch (entry.getTransactionCode()) {
                case 22:
                case 23:
                case 24:
                case 27:
                case 28:
                case 29:
                case 32:
                case 33:
                case 34:
                case 37:
                case 38:
                case 39:
                case 42:
                case 43:
                case 44:
                case 47:
                case 48:
                case 49:
                case 52:
                case 53:
                case 54:
                case 57:
                case 58:
                case 59:
                    throw new Exception("Invalid TransactionCode for COR: " + entry.getTransactionCode());
            }

            // Verify the Amount is valid for SEC code and TransactionCode
            validAmountForCodes(entry);

            // Verify the TransactionCode is valid for a ServiceClassCode
            validTranCodeForServiceClassCode(entry);

            // Verify Addenda* FieldInclusion based on entry.Category and batchHeader.StandardEntryClassCode
            addendaFieldInclusion(entry);
        }
    }

    @Override
    public void create() throws Exception {
        // generates sequence numbers and batch control
        build();

        validate();
    }

    private void isAddenda98() throws Exception {
        for (EntryDetail entry : getEntries()) {
            if (entry.getAddenda98() == null && entry.getAddenda98Refused() == null) {
                throw new Exception("Addenda98 is required for COR");
            }
        }
    }
}

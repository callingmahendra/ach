package com.ach;

import java.util.ArrayList;
import java.util.List;

public class BatchBOC {
    private String id;
    private BatchHeader header;
    private List<EntryDetail> entries;
    private BatchControl control;
    private List<ADVEntryDetail> advEntries;
    private ADVBatchControl advControl;
    private Offset offset;
    private String category;

    public BatchBOC() {
        this.entries = new ArrayList<>();
        this.advEntries = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BatchHeader getHeader() {
        return header;
    }

    public void setHeader(BatchHeader header) {
        this.header = header;
    }

    public List<EntryDetail> getEntries() {
        return entries;
    }

    public void addEntry(EntryDetail entry) {
        this.entries.add(entry);
    }

    public BatchControl getControl() {
        return control;
    }

    public void setControl(BatchControl control) {
        this.control = control;
    }

    public List<ADVEntryDetail> getAdvEntries() {
        return advEntries;
    }

    public void addAdvEntry(ADVEntryDetail entry) {
        this.advEntries.add(entry);
    }

    public ADVBatchControl getAdvControl() {
        return advControl;
    }

    public void setAdvControl(ADVBatchControl advControl) {
        this.advControl = advControl;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void parse(String record) {
        // Implement the parse logic here
    }

    @Override
    public String toString() {
        // Implement the toString logic here
        return "";
    }

    public void validate() {
        // Implement the validate logic here
    }
}

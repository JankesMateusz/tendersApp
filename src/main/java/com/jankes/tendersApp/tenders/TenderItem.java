package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.BaseEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

class TenderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tender_id")
    protected Tender tender;
    protected ItemCategory category;
    protected Integer quantity;
    protected Integer cpuQuantity;
    protected String os;
    protected String office;
    protected Integer taskNumber;

    public TenderItem(){}

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCpuQuantity() {
        return cpuQuantity;
    }

    public void setCpuQuantity(Integer cpuQuantity) {
        this.cpuQuantity = cpuQuantity;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
}

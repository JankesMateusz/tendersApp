package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.BaseEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

class TenderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;
    private ItemCategory category;
    private Integer quantity;
    private Integer cpuQuantity;
    private String os;
    private String office;
    private String remarks;
    private Integer taskNumber;

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

    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
    void updateItemFromDto(TenderItemDto dto){
        this.category = dto.getCategory();
        this.quantity = dto.getQuantity();
        this.cpuQuantity = dto.getCpuQuantity();
        this.os = dto.getOs();
        this.office = dto.getOffice();
        this.remarks = dto.getRemarks();
        this.taskNumber = dto.getTaskNumber();
    }
}

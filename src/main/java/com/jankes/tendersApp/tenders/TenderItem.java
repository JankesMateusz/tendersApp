package com.jankes.tendersApp.tenders;

import javax.persistence.*;

@Entity
@Table(name = "tender_items")
class TenderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
    private Integer quantity;
    private Integer cpuQuantity;
    private String os;
    private String office;
    private String remarks;
    private Integer taskNumber;
    private String purchaseForm;

    public TenderItem(){}

    public TenderItem(Tender tender, ItemCategory category, Integer quantity, Integer cpuQuantity, String os, String office, String remarks, Integer taskNumber, String purchaseForm) {
        this.tender = tender;
        this.category = category;
        this.quantity = quantity;
        this.cpuQuantity = cpuQuantity;
        this.os = os;
        this.office = office;
        this.remarks = remarks;
        this.taskNumber = taskNumber;
        this.purchaseForm = purchaseForm;
    }

    public Long getId() {return this.id;}

    public void setId(Long id){
        this.id = id;
    }

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

    public String getPurchaseForm() {return purchaseForm;}

    public void setPurchaseForm(String purchaseForm) {
        this.purchaseForm = purchaseForm;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TenderItem other))
            return false;

        return this.getId() != null &&
                this.getId().equals(other.getId());
    }
}

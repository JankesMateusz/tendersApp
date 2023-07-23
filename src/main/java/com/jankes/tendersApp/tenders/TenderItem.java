package com.jankes.tendersApp.tenders;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tender_items")
class TenderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;
    //PRE BID VERIFICATION

    @Enumerated(EnumType.STRING)
    private ItemCategory category;
    private Integer quantity;
    private Integer cpuQuantity;
    private String os;
    private String office;
    private String remarks;
    private Integer taskNumber;
    private String initialBenchmark;
    private boolean ISO50001;
    private boolean TCO;
    private String purchaseForm;

    //POST BID VERIFICATION

    private String winner;
    private String vendor;
    private String status;
    @Nullable
    private LocalDate awardLetterDate;
    private String comments;
    private String finalOS;
    private String finalOffice;
    private String deliveryTerm;
    private String benchmarkBid;

    public TenderItem(){}

    public TenderItem(Tender tender, ItemCategory category, Integer quantity, Integer cpuQuantity, String os, String office, String remarks, Integer taskNumber, String purchaseForm, String winner, String vendor, String status, LocalDate awardLetterDate, String comments, String finalOS, String finalOffice, String deliveryTerm, String benchmarkBid) {
        this.tender = tender;
        this.category = category;
        this.quantity = quantity;
        this.cpuQuantity = cpuQuantity;
        this.os = os;
        this.office = office;
        this.remarks = remarks;
        this.taskNumber = taskNumber;
        this.purchaseForm = purchaseForm;
        this.winner = winner;
        this.vendor = vendor;
        this.status = status;
        this.awardLetterDate = awardLetterDate;
        this.comments = comments;
        this.finalOS = finalOS;
        this.finalOffice = finalOffice;
        this.deliveryTerm = deliveryTerm;
        this.benchmarkBid = benchmarkBid;
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

    public String getInitialBenchmark() {
        return initialBenchmark;
    }

    public void setInitialBenchmark(String initialBenchmark) {
        this.initialBenchmark = initialBenchmark;
    }

    public boolean isISO50001() {
        return ISO50001;
    }

    public void setISO50001(boolean ISO50001) {
        this.ISO50001 = ISO50001;
    }

    public boolean isTCO() {
        return TCO;
    }

    public void setTCO(boolean TCO) {
        this.TCO = TCO;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAwardLetterDate() {
        return awardLetterDate;
    }

    public void setAwardLetterDate(LocalDate awardLetterDate) {
        this.awardLetterDate = awardLetterDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFinalOS() {
        return finalOS;
    }

    public void setFinalOS(String finalOS) {
        this.finalOS = finalOS;
    }

    public String getFinalOffice() {
        return finalOffice;
    }

    public void setFinalOffice(String finalOffice) {
        this.finalOffice = finalOffice;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    public String getBenchmarkBid() {
        return benchmarkBid;
    }

    public void setBenchmarkBid(String benchmarkBid) {
        this.benchmarkBid = benchmarkBid;
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

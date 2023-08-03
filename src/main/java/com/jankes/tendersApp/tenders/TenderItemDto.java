package com.jankes.tendersApp.tenders;

import java.util.Objects;

public class TenderItemDto {

    private long id;
    private String category;
    private int quantity;
    private int cpuQuantity;
    private String os;
    private String office;
    private String remarks;
    private int taskNumber;
    private String initialBenchmark;
    private boolean iso50001;
    private boolean tco;
    private String purchaseForm;
    private String winner;
    private String vendor;
    private String status;
    private String awardLetterDate;
    private String comments;
    private String finalOS;
    private String finalOffice;
    private String deliveryTerm;
    private String benchmarkBid;

    public static Builder builder(){
        return new Builder();
    }

    public TenderItemDto(){}

    public TenderItemDto(Builder builder){
        this.id = builder.id;
        this.category = builder.category;
        this.quantity = builder.quantity;
        this.cpuQuantity = builder.cpuQuantity;
        this.os = builder.os;
        this.office = builder.office;
        this.remarks = builder.remarks;
        this.taskNumber = builder.taskNumber;
        this.purchaseForm = builder.purchaseForm;
        this.initialBenchmark = builder.initialBenchmark;
        this.iso50001 = builder.iso50001;
        this.tco = builder.tco;
        this.winner = builder.winner;
        this.vendor = builder.vendor;
        this.status = builder.status;
        this.awardLetterDate = builder.awardLetterDate;
        this.comments = builder.comments;
        this.finalOS = builder.finalOS;
        this.finalOffice = builder.finalOffice;
        this.deliveryTerm = builder.deliveryTerm;
        this.benchmarkBid = builder.benchmarkBid;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCpuQuantity() {
        return cpuQuantity;
    }

    public String getOs() {
        return os;
    }

    public String getOffice() {
        return office;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getInitialBenchmark() {
        return initialBenchmark;
    }

    public boolean isIso50001() {
        return iso50001;
    }

    public boolean isTco() {
        return tco;
    }

    public String getPurchaseForm(){return purchaseForm;}

    public String getWinner() {
        return winner;
    }

    public String getVendor() {
        return vendor;
    }

    public String getStatus() {
        return status;
    }

    public String getAwardLetterDate() {
        return Objects.requireNonNullElse(this.awardLetterDate, "1999-01-01");
    }

    public String getComments() {
        return comments;
    }

    public String getFinalOS() {
        return finalOS;
    }

    public String getFinalOffice() {
        return finalOffice;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public String getBenchmarkBid() {
        return benchmarkBid;
    }

    public static class Builder{
        private long id;
        private String category;
        private int quantity;
        private int cpuQuantity;
        private String os;
        private String office;
        private String remarks;
        private String initialBenchmark;
        private boolean iso50001;
        private boolean tco;
        private int taskNumber;
        private String purchaseForm;
        private String winner;
        private String vendor;
        private String status;
        private String awardLetterDate;
        private String comments;
        private String finalOS;
        private String finalOffice;
        private String deliveryTerm;
        private String benchmarkBid;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withCategory(String category){
            this.category = category;
            return this;
        }

        public Builder withQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder withCpuQuantity(int cpuQuantity){
            this.cpuQuantity = cpuQuantity;
            return this;
        }

        public Builder withOs(String os){
            this.os = os;
            return this;
        }

        public Builder withOffice(String office){
            this.office = office;
            return this;
        }

        public Builder withRemarks(String remarks){
            this.remarks = remarks;
            return this;
        }

        public Builder withTaskNumber(int number){
            this.taskNumber = number;
            return this;
        }

        public Builder withInitialBenchmark(String initialBenchmark){
            this.initialBenchmark = initialBenchmark;
            return this;
        }

        public Builder withISO50001(boolean iso50001){
            this.iso50001 = iso50001;
            return this;
        }

        public Builder withTCO(boolean tco){
            this.tco = tco;
            return this;
        }

        public Builder withPurchaseForm(String purchaseForm){
            this.purchaseForm = purchaseForm;
            return this;
        }

        public Builder withWinner(String winner){
            this.winner = winner;
            return this;
        }

        public Builder withVendor(String vendor){
            this.vendor = vendor;
            return this;
        }

        public Builder withStatus(String status){
            this.status = status;
            return this;
        }

        public Builder withAwardLetterDate(String awardLetterDate){
            this.awardLetterDate = awardLetterDate;
            return this;
        }

        public Builder withComments(String comments){
            this.comments = comments;
            return this;
        }

        public Builder withFinalOS(String finalOS){
            this.finalOS = finalOS;
            return this;
        }

        public Builder withFinalOffice(String finalOffice){
            this.finalOffice = finalOffice;
            return this;
        }

        public Builder withDeliveryTerm(String deliveryTerm){
            this.deliveryTerm = deliveryTerm;
            return this;
        }

        public Builder withBenchmarkBid(String benchmarkBid){
            this.benchmarkBid = benchmarkBid;
            return this;
        }

        public TenderItemDto build(){
            return new TenderItemDto(this);
        }
    }
}

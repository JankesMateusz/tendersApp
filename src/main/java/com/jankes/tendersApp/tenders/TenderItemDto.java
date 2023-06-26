package com.jankes.tendersApp.tenders;

public class TenderItemDto {

    private long id;
    private String category;
    private int quantity;
    private int cpuQuantity;
    private String os;
    private String office;
    private String remarks;
    private int taskNumber;
    private String purchaseForm;

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

    public String getPurchaseForm(){return purchaseForm;}

    public static class Builder{
        private long id;
        private String category;
        private int quantity;
        private int cpuQuantity;
        private String os;
        private String office;
        private String remarks;
        private int taskNumber;
        private String purchaseForm;

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

        public Builder withPurchaseForm(String purchaseForm){
            this.purchaseForm = purchaseForm;
            return this;
        }

        public TenderItemDto build(){
            return new TenderItemDto(this);
        }
    }
}

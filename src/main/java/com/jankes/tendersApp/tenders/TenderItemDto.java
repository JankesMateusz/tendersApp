package com.jankes.tendersApp.tenders;

class TenderItemDto {

    private final long id;
    private final String category;
    private final int quantity;
    private final int cpuQuantity;
    private final String os;
    private final String office;
    private final String remarks;
    private final int taskNumber;

    public static Builder builder(){
        return new Builder();
    }

    private TenderItemDto(Builder builder){
        this.id = builder.id;
        this.category = builder.category;
        this.quantity = builder.quantity;
        this.cpuQuantity = builder.cpuQuantity;
        this.os = builder.os;
        this.office = builder.office;
        this.remarks = builder.remarks;
        this.taskNumber = builder.taskNumber;
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

    public static class Builder{
        private long id;
        private String category;
        private int quantity;
        private int cpuQuantity;
        private String os;
        private String office;
        private String remarks;
        private int taskNumber;

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

        public TenderItemDto build(){
            return new TenderItemDto(this);
        }
    }
}

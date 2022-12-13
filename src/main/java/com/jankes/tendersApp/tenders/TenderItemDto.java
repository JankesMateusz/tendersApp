package com.jankes.tendersApp.tenders;

class TenderItemDto {

    private final long id;
    private ItemCategory category;
    private int quantity;
    private int cpuQuantity;
    private String os;
    private String office;
    private int taskNumber;

    public TenderItemDto(TenderItem item){
        this.id = item.getId();
        this.category = item.getCategory();
        this.quantity = item.getQuantity();
        this.cpuQuantity = item.getCpuQuantity();
        this.os = item.getOs();
        this.office = item.getOffice();
        this.taskNumber = item.getTaskNumber();
    }

    public long getId() {
        return id;
    }

    public ItemCategory getCategory() {
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

    public int getTaskNumber() {
        return taskNumber;
    }
}

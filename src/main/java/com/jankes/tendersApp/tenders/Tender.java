package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tenders")
public class Tender {

    public enum Status {
        PENDING, CONFIRMED, CANCELED, REFUSED
    }

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private Purchaser purchaser;
    private String title;
    private String personOfContactFirstName;
    private String personOfContactLastName;
    private String email; //TODO validation
    private String phoneNumber; //TODO validation?
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate publicationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate bidDate;
    private String link;
    private String bidNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean isTimeUp;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate reportDate;
    @Enumerated(EnumType.STRING)
    private TenderBudget budget;
    @OneToMany(mappedBy = "tender")
    private Set<TenderItem> tenderItems;
    private String remarks;

    public Tender() {
        this.isTimeUp = false;
        this.status = Status.PENDING;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getBidDate() {
        return bidDate;
    }

    public void setBidDate(LocalDate bidDate) {
        this.bidDate = bidDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<TenderItem> getTenderItems() {
        return tenderItems;
    }

    void setTenderItems(Set<TenderItem> tenderItems) {
        this.tenderItems = tenderItems;
    }

    public String getPersonOfContactFirstName() {
        return personOfContactFirstName;
    }

    public void setPersonOfContactFirstName(String personOfContactFirstName) {
        this.personOfContactFirstName = personOfContactFirstName;
    }

    public String getPersonOfContactLastName() {
        return personOfContactLastName;
    }

    public void setPersonOfContactLastName(String personOfContactLastName) {
        this.personOfContactLastName = personOfContactLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isTimeUp() {
        return isTimeUp;
    }

    public void setTimeUp(boolean timeUp) {
        isTimeUp = timeUp;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public TenderBudget getBudget() {
        return budget;
    }

    public void setBudget(TenderBudget budget) {
        this.budget = budget;
    }

    void addTenderItem(TenderItem item) {
        tenderItems.add(item);
        item.setTender(this);
    }

    void removeTenderItem(TenderItem item) {
        if (!tenderItems.contains(item)) {
            return;
        }
        tenderItems.remove(item);
        item.setTender(null);
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<TenderItemDto> getTenderItemsDto(){
        return tenderItems.stream()
                .map(t -> new TenderItemMapper()
                        .toDto(t))
                .collect(Collectors.toList());
    }
}

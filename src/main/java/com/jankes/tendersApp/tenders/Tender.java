package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.contacts.PersonInContact;
import com.jankes.tendersApp.purchasers.Purchaser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tenders")
public class Tender {

    public enum Status {
        PENDING, CONFIRMED, CANCELED, REFUSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private Purchaser purchaser;
    private boolean inReport;
    private String mdpId;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bidDate;
    private String siwzLink;
    private String bidNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;
    private boolean euFinancing;
    @Enumerated(EnumType.STRING)
    private TenderBudget budget;
    private String link1;
    private String link2;
    private String link3;
    private String comments;
    @OneToMany(mappedBy = "tender")
    private List<TenderItem> tenderItems;
    @ManyToOne
    @JoinColumn(name = "personInContact_id")
    private PersonInContact personInContact;
    private boolean isTimeUp;

    public Tender() {
        this.isTimeUp = false;
        this.status = Status.PENDING;
        this.tenderItems = new ArrayList<>();
        this.inReport = false;
    }

    public void generateMDPID(){
        int currentYear = Year.now().getValue();
        this.mdpId = String.format("%d%05d", currentYear, id);
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

    public void setMdpId(String mdpId) {
        this.mdpId = mdpId;
    }

    public String getMdpId(){ return mdpId;}

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

    public String getSiwzLink() {
        return siwzLink;
    }

    public void setSiwzLink(String siwzLink) {
        this.siwzLink = siwzLink;
    }

    public List<TenderItem> getTenderItems() {
        return tenderItems;
    }

    void setTenderItems(List<TenderItem> tenderItems) {
        this.tenderItems = tenderItems;
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

    public boolean isInReport() {
        return inReport;
    }

    public void setInReport(boolean inReport) {
        this.inReport = inReport;
    }

    public boolean isEuFinancing() {
        return euFinancing;
    }

    public void setEuFinancing(boolean euFinancing) {
        this.euFinancing = euFinancing;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getLink3() {
        return link3;
    }

    public void setLink3(String link3) {
        this.link3 = link3;
    }

    public PersonInContact getPersonInContact() {
        return personInContact;
    }

    public void setPersonInContact(PersonInContact personInContact) {
        this.personInContact = personInContact;
    }

    public List<TenderItemDto> getTenderItemsDto(){
        return tenderItems.stream()
                .map(t -> new TenderItemMapper()
                        .toDto(t))
                .collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Tender other))
            return false;

        return this.getId() != null &&
                this.getId().equals(other.getId());
    }
}

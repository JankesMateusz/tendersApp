package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.common.BaseEntity;
import com.jankes.tendersApp.purchasers.Purchaser;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Tender extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "purchaser_id")
    private Purchaser purchaser;
    private String title;
    private Date publicationDate;
    private Date bidDate;
    private String link;
    @OneToMany(mappedBy = "tender")
    private Set<TenderItem> tenderItems;

    public Tender(){}

    public Tender(Purchaser purchaser, String title, Date publicationDate, Date bidDate, String link, Set<TenderItem> tenderItems) {
        this.purchaser = purchaser;
        this.title = title;
        this.publicationDate = publicationDate;
        this.bidDate = bidDate;
        this.link = link;
        this.tenderItems = tenderItems;
    }

    void setId(Long id){
        this.id = id;
    }
    Purchaser getPurchaser() {
        return purchaser;
    }

    void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    Date getPublicationDate() {
        return publicationDate;
    }

    void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    Date getBidDate() {
        return bidDate;
    }

    void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    String getLink() {
        return link;
    }

    void setLink(String link) {
        this.link = link;
    }

    Set<TenderItem> getTenderItems() {
        return tenderItems;
    }

    void addTenderItem(TenderItem item){
        tenderItems.add(item);
        item.setTender(this);
    }
    void removeTenderItem(TenderItem item){
        if(!tenderItems.contains(item)){
            return;
        }
        tenderItems.remove(item);
        item.setTender(null);
    }

    TenderDto toDto() {
        return new TenderDto(this);
    }
}

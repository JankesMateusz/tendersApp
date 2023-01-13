package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.purchasers.PurchaserMapper;
import com.jankes.tendersApp.purchasers.PurchaserService;
import com.jankes.tendersApp.purchasers.TypeOfAccount;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("tenders warmup")
public class Warmup implements ApplicationListener<ContextRefreshedEvent> {

    private final TenderRepository tenderRepository;
    private final PurchaserService purchaserService;

    public Warmup(TenderRepository tenderRepository, PurchaserService purchaserService) {
        this.tenderRepository = tenderRepository;
        this.purchaserService = purchaserService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event){
        if(tenderRepository.count() == 0){
            var tender = new Tender();

            tender.setTitle("Test");
            tender.setReportDate(LocalDate.parse("02-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setPublicationDate(LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setBidDate(LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setBidNumber("ID123");
            tender.setBudget(TenderBudget.LESS_THAN_623504_PLN);
            tender.setRemarks("dupa");
            tender.setPersonOfContactFirstName("tester");
            tender.setPersonOfContactLastName("test");
            //tender.setPurchaser(purchaser);

            var tender2 = new Tender();

            tender.setTitle("Test");
            tender.setReportDate(LocalDate.parse("02-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setPublicationDate(LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setBidDate(LocalDate.parse("01-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            tender.setBidNumber("ID321");
            tender.setBudget(TenderBudget.LESS_THAN_623504_PLN);
            tender.setRemarks("dupa");
            tender.setPersonOfContactFirstName("tester");
            tender.setPersonOfContactLastName("test");
            //tender.setPurchaser(purchaser);

            tenderRepository.save(tender);
            tenderRepository.save(tender2);
        }
    }
}

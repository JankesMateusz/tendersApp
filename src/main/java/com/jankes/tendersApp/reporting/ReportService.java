package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.contacts.PersonInContactService;
import com.jankes.tendersApp.purchasers.PurchaserService;
import com.jankes.tendersApp.tenders.TenderItemService;
import com.jankes.tendersApp.tenders.TenderService;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final TenderService tenderService;
    private final TenderItemService tenderItemService;
    private final PurchaserService purchaserService;
    private final PersonInContactService personInContactService;
    private final ReportMapper reportMapper;

    public ReportService(TenderService tenderService, TenderItemService tenderItemService, PurchaserService purchaserService, PersonInContactService personInContactService, ReportMapper reportMapper) {
        this.tenderService = tenderService;
        this.tenderItemService = tenderItemService;
        this.purchaserService = purchaserService;
        this.personInContactService = personInContactService;
        this.reportMapper = reportMapper;
    }


}

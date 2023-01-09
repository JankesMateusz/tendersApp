package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.purchasers.PurchaserService;
import com.jankes.tendersApp.tenders.TenderService;

public class ReportService {

    private final TenderService tenderService;
    private final PurchaserService purchaserService;

    public ReportService(TenderService tenderService, PurchaserService purchaserService) {
        this.tenderService = tenderService;
        this.purchaserService = purchaserService;
    }


}

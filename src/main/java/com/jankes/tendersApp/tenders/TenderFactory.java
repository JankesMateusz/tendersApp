package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserService;
import org.springframework.stereotype.Service;

@Service
class TenderFactory {

    private final PurchaserService purchaserService;
    TenderFactory(PurchaserService purchaserService){
        this.purchaserService = purchaserService;
    }

    Tender from(TenderDto source){
        var result = new Tender();
        result.setId(source.getId());
        result.setPurchaser(purchaserService.findPurchaser(
                source.getPurchaser().getId())
        );
        result.setPublicationDate(source.getPublicationDate());
        result.setBidDate(source.getBidDate());
        result.setLink(source.getLink());
        source.getTenderItems().forEach(sourceItem -> {
            var item = new TenderItem(result, sourceItem.getCategory(),
                    sourceItem.getQuantity(),
                    sourceItem.getCpuQuantity(),
                    sourceItem.getOs(),
                    sourceItem.getOffice(),
                    sourceItem.getRemarks(),
                    sourceItem.getTaskNumber()
            );
            item.setId(source.getId());
            result.addTenderItem(item);
        });
        return result;
    }
}

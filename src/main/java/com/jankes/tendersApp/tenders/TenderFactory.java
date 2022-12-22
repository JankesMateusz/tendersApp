package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
class TenderFactory {

    TenderFactory(){
    }

    Tender from(TenderDto source){
        var result = new Tender();
        result.setId(source.getId());
        result.setPublicationDate(source.getPublicationDate());
        result.setBidDate(source.getBidDate());
        result.setLink(source.getLink());
        source.getTenderItems().forEach(sourceItem -> {
            var item = new TenderItemMapperImpl().toEntity(sourceItem);
            result.addTenderItem(item);
        });
        return result;
    }
}

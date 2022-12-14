package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

@Service
public class TenderFactory {

    Tender from(TenderDto source){
        var result = new Tender();
        result.setId(source.getId());
        result.setPurchaser();
        result.setPublicationDate(source.getPublicationDate());
        result.setBidDate(source.getBidDate());
        result.setLink(source.getLink());
        source.getTenderItems().forEach(sourceItem -> {
            var item = new TenderItem()
        });
    }
}

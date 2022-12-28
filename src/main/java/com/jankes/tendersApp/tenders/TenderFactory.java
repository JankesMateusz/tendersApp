package com.jankes.tendersApp.tenders;

import org.springframework.stereotype.Service;

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
            var item = new TenderItemMapper().toEntity(sourceItem);
            result.addTenderItem(item);
        });
        return result;
    }
}

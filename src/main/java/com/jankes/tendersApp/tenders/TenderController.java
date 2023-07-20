package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/tenders")
class TenderController {

    private final TenderService tenderService;
    private final TenderItemService tenderItemService;

    TenderController(TenderService tenderService, TenderItemService itemService) {
        this.tenderService = tenderService;
        this.tenderItemService = itemService;
    }

    @GetMapping("/{mdpId}")
    ResponseEntity<TenderResponse> findTender(@PathVariable String mdpId) {
        TenderDto tender = tenderService.findSingleTender(mdpId);
        List<TenderItemDto> items = tenderItemService.findItemsByTenderMdpId(mdpId);
        TenderResponse response = new TenderResponse(tender, tender.getPurchaser(), items, tender.getPersonInContact());
        if (tender == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<TenderDto>> findAll() {
        List<TenderDto> tenders = tenderService.findAllTenders();
        if (tenders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @GetMapping("/forPurchaser/{id}")
    ResponseEntity<List<TenderDto>> findAllForPurchaser(@PathVariable Long id){
        List<TenderDto> tenders = tenderService.findAllTendersForPurchaser(id);
        if (tenders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<List<TenderDto>> findTenderByTitle(@RequestParam(value = "name") String name) {
        List<TenderDto> tenders = tenderService.findAllTendersByTitle(name);
        if (tenders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TenderDto> create(@RequestBody TenderRequest toCreate) {
        TenderDto result = tenderService.saveTender(toCreate.getTenderDto(), toCreate.getPurchaserId(), toCreate.getTenderItems(), toCreate.getPersonInContactId());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<TenderDto> update(@PathVariable long id, @RequestBody TenderDto toUpdate) {
        if (id != toUpdate.getId() && toUpdate.getId() != 0) {
            throw new IllegalStateException("Id in URL is not equal to Id in request: " + id + " != " + toUpdate.getId());
        }
        //tenderService.saveTender(toUpdate);
        return ResponseEntity.noContent().build();
    }
}

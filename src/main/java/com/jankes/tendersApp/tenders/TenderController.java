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

    TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping("/{id}")
    ResponseEntity<TenderDto> findTender(@PathVariable long id){
        TenderDto tender = tenderService.findSingleTender(id);
        if(tender == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tender, HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<List<TenderDto>> findTenderByTitle(@RequestParam(value = "name") String name){
        List<TenderDto> tenders = tenderService.findAllTendersByTitle(name);
        if(tenders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tenders, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<TenderDto> create(@RequestBody TenderRequest toCreate) {
        TenderDto  result = tenderService.saveTender(toCreate.getTenderDto(), toCreate.getPurchaserDto(), toCreate.getTenderItems());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<TenderDto> update(@PathVariable long id, @RequestBody TenderDto toUpdate) {
        if(id != toUpdate.getId() && toUpdate.getId() != 0){
            throw new IllegalStateException("Id in URL is not equal to Id in request: " + id + " != " + toUpdate.getId());
        }
        //tenderService.saveTender(toUpdate);
        return ResponseEntity.noContent().build();
    }
}

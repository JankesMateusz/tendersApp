package com.jankes.tendersApp.purchasers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/purchasers")
class PurchaserController {

    private final PurchaserService purchaserService;

    PurchaserController(PurchaserService purchaserService) {
        this.purchaserService = purchaserService;
    }

    @GetMapping("/{id}")
    ResponseEntity<PurchaserDto> findPurchaser(@PathVariable long id){
        PurchaserDto purchaser = purchaserService.findPurchaser(id);
        if(purchaser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(purchaser, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    ResponseEntity<List<PurchaserDto>> findPurchasersByName(@PathVariable String name){
        List<PurchaserDto> purchasers = purchaserService.findByName(name);
        if(purchasers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchasers, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PurchaserDto> create(@RequestBody PurchaserDto toCreate) throws Exception{
        PurchaserDto result = purchaserService.createPurchaser(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<PurchaserDto> update(@PathVariable long id, @RequestBody PurchaserDto toUpdate) throws Exception{
        if(id != toUpdate.getId() && toUpdate.getId() != 0){
            throw new IllegalStateException("Id in URL is not equal to Id in request: " + id + " != " + toUpdate.getId());
        }
        purchaserService.createPurchaser(toUpdate);
        return ResponseEntity.noContent().build();
    }
}

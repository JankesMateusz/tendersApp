package com.jankes.tendersApp.purchasers;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PurchaserService {

    private final PurchaserRepository purchaserRepository;

    public PurchaserService(PurchaserRepository purchaserRepository){
        this.purchaserRepository = purchaserRepository;
    }

    public Purchaser findPurchaser(Long id){
        return purchaserRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Purchaser not found"));
    }
}

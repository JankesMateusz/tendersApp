//package com.jankes.tendersApp.purchasers;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//@Component("purchasersWarmup")
//public class Warmup implements ApplicationListener<ContextRefreshedEvent> {
//
//    private final PurchaserRepository purchaserRepository;
//
//    public Warmup(PurchaserRepository purchaserRepository) {
//        this.purchaserRepository = purchaserRepository;
//    }
//
//    @Override
//    public void onApplicationEvent(final ContextRefreshedEvent event){
//        if(purchaserRepository.count() == 0){
//            var purchaser = new Purchaser();
//
//            purchaser.setTypeOfAccount(TypeOfAccount.EDUK12);
//            purchaser.setOfficialName("Szkoła Podstawowa nr 1");
//            purchaser.setCity("Warszawa");
//            purchaser.setProvince("mazowieckie");
//            purchaser.setAddress("ul. testowa 1");
//            purchaser.setZipCode("01-100");
//
//            var purchaser2 = new Purchaser();
//
//            purchaser2.setTypeOfAccount(TypeOfAccount.DEFENCE);
//            purchaser2.setOfficialName("test");
//            purchaser2.setCity("test");
//            purchaser2.setProvince("test");
//            purchaser2.setAddress("ul. testowa 1");
//            purchaser2.setZipCode("01-100");
//
//            purchaserRepository.save(purchaser);
//            purchaserRepository.save(purchaser2);
//
//        }
//    }
//}

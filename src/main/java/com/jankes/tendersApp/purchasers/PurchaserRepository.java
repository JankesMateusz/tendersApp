package com.jankes.tendersApp.purchasers;

import java.util.List;
import java.util.Optional;

interface PurchaserRepository{

    Optional<Purchaser> findById(Long id);

    Optional<Purchaser> findByName(String name);

    List<Purchaser> findAll();

    List<Purchaser> findByNameIgnoreCaseContaining(String name);

    Purchaser save(Purchaser entity);

    void delete(Purchaser toDelete);

    boolean existsById(Long id);
}

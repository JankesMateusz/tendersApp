package com.jankes.tendersApp.purchasers;

import java.util.List;
import java.util.Optional;

interface PurchaserRepository{

    long count();

    Optional<Purchaser> findById(Long id);

    Optional<Purchaser> findByOfficialName(String name);

    List<Purchaser> findAll();

    List<Purchaser> findByOfficialNameIgnoreCaseContaining(String name);

    List<Purchaser> findByCityIgnoreCaseContaining(String city);

    Purchaser save(Purchaser entity);

    void delete(Purchaser toDelete);

    boolean existsById(Long id);
}

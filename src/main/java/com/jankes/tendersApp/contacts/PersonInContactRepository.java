package com.jankes.tendersApp.contacts;

import java.util.List;
import java.util.Optional;

public interface PersonInContactRepository {

    Optional<PersonInContact> findById(Long id);

    List<PersonInContact> findAll();

    List<PersonInContact> findAllByPurchaserId(Long id);

    PersonInContact save(PersonInContact personInContact);

    void delete(PersonInContact toDelete);

    boolean existsById(Long id);
}

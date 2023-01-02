package com.jankes.tendersApp.purchasers;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PurchaserServiceTest {

    @Test
    @Description("Saves new purchaser to repository")
    public void saveNewPurchaser() throws Exception{
        //given
        var repository = inMemoryPurchaserRepository();
        int countBefore = repository.count();
        //and
        var purchaserDto = mock(PurchaserDto.class);
        when(purchaserDto.getId()).thenReturn(1L);
        when(purchaserDto.getTypeOfAccount()).thenReturn("healthcare");
        // and
        var mapper = new PurchaserMapper();
        //system under test
        var service = new PurchaserService(repository, mapper);
        //when
        var result = service.createPurchaser(purchaserDto);
        assertThat(repository.count()).isNotEqualTo(countBefore);
        assertThat(result).isInstanceOf(PurchaserDto.class);
    }

    @Test
    public void findPurchaserThrowsException(){
        //given
        var repository = inMemoryPurchaserRepository();
        //system
        var mapper = new PurchaserMapper();
        var service = new PurchaserService(repository, mapper);

        //when
        Throwable t = catchThrowable(() -> service.findPurchaser(1L));

        assertThat(t).isInstanceOf(Exception.class);
        assertThat(t.getMessage()).isEqualTo("Purchaser not found");
    }

    @Test
    public void updatePurchaserEmail() throws Exception{
        //given
        var repository = inMemoryPurchaserRepository();
        //and
        var mapper = new PurchaserMapper();
        //and
        var purchaser = purchaserWith(1,"test", "testowe", "testowe", TypeOfAccount.DEFENCE, "test@test.pl");
        repository.save(purchaser);
        int countBefore = repository.count();
        //when
        purchaser.setEmail("test2@test.pl");
        //system under test
        var service = new PurchaserService(repository, mapper);
        //when
        service.createPurchaser(mapper.toDto(purchaser));
        //
        assertThat(repository.count()).isEqualTo(countBefore);
        assertThat(service.findPurchaser(1L).getEmail()).isEqualTo("test2@test.pl");
    }

    @Test
    public void updatePurchaserTypeOfAccount() throws Exception{
        //given
        var repository = inMemoryPurchaserRepository();
        //and
        var mapper = new PurchaserMapper();
        //and
        var purchaser = purchaserWith(1,"test", "testowe", "testowe", TypeOfAccount.DEFENCE, "test@test.pl");
        repository.save(purchaser);
        int countBefore = repository.count();
        //when
        purchaser.setTypeOfAccount(TypeOfAccount.EDUK12);
        //system under test
        var service = new PurchaserService(repository, mapper);
        //when
        service.createPurchaser(mapper.toDto(purchaser));
        //
        assertThat(repository.count()).isEqualTo(countBefore);
        assertThat(service.findPurchaser(1L).getTypeOfAccount()).isEqualTo("eduk12");
    }

    @Test
    public void findAllPurchasers(){
        //given
        var repository = inMemoryPurchaserRepository();
        //and
        var mapper = new PurchaserMapper();
        //and
        var purchaser = purchaserWith(1,"test", "testowe", "testowe", TypeOfAccount.DEFENCE, "test@test.pl");
        //and
        var purchaser2 = purchaserWith(2,"test2", "testowe2", "testowe2", TypeOfAccount.EDUK12, "test2@test.pl");
        //
        repository.save(purchaser);
        repository.save(purchaser2);
        int repoSize = repository.count();
        //system under test
        var service = new PurchaserService(repository, mapper);
        //when
        var result = service.findAllPurchasers();
        //assert
        assertThat(result.size()).isEqualTo(repoSize);
    }

    @Test
    public void findAllPurchasersByName(){
        //given
        var repository = inMemoryPurchaserRepository();
        //and
        var mapper = new PurchaserMapper();
        //and
        var purchaser = purchaserWith(1,"test", "testowe", "testowe", TypeOfAccount.DEFENCE, "test@test.pl");
        //and
        var purchaser2 = purchaserWith(2,"test2", "testowe2", "testowe2", TypeOfAccount.EDUK12, "test2@test.pl");
        //
        var purchaser3 = purchaserWith(3,"nowy", "nowe", "nowe", TypeOfAccount.EDUK12, "nowe@test.pl");
        repository.save(purchaser);
        repository.save(purchaser2);
        repository.save(purchaser3);
        //system under test
        var service = new PurchaserService(repository, mapper);
        //when
        var result = service.findByName("test");
        //
        assertThat(result.size()).isEqualTo(2);
    }

    private Purchaser purchaserWith(long id, String name, String city, String province, TypeOfAccount typeOfAccount, String email){
        var result = new Purchaser();
        result.setId(id);
        result.setOfficialName(name);
        result.setCity(city);
        result.setProvince(province);
        result.setTypeOfAccount(typeOfAccount);
        result.setEmail(email);
        return result;
    }

    private InMemoryPurchaserRepository inMemoryPurchaserRepository(){
        return new InMemoryPurchaserRepository();
    }

    private static class InMemoryPurchaserRepository implements PurchaserRepository{
        private long index = 0;
        private Map<Long, Purchaser> map = new HashMap<>();

        public int count(){
            return map.size();
        }

        @Override
        public Optional<Purchaser> findById(Long id) {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public Optional<Purchaser> findByOfficialName(String name) {
            return Optional.empty();
        }

        @Override
        public List<Purchaser> findAll() {
            return new ArrayList<>(map.values());
        }

        @Override
        public List<Purchaser> findByOfficialNameIgnoreCaseContaining(String name) {
            return map.values()
                    .stream()
                    .filter(value -> value
                            .getOfficialName()
                            .contains(name))
                    .collect(Collectors.toList());
        }

        @Override
        public Purchaser save(Purchaser entity) {
            if (entity.getId() == 0) {
                try {
                    var field = Purchaser.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, ++index);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            map.put(entity.getId(), entity);
            return entity;
        }

        @Override
        public void delete(Purchaser toDelete) {

        }

        @Override
        public boolean existsById(Long id) {
            return map.containsKey(id);
        }
    }
}

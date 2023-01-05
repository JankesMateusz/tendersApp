package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.PurchaserService;
import com.jankes.tendersApp.purchasers.TypeOfAccount;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TenderServiceTest {

    @Test
    @Description("Should return tender if it does exist in repository")
    public void findSingleTenderReturnsTender() {

        //given
        var repository = inMemoryTenderRepository();
        Set<TenderItem> items = new HashSet<>();
        repository.save(tenderWith(1L, "test", "www.test.pl", "01-01-2022", "08-01-2022", items));
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        // system
        var service = new TenderService(repository, null, mapper, purchaserService);
        // when
        var result = service.findSingleTender(1L);
        // then
        assertThat(result).isInstanceOf(TenderDto.class);
        assertThat(result.getTitle()).isEqualTo("test");
    }

    @Test
    @Description("Should throw exception when finds no tender")
    public void findSingleTenderThrowsException() {

        //given (empty repository)
        var repository = inMemoryTenderRepository();
        Set<TenderItem> items = new HashSet<>();
        repository.save(tenderWith(1L, "test", "www.test.pl", "01-01-2022", "08-01-2022", items));
        //and
        var mapper = new TenderMapper();
        // system
        var service = new TenderService(repository, null, mapper, null);
        // assert
        Throwable t = catchThrowable(() -> service.findSingleTender(2L));
        assertThat(t).isInstanceOf(Exception.class);
        assertThat(t.getMessage()).isEqualTo("Tender not found");
    }

    @Test
    @Description("Should save new tender to repository")
    public void saveNewTenderToRepository() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        int countBeforeTest = tenderRepository.count();
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        //and
        var tenderItemRepositoryMock = mock(TenderItemRepository.class);
        //and
        Set<TenderItem> items = new HashSet<>();
        var tender = tenderWith(1L, "test", "www.test.pl", "01-01-2022", "08-01-2022", items);
        //system under test:
        var toTest = new TenderService(tenderRepository, tenderItemRepositoryMock, mapper, purchaserService);
        //when
        toTest.saveTender(mapper.toDto(tender));
        //test
        assertThat(countBeforeTest).isNotEqualTo(tenderRepository.count());
    }

    @Test
    @Description("Should update existing tender")
    public void updateTenderWithoutItemsToRemoveOrUpdate() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        Set<TenderItem> items = new HashSet<>();
        var tender = tenderWith(1, "test", "www.test.pl", "01-01-2022", "08-01-2022", items);
        //and
        tenderRepository.save(tender);
        int countBeforeTest = tenderRepository.count();
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        //updated tender
        var updatedTender = tenderWith(1, "test 2", "www.test2.pl", "01-01-2022", "08-01-2022", items);
        //system under test
        var service = new TenderService(tenderRepository, null, mapper, purchaserService);
        //when
        service.saveTender(mapper.toDto(updatedTender));
        //assert
        assertThat(service.findSingleTender(1L).getTitle()).isEqualTo("test 2");
        assertThat(service.findSingleTender(1L).getLink()).isEqualTo("www.test2.pl");
        assertThat(countBeforeTest).isEqualTo(tenderRepository.count());
    }

    @Test
    public void findTendersContainingPhrase() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        //and
        Set<TenderItem> items = new HashSet<>();
        var tender = tenderWith(1, "test", "www.test.pl", "01-01-2022", "08-01-2022", items);
        var tender2 = tenderWith(2, "test23", "www.test.pl", "01-01-2022", "08-01-2022", items);
        var tender3 = tenderWith(3, "nothing", "www.test.pl", "01-01-2022", "08-01-2022", items);
        //and
        tenderRepository.save(tender);
        tenderRepository.save(tender2);
        tenderRepository.save(tender3);
        //system under test
        var service = new TenderService(tenderRepository, null, mapper, purchaserService);
        //when
        var result = service.findAllTendersByTitle("test");

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @Description("Should update tender and remove one tender item")
    public void updateTenderAndRemoveItemFromTenderItems() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        //and
        Set<TenderItem> items = new HashSet<>();
        Set<TenderItem> itemsForUpdate = new HashSet<>();
        items.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        items.add(tenderItemWith(2L, ItemCategory.AIO, 10));
        //and
        var tender = tenderWith(1, "test", "www.test.pl", "01-01-2022", "08-01-2022", items);
        tenderRepository.save(tender);
        int countBeforeTest = tenderRepository.count();

        //and
        var itemRepository = inMemoryTenderItemRepository();
        //updated tender
        itemsForUpdate.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        var updatedTender = tenderWith(1, "test 2", "www.test2.pl", "01-01-2022", "08-01-2022", itemsForUpdate);
        //system under test
        var service = new TenderService(tenderRepository, itemRepository, mapper, purchaserService);
        var updatedTenderDto = mapper.toDto(updatedTender);
        //when
        service.saveTender(updatedTenderDto);
        //assert
        assertThat(countBeforeTest).isEqualTo(tenderRepository.count());
        assertThat(service.findSingleTender(1L).getTenderItems().size()).isEqualTo(1);
    }

    @Test
    public void updateTenderAndUpdateTenderItems() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        var mapper = new TenderMapper();
        //and
        var purchaserService = mock(PurchaserService.class);
        //and
        Set<TenderItem> items = new HashSet<>();
        Set<TenderItem> itemsForUpdate = new HashSet<>();
        items.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        items.add(tenderItemWith(2L, ItemCategory.AIO, 10));
        //and
        var tender = tenderWith(1, "test", "www.test.pl", "01-01-2022", "08-01-2022", items);
        tenderRepository.save(tender);
        var tenderForUpdate = tenderWith(1, "test", "www.test.pl", "01-01-2022", "08-01-2022", itemsForUpdate);
        //and
        //and
        var itemRepository = inMemoryTenderItemRepository();
        //updated tender
        itemsForUpdate.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        itemsForUpdate.add(tenderItemWith(2L, ItemCategory.AIO, 100));
        itemsForUpdate.add(tenderItemWith(3L, ItemCategory.MFP, 10));
        //system under test
        var service = new TenderService(tenderRepository, itemRepository, mapper, purchaserService);
        var updatedTenderDto = mapper.toDto(tenderForUpdate);
        //when
        service.saveTender(updatedTenderDto);
        //
        assertThat(service.findSingleTender(1L).getTenderItems().size()).isEqualTo(3);
        assertThat(service.findSingleTender(1L).getTenderItems().get(2).getCategory()).isEqualTo("mfp");
        assertThat(service.findSingleTender(1L).getTenderItems().get(1).getQuantity()).isEqualTo(100);
    }


    private InMemoryTenderRepository inMemoryTenderRepository() {
        return new InMemoryTenderRepository();
    }

    private static class InMemoryTenderRepository implements TenderRepository {
        private long index = 0;
        private Map<Long, Tender> map = new HashMap<>();

        public int count() {
            return map.values().size();
        }

        @Override
        public List<Tender> findAllByPurchaserId(Long id) {
            return null;
        }

        @Override
        public boolean existsById(Long id) {

            return map.containsKey(id);
        }

        @Override
        public List<Tender> findAllByTitleIgnoreCaseContaining(String phrase) {
            return map.values().stream().filter(t -> t.getTitle()
                            .contains(phrase))
                    .collect(Collectors.toList());
        }

        @Override
        public Tender save(Tender entity) {
            if (entity.getId() == 0) {
                try {
                    var field = Tender.class.getDeclaredField("id");
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
        public Optional<Tender> findById(Long id) {
            return Optional.ofNullable(map.get(id));
        }
    }

    private InMemoryTenderItemRepository inMemoryTenderItemRepository() {
        return new InMemoryTenderItemRepository();
    }

    private static class InMemoryTenderItemRepository implements TenderItemRepository {

        private long index = 0;
        private Map<Long, TenderItem> map = new HashMap<>();

        @Override
        public Optional<TenderItem> findById(Long id) {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public TenderItem save(TenderItem entity) {
            if (entity.getId() == 0) {
                try {
                    var field = Tender.class.getDeclaredField("id");
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
        public void delete(TenderItem toRemove) {
            map.remove(toRemove.getId());
        }
    }

    private Tender tenderWith(long id, String title, String link, String publicationDate, String bidDate, Set<TenderItem> items) {

        var tenderForTest = new Tender();
        var purchaser = mock(com.jankes.tendersApp.purchasers.Purchaser.class);

        when(purchaser.getId()).thenReturn(1L);
        when(purchaser.getTypeOfAccount()).thenReturn(TypeOfAccount.DEFENCE);
        tenderForTest.setId(id);
        tenderForTest.setTitle(title);
        tenderForTest.setLink(link);
        tenderForTest.setPublicationDate(LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tenderForTest.setBidDate(LocalDate.parse(bidDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tenderForTest.setTenderItems(items);
        tenderForTest.setPurchaser(purchaser);
        tenderForTest.setBudget(TenderBudget.LESS_THAN_130000_PLN);
        tenderForTest.setReportDate(LocalDate.parse("01-01-1999", DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        return tenderForTest;
    }

    private TenderItem tenderItemWith(long id, ItemCategory category, int quantity) {

        var item = new TenderItem();

        item.setId(id);
        item.setCategory(category);
        item.setQuantity(quantity);

        return item;
    }
}
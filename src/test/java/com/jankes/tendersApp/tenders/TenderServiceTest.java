package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import com.jankes.tendersApp.purchasers.PurchaserService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TenderServiceTest {

    @Test
    @Description("Should return tender if it does exist in repository")
    public void findSingleTenderReturnsTender() {

        //given
        var tender = mock(Tender.class);
        var purchaser = mock(Purchaser.class);
        when(tender.getPurchaser()).thenReturn(purchaser);
        var repository = mock(TenderRepository.class);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tender));

        // system
        var service = new TenderService(repository, null, null);
        // when
        var result = service.findSingleTender(1L);
        // then
        assertThat(result).isInstanceOf(TenderDto.class);
    }

    @Test
    @Description("Should throw exception when finds no tender")
    public void findSingleTenderThrowsException() {

        //given (empty repository)
        var repository = inMemoryTenderRepository();

        // system
        var service = new TenderService(repository, null, null);

        // assert
        Throwable t = catchThrowable(() -> service.findSingleTender(1L));
        assertThat(t).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @Description("Should save new tender to repository")
    public void saveNewTenderToRepository() {
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        int countBeforeTest = tenderRepository.count();
        //and
        var tenderFactoryMock = mock(TenderFactory.class);
        //and
        var tenderItemRepositoryMock = mock(TenderItemRepository.class);
        //and
        var tenderDto = mock(TenderDto.class);
        //and
        var tender = mock(Tender.class);
        //and
        when(tender.toDto()).thenReturn(tenderDto);
        when(tenderFactoryMock.from(tenderDto)).thenReturn(tender);
        //system under test:
        var toTest = new TenderService(tenderRepository, tenderItemRepositoryMock, tenderFactoryMock);
        //when
        toTest.saveTender(tenderDto);
        //test
        assertThat(countBeforeTest).isNotEqualTo(tenderRepository.count());
    }

    @Test
    @Description("Should update existing tender")
    public void updateTenderWithoutItemsToRemoveOrUpdate() throws Exception{
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        Set<TenderItem> items = new HashSet<>();
        var tender = tenderWith(1, "test", "www.test.pl", "2022-12-01", "2022-12-10", items);
        //and
        tenderRepository.save(tender);
        int countBeforeTest = tenderRepository.count();
        //and
        var factory = mock(TenderFactory.class);
        //updated tender
        var updatedTender = tenderWith(1, "test 2", "www.test2.pl", "2022-12-01", "2022-12-10", items);
        var dto = updatedTender.toDto();
        when(factory.from(dto)).thenReturn(updatedTender);
        //system under test
        var service = new TenderService(tenderRepository,null, factory);
        //when
        service.saveTender(dto);
        //assert
        assertThat(service.findSingleTender(1L).getTitle()).isEqualTo("test 2");
        assertThat(service.findSingleTender(1L).getLink()).isEqualTo("www.test2.pl");
        assertThat(countBeforeTest).isEqualTo(tenderRepository.count());
    }

    @Test
    @Description("Should update tender and remove one tender item")
    public void updateTenderAndRemoveItemFromTenderItems() throws Exception{
        //given
        InMemoryTenderRepository tenderRepository = inMemoryTenderRepository();
        //and
        Set<TenderItem> items = new HashSet<>();
        Set<TenderItem> itemsForUpdate = new HashSet<>();
        items.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        items.add(tenderItemWith(2L, ItemCategory.AIO, 10));
        var tender = tenderWith(1, "test", "www.test.pl", "2022-12-01", "2022-12-10", items);
        tenderRepository.save(tender);
        int countBeforeTest = tenderRepository.count();
        //and
        var factory = mock(TenderFactory.class);
        //and
        var itemRepository = inMemoryTenderItemRepository();
        //updated tender
        itemsForUpdate.add(tenderItemWith(1L, ItemCategory.NOTEBOOK, 5));
        var updatedTender = tenderWith(1, "test 2", "www.test2.pl", "2022-12-01", "2022-12-10", itemsForUpdate);
        var updatedTenderDto = updatedTender.toDto();
        when(factory.from(updatedTenderDto)).thenReturn(updatedTender);
        //system under test
        var service = new TenderService(tenderRepository, itemRepository, factory);
        //when
        service.saveTender(updatedTenderDto);
        var result = service.saveTender(updatedTenderDto);
        //assert
        assertThat(countBeforeTest).isEqualTo(tenderRepository.count());
        assertThat(service.findSingleTender(1L).getTenderItems().size()).isEqualTo(1);
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
            return false;
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

    private InMemoryTenderItemRepository inMemoryTenderItemRepository() {return new InMemoryTenderItemRepository();}

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

    private Tender tenderWith(long id, String title, String link, String publicationDate, String bidDate, Set<TenderItem> items) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

        var tenderForTest = new Tender();
        tenderForTest.setId(id);
        tenderForTest.setTitle(title);
        tenderForTest.setLink(link);
        tenderForTest.setPublicationDate(format.parse(publicationDate));
        tenderForTest.setBidDate(format.parse(bidDate));
        tenderForTest.setTenderItems(items);

        return tenderForTest;
    }

    private TenderItem tenderItemWith(long id, ItemCategory category, int quantity){

        var item = new TenderItem();

        item.setId(id);
        item.setCategory(category);
        item.setQuantity(quantity);

        return item;
    }
}
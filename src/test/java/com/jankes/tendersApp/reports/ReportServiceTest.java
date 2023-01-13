package com.jankes.tendersApp.reports;

import com.jankes.tendersApp.purchasers.TypeOfAccount;
import com.jankes.tendersApp.reporting.ReportMapper;
import com.jankes.tendersApp.reporting.ReportService;
import com.jankes.tendersApp.tenders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    public InMemoryTenderRepository inMemoryTenderRepository;

    @BeforeEach
    public void setUpRepository(){
        inMemoryTenderRepository = new InMemoryTenderRepository();
        inMemoryTenderRepository.save(tenderWith(1L, "test 1", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022"));
        inMemoryTenderRepository.save(tenderWith(2L, "test 2", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022"));
        inMemoryTenderRepository.save(tenderWith(3L, "test 3", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022"));
        inMemoryTenderRepository.save(tenderWith(4L, "test 4", "www.test.pl", "02-01-2022", "09-01-2022", "03-01-2022"));
    }


    @Test
    public void findsReportsByDate(){
        List<Tender> tenders = List.of(
                tenderWith(1L, "test 1", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022"),
                tenderWith(2L, "test 2", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022"),
                tenderWith(3L, "test 3", "www.test.pl", "01-01-2022", "08-01-2022", "02-01-2022")
        );
        //given

        //and
        var mapper = new ReportMapper();
        //and
        var tenderService = new TenderService(null, null, null, null);
        //system under test
        ReportService reportService = new ReportService(tenderService, mapper);
        //when
        reportService.findReportByReportDate(LocalDate.parse("02-01-2022"));

    }

    private InMemoryTenderRepository inMemoryTenderRepository() {
        return new InMemoryTenderRepository();
    }

    private static class InMemoryTenderRepository implements TenderRepository {
        private long index = 0;
        private Map<Long, Tender> map = new HashMap<>();

        public long count() {
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
        public List<Tender> findAllByReportDate(LocalDate date) {
            return map.values().stream().filter(t -> t.getReportDate()
                    .equals(date))
                    .collect(Collectors.toList());
        }

        @Override
        public List<Tender> findAllByReportDateGreaterThanEqualAndReportDateLessThanEqual(LocalDate startDate, LocalDate endDate) {
            return null;
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

    private Tender tenderWith(long id, String title, String link, String publicationDate, String bidDate, String reportDate) {

        var tenderForTest = new Tender();
        var purchaser = mock(com.jankes.tendersApp.purchasers.Purchaser.class);

        when(purchaser.getId()).thenReturn(1L);
        when(purchaser.getTypeOfAccount()).thenReturn(TypeOfAccount.DEFENCE);
        tenderForTest.setId(id);
        tenderForTest.setTitle(title);
        tenderForTest.setLink(link);
        tenderForTest.setPublicationDate(LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tenderForTest.setBidDate(LocalDate.parse(bidDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        tenderForTest.setPurchaser(purchaser);
        tenderForTest.setBudget(TenderBudget.LESS_THAN_130000_PLN);
        tenderForTest.setReportDate(LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        return tenderForTest;
    }
}

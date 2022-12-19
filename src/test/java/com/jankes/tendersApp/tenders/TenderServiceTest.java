package com.jankes.tendersApp.tenders;

import com.jankes.tendersApp.purchasers.Purchaser;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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

    //TODO test z repoInMemory?
    @Test
    @Description("hhh")
    public void findSingleTenderThrowsException() {

        //given
        var repository = mock(TenderRepository.class);
        when(repository.findById(anyLong())).thenThrow(IllegalStateException.class);

        // system
        var service = new TenderService(repository, null, null);

        // assert
        Throwable t = catchThrowable(() -> service.findSingleTender(1L));
        assertThat(t).isInstanceOf(IllegalStateException.class);
    }


    private TenderRepository inMemoryTenderRepository() {

        return null;
    }
}
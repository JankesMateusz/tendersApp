package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.tenders.TenderService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {

    private final TenderService tenderService;
    private final ReportMapper reportMapper;

    public ReportService(TenderService tenderService, ReportMapper reportMapper) {
        this.tenderService = tenderService;
        this.reportMapper = reportMapper;
    }

    public List<ReportDto> findReportByReportDate(LocalDate date){
        return tenderService.findTendersByReportDate(date)
                .stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReportDto> findReportsBetweenReportDates(LocalDate start, LocalDate end){
        return tenderService.findTendersBetweenReportDates(start, end)
                .stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }
}

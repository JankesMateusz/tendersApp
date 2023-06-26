package com.jankes.tendersApp.reporting;

import com.jankes.tendersApp.tenders.Tender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate reportDate;
}

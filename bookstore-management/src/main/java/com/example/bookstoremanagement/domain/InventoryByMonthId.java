package com.example.bookstoremanagement.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InventoryByMonthId implements Serializable {
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "month")
    private Integer month;
    @Column(name = "year")
    private Integer year;
}

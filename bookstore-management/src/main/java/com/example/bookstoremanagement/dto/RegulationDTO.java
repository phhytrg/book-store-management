package com.example.bookstoremanagement.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RegulationDTO {
    private Long id;
    private String title;
    private Integer value;
}
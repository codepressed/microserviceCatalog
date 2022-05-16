package edu.uoc.epcsd.showcatalog.entities.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceDTO {

    private Date date;

    private String streamingURL;

    private Integer remainingSeats;

    private String status;

    private Long show;
}

package edu.uoc.epcsd.showcatalog.entities.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO {

    private String name;

    private String image;

    private String price;

    private String duration;

    private Integer capacity;

    private Date onSaleDate;

    private List<Long> categories;

}

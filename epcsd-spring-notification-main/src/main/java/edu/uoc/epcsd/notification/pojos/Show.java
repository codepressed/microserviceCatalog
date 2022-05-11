package edu.uoc.epcsd.notification.pojos;

import lombok.*;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    private Long id;

    private String name;

    private String image;

    private String price;

    private String duration;

    private Integer capacity;

    private Date onSaleDate;

    private List<Category> categories;
}

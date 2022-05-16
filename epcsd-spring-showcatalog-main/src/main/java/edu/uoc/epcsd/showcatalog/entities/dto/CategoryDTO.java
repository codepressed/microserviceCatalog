package edu.uoc.epcsd.showcatalog.entities.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String name;

    private String description;

}

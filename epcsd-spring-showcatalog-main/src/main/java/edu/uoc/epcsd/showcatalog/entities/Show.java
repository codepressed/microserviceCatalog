package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private String price;

    @Column(name = "duration")
    private String duration;

    @Column(name = "integer")
    private Integer capacity;

    @Column(name = "onSaleDate")
    private Date onSaleDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "show_categories",
            joinColumns = @JoinColumn(name = "id_show"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    private List<Category> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "show", cascade = CascadeType.MERGE)
    private List<Performance> performances;
}

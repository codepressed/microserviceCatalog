package edu.uoc.epcsd.showcatalog.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name")
        private Date date;

        @Column(name = "streamingURL")
        private String streamingURL;

        @Column(name = "remainingSeats")
        private String remainingSeats;

        @Column(name = "status")
        private String status;

        @ManyToOne(cascade = CascadeType.MERGE)
        private Show show;
}

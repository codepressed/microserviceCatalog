package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

        @Column(name = "date")
        private Date date;

        @Column(name = "streamingURL")
        private String streamingURL;

        @Column(name = "remainingSeats")
        private Integer remainingSeats;

        @Column(name = "status")
        private String status;

        @JsonIgnore
        @ManyToOne
        private Show show;
}

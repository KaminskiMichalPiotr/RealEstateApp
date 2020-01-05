package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private Date announcementDate;

    @Column
    @Type(type = "text")
    private String description;

    @Column
    @ElementCollection
    private List<String> picturesPaths;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "realEstate_id", referencedColumnName = "id", nullable = false)
    private RealEstate realEstate;

}

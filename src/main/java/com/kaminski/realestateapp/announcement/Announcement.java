package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne
    private User createdBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "realEstate_id", referencedColumnName = "id")
    private RealEstate realEstate;

}

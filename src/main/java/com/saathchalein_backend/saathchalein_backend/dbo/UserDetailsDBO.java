package com.saathchalein_backend.saathchalein_backend.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserDBO user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleDBO role = RoleDBO.USER;

    private String city;
    private String pincode;
    private String about;
    private String favourites;
}

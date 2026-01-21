package com.example.onlineFoodOrderingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}

//package com.airtravel.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "property_entity")
//public class Property {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "no_guest", nullable = false)
//    private Integer noGuest;
//
//    @Column(name = "no_bedrooms", nullable = false)
//    private Integer noBedrooms;
//
//    @Column(name = "no_bathrooms", nullable = false)
//    private Integer noBathrooms;
//
//    @Column(name = "nightly_price", nullable = false)
//    private Integer nightlyPrice;
//
//    @ManyToOne
//    @JoinColumn(name = "country_id")
//    private Country country;
//
//    @ManyToOne
//    @JoinColumn(name = "location_id")
//    private Location location;
//
//}
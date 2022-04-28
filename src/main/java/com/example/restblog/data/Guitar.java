package com.example.restblog.data;

import com.example.restblog.web.Brand;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name= "guitars")


public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String style;

    @Column(nullable = false)
    private String model;


    @Column(nullable = false)
    private int numberOfStrings;

    @Column(nullable = false)
    private String type;

    @ManyToOne
    @JsonIgnoreProperties({"guitars"}) // this is how you avoid infinite recursion
    private Brand brand;
}

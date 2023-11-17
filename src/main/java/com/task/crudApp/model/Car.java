package com.task.crudApp.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name="cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private String yearProduced;

}

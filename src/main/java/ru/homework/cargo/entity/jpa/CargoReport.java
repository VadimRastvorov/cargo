package ru.homework.cargo.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cargo_report", schema = "cargo")
public class CargoReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cargo_json")
    private String cargoJson;
    private String cargo;
    private String truck;
    private String parcels;
    @Column(name = "truck_count")
    private long truckCount;
    private String algorithm;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}

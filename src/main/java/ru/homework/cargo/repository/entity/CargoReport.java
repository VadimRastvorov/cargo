package ru.homework.cargo.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

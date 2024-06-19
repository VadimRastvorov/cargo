package ru.homework.cargo.repository.entity; //todo вынеси entity из repository

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor //todo можно заменить на @RequiredArgsConstructor
@AllArgsConstructor //todo можно заменить на @RequiredArgsConstructor
@Table(name = "parcels_type", schema = "cargo")
public class ParcelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private String parcel;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}

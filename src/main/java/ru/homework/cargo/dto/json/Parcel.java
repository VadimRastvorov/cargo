package ru.homework.cargo.dto.json; //todo зачем его выделить в json, переименуй в  entity и вынеси к dto

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Parcel {
    private final List<String> parcel;
}

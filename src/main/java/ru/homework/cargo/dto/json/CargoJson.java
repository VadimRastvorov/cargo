package ru.homework.cargo.dto.json; //todo зачем его выделить в json, переименуй в  entity и вынеси к dto

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonAutoDetect //todo для чего?
public class CargoJson {
    private final List<String> cargo;
}

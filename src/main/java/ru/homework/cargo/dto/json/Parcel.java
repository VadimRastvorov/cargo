package ru.homework.cargo.dto.json;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Parcel {
    private final List<String> parcel;
}

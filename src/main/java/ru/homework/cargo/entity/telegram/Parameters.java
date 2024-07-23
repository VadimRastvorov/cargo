package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Parameters {
    private String code;
    private String title;
    private long width;
    private long height;
    private String parcelsTitle;
    private String parcel;
    private String type;
    private String truckTitle;
    private int truckCount;
    private String algorithmName;
}

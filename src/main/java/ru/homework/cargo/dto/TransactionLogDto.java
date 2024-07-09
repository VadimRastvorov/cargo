package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//todo почему final?
public class TransactionLogDto {
    private final String response;
    private final String request;
    private final String source;
}

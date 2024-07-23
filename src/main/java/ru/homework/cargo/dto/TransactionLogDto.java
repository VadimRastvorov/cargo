package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionLogDto {
    private String response;
    private String request;
    private String source;
}

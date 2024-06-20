package ru.homework.cargo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.cargo.dto.TransactionLogDto;
import ru.homework.cargo.entity.jpa.TransactionLog;
import ru.homework.cargo.mapper.jpa.TransactionLogMapper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionLogMapperTest {

    private final TransactionLogMapper mapper = Mappers.getMapper(TransactionLogMapper.class);

    @Test
    void shouldMapRequestDtoToEntity() {
        TransactionLogDto transactionLogDto = TransactionLogDto.builder()
                .request("request")
                .response("response")
                .source("This is a new transactionLog")
                .build();
        TransactionLog transactionLog = mapper.toEntity(transactionLogDto);

        assertThat(transactionLog.getRequest()).isEqualTo("request");
        assertThat(transactionLog.getResponse()).isEqualTo("response");
        assertThat(transactionLog.getSource()).isEqualTo("This is a new transactionLog");
        assertThat(transactionLog.getCreatedDate()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void shouldMapRequestEntityToDto() {
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setRequest("request");
        transactionLog.setResponse("response");
        transactionLog.setSource("This is a new transactionLog");
        transactionLog.setCreatedDate(LocalDateTime.of(2023, 4, 12, 10, 30, 0));

        TransactionLogDto transactionLogDto = mapper.toDto(transactionLog);
        assertThat(transactionLogDto.getRequest()).isEqualTo("request");
        assertThat(transactionLogDto.getResponse()).isEqualTo("response");
        assertThat(transactionLogDto.getSource()).isEqualTo("This is a new transactionLog");
    }
}
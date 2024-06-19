package ru.homework.cargo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.cargo.dto.jpa.ResponseDto;
import ru.homework.cargo.repository.entity.Response;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseMapperTest {

    private final ResponseMapper mapper = Mappers.getMapper(ResponseMapper.class);

    @Test
    void shouldMapResponseDtoToEntity() {
        ResponseDto responseDto = ResponseDto.builder()
                .message("New Response")
                .source("This is a new response")
                .build();

        Response response = mapper.toEntity(responseDto);

        assertThat(response.getMessage()).isEqualTo("New Response");
        assertThat(response.getSource()).isEqualTo("This is a new response");
        assertThat(response.getCreatedDate()).isNotNull();
        assertThat(response.getCreatedDate()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void shouldMapResponseEntityToDto() {
        Response response = Response.builder()
                .message("New Response")
                .source("This is a new response")
                .createdDate(LocalDateTime.of(2023, 4, 12, 10, 30, 0))
                .build();

        ResponseDto responseDto = mapper.toDto(response);

        assertThat(responseDto.getMessage()).isEqualTo("New Response");
        assertThat(responseDto.getSource()).isEqualTo("This is a new response");
    }
}
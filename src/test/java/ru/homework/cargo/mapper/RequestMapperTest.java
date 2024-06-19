package ru.homework.cargo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.cargo.dto.domain.RequestDto;
import ru.homework.cargo.entity.Request;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RequestMapperTest {

    private final RequestMapper mapper = Mappers.getMapper(RequestMapper.class);

    @Test
    void shouldMapRequestDtoToEntity() {
        RequestDto requestDto = RequestDto.builder()
                .message("New Request")
                .source("This is a new request")
                .build();
        Request request = mapper.toEntity(requestDto);

        assertThat(request.getMessage()).isEqualTo("New Request");
        assertThat(request.getSource()).isEqualTo("This is a new request");
        assertThat(request.getCreatedDate()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void shouldMapRequestEntityToDto() {
        Request request = new Request();
        request.setMessage("New Request");
        request.setSource("This is a new request");
        request.setCreatedDate(LocalDateTime.of(2023, 4, 12, 10, 30, 0));

        RequestDto requestDto = mapper.toDto(request);
        assertThat(requestDto.getMessage()).isEqualTo("New Request");
        assertThat(requestDto.getSource()).isEqualTo("This is a new request");
    }
}
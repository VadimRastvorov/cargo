package ru.homework.cargo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.cargo.dto.CarcaseTypeDto;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.mapper.jpa.CarcaseTypeMapper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CarcaseTypeMapperTest {

    private final CarcaseTypeMapper mapper = Mappers.getMapper(CarcaseTypeMapper.class);

    @Test
    void shouldMapCarcaseTypeDtoToEntity() {

        CarcaseTypeDto carcaseTypeDto = CarcaseTypeDto.builder()
                .title("New Carcase Type")
                .width(10L)
                .height(20L)
                .code("ABC123")
                .build();

        CarcaseType carcaseType = mapper.toEntity(carcaseTypeDto);
        assertThat(carcaseType.getTitle()).isEqualTo("New Carcase Type");
        assertThat(carcaseType.getWidth()).isEqualTo(10L);
        assertThat(carcaseType.getCode()).isEqualTo("ABC123");
        assertThat(carcaseType.getCreatedDate()).isNotNull();
        assertThat(carcaseType.getCreatedDate()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void shouldMapCarcaseTypeEntityToDto() {
        CarcaseType carcaseType = new CarcaseType();
        carcaseType.setTitle("New Carcase Type");
        carcaseType.setCode("ABC123");
        carcaseType.setHeight(20L);
        carcaseType.setWidth(10L);
        carcaseType.setCreatedDate(LocalDateTime.of(2023, 4, 12, 10, 30, 0));

        CarcaseTypeDto carcaseTypeDto = mapper.toDto(carcaseType);
        assertThat(carcaseTypeDto.getTitle()).isEqualTo("New Carcase Type");
        assertThat(carcaseTypeDto.getWidth()).isEqualTo(10L);
        assertThat(carcaseTypeDto.getHeight()).isEqualTo(20L);
        assertThat(carcaseTypeDto.getCode()).isEqualTo("ABC123");
    }
}
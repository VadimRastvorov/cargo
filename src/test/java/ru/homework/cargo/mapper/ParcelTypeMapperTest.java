package ru.homework.cargo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.repository.entity.ParcelType;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ParcelTypeMapperTest {

    private final ParcelTypeMapper mapper = Mappers.getMapper(ParcelTypeMapper.class);

    @Test
    void shouldMapParcelTypeDtoToEntity() {
        ParcelTypeDto parcelTypeDto = ParcelTypeDto.builder()
                .title("New Parcel Type")
                .code("ABC123")
                .build();

        ParcelType parcelType = mapper.toEntity(parcelTypeDto);

        // Assert
        assertThat(parcelType.getTitle()).isEqualTo("New Parcel Type");
        assertThat(parcelType.getCode()).isEqualTo("ABC123");
        assertThat(parcelType.getCreatedDate()).isNotNull();
        assertThat(parcelType.getCreatedDate()).isInstanceOf(LocalDateTime.class);
    }

    @Test
    void shouldMapParcelTypeEntityToDto() {
        ParcelType parcelType = ParcelType.builder()
                .title("New Parcel Type")
                .code("ABC123")
                .createdDate(LocalDateTime.of(2023, 4, 12, 10, 30, 0))
                .build();

        ParcelTypeDto parcelTypeDto = mapper.toDto(parcelType);

        assertThat(parcelTypeDto.getTitle()).isEqualTo("New Parcel Type");
        assertThat(parcelTypeDto.getCode()).isEqualTo("ABC123");
    }
}
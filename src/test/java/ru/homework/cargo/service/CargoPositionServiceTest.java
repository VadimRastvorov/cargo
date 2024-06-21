package ru.homework.cargo.service;

import org.junit.jupiter.api.Test;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.mapper.CargoPositionMapper;
import ru.homework.cargo.service.loadingTruck.CargoPositionService;

import static org.assertj.core.api.Assertions.assertThat;

class CargoPositionServiceTest {

    CargoPositionService cargoPositionService = new CargoPositionService(new CargoPositionMapper());

    @Test
    void shouldFindStartPositionInTruck() {
        char[][] parcel = {
                {'s', 's'},
                {'s', 's'}
        };

        char[][] truck = new char[6][6];

        CargoPosition startPosition = cargoPositionService.createCargoPosition(parcel, truck);

        assertThat(startPosition.getHeight()).isEqualTo(0);
        assertThat(startPosition.getWidth()).isEqualTo(0);
        assertThat(startPosition.isFullTruck()).isFalse();
        assertThat(startPosition.getParcel()).isEqualTo(parcel);
    }

    @Test
    void shouldReturnFullTruckIfNoSpaceAvailable() {

        char[][] parcel = {
                {'r', 'r', 'r'},
                {'r', 'r', 'r'},
                {'r', 'r', 'r'}
        };

        char[][] truck = new char[3][3];
        truck[0][0] = 'e';
        truck[0][1] = 'e';
        truck[0][2] = 'e';
        truck[1][0] = 'e';
        truck[1][1] = 'e';
        truck[1][2] = 'e';

        CargoPosition startPosition = cargoPositionService.createCargoPosition(parcel, truck);

        assertThat(startPosition.getHeight()).isEqualTo(0);
        assertThat(startPosition.getWidth()).isEqualTo(0);
        assertThat(startPosition.isFullTruck()).isTrue();
        assertThat(startPosition.getParcel()).isEqualTo(parcel);
    }
}
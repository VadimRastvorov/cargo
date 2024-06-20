package ru.homework.cargo.service;

import org.junit.jupiter.api.Test;
import ru.homework.cargo.entity.CargoPosition;

import static org.assertj.core.api.Assertions.assertThat;

class CargoServiceTest {

    private final CargoService cargoService = new CargoService();

    @Test
    void shouldFindStartPositionInTruck() {
        char[][] parcel = {
                {'s', 's'},
                {'s', 's'}
        };

        char[][] truck = new char[6][6];

        CargoPosition startPosition = cargoService.returnStartPosition(parcel, truck);

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

        CargoPosition startPosition = cargoService.returnStartPosition(parcel, truck);

        assertThat(startPosition.getHeight()).isEqualTo(0);
        assertThat(startPosition.getWidth()).isEqualTo(0);
        assertThat(startPosition.isFullTruck()).isTrue();
        assertThat(startPosition.getParcel()).isEqualTo(parcel);
    }
}
package ru.homework.cargo.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruckServiceTest {

    private final TruckService truckService = new TruckService();

    @Test
    void shouldCreateMultipleTrucks() {
        List<char[][]> trucks = truckService.createTrucks(3, 4, 2);

        assertEquals(2, trucks.size());
        for (char[][] truck : trucks) {
            assertEquals(3, truck.length);
            for (char[] row : truck) {
                assertEquals(4, row.length);
            }
        }
    }
}
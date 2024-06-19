package ru.homework.cargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.CargoStartPositionDto;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@Service
public class CargoService {
    private final static char INITIALISED_VARIABLE= '\u0000';

    public CargoStartPositionDto returnStartPosition(char[][] parcel, char[][] truck) {
        log.info("метод returnStartPosition: {}", Arrays.deepToString(parcel));
        return IntStream.range(0, truck.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, truck[i].length)
                        .mapToObj(j -> new int[]{i, j}))
                .filter(pos -> {
                    int i = pos[0];
                    int j = pos[1];
                    return j + parcel[0].length <= truck[0].length &&
                            i + parcel.length <= truck.length &&
                            IntStream.range(i, truck.length)
                                    .allMatch(c -> truck[c][j] == INITIALISED_VARIABLE);
                })
                .findFirst()
                .map(pos -> createCargoStartPosition(parcel, pos[0], pos[1], false))
                .orElseGet(() -> createCargoStartPosition(parcel, 0, 0, true));
    }

    private CargoStartPositionDto createCargoStartPosition(char[][] parcel, int height, int width, boolean fullTruck) {
        return CargoStartPositionDto.builder()
                .height(height)
                .width(width)
                .fullTruck(fullTruck)
                .parcel(parcel)
                .build();
    }
}

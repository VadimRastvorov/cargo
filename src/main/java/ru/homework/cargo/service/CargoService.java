package ru.homework.cargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.util.Transformer;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@Service
public class CargoService {
    private final static char INITIALISED_VARIABLE = '\u0000';
    private final static int PARCEL_HEIGHT_DEFAULT = 0;
    private final static int PARCEL_WIDTH_DEFAULT = 0;

    public CargoPosition returnStartPosition(char[][] parcel, char[][] truck) {
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
                .map(pos -> Transformer.createCargoStartPosition(parcel, pos[0], pos[1], false))
                .orElseGet(() -> Transformer.createCargoStartPosition(parcel, PARCEL_HEIGHT_DEFAULT, PARCEL_WIDTH_DEFAULT, true));
    }
}

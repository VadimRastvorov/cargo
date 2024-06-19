package ru.homework.cargo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.homework.cargo.dto.CargoStartPositionDto;

import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
@Slf4j
public class TelegramTruck {
    private final static char INITIALISED_VARIABLE ;

    static {
        INITIALISED_VARIABLE = '\u0000'; //todo почему нельзя задекларировать сразу при инициализации?
    }

    private final int height;
    private final int width;
    @Setter
    private char[][] truck;

    public TelegramTruck(int height, int width) {
        this.height = height;
        this.width = width;
        this.truck = new char[height][width];
    }

    public boolean addParcelToTruck(char[][] parcel) {
        log.info("метод addParcelToTruck: {}", Arrays.deepToString(parcel));
        CargoStartPositionDto position = returnStartPosition(parcel);
        if (position.isFullTruck()) {
            return false;
        }
        IntStream.range(0, parcel.length)
                .forEach(i -> IntStream.range(0, parcel[i].length)
                        .forEach(j -> truck[i + position.getHeight()][j + position.getWidth()] = parcel[i][j]));
        return true;
    }

    private CargoStartPositionDto returnStartPosition(char[][] parcel) {
        return IntStream.range(0, truck.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, truck[i].length)
                        .mapToObj(j -> new int[]{i, j}))
                .filter(pos -> {
                    int i = pos[0];
                    int j = pos[1];
                    return j + parcel[0].length <= width &&
                            i + parcel.length <= height &&
                            IntStream.range(i, truck.length)
                                    .allMatch(c -> truck[c][j] == INITIALISED_VARIABLE);
                })
                .findFirst()
                .map(pos -> createCargoStartPosition(pos[0], pos[1], false))
                .orElseGet(() -> createCargoStartPosition(0, 0, true));
    }

    private CargoStartPositionDto createCargoStartPosition(int height, int width, boolean fullTruck) {
        return CargoStartPositionDto.builder()
                .height(height)
                .width(width)
                .fullTruck(fullTruck)
                .build();
    }
}

package ru.homework.cargo.service.loadingTruck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.service.LoadingParcelToTruckService;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@Service
public class LoadingParcelToTruckServiceImpl implements LoadingParcelToTruckService {
    public void loadParcelToTruck(char[][] parcel, char[][] truck, CargoPosition position) {
        log.info("метод addParcelToTruck: {}", Arrays.deepToString(parcel));
        IntStream.range(0, parcel.length)
                .forEach(i -> IntStream.range(0, parcel[i].length)
                        .forEach(j -> truck[i + position.getHeight()][j + position.getWidth()] = parcel[i][j]));
    }
}

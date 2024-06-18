package ru.homework.cargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.CargoStartPositionDto;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class TruckService {

    public char[][] addParcelToTruck(char[][] parcel, char[][] truck, CargoStartPositionDto position) {
        log.info("метод addParcelToTruck: {}", Arrays.deepToString(parcel));
        IntStream.range(0, parcel.length)
                .forEach(i -> IntStream.range(0, parcel[i].length)
                        .forEach(j -> truck[i + position.getHeight()][j + position.getWidth()] = parcel[i][j]));
        return truck;
    }

    public List<char[][]> createTrucks(CarcaseTypeDto carcaseTypeDto, int truckCount) {
        log.info("метод createTrucks: {}", carcaseTypeDto);
        return IntStream.range(0, (int) truckCount)
                .mapToObj(i -> new char[(int) carcaseTypeDto.getHeight()][(int) carcaseTypeDto.getWidth()])
                .collect(Collectors.toList());
    }

    private char[][] createNewCharArray(CarcaseTypeDto carcase) {
        log.info("метод createNewCharArray: {}", carcase);
        int height = (int) carcase.getHeight();
        int width = (int) carcase.getWidth();
        return new char[height][width];
    }
}

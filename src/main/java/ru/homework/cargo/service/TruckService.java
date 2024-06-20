package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.TruckLoad;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class TruckService {
    public List<char[][]> createTrucks(TruckLoad truckLoad) {
        log.info("метод createTrucks: {}", truckLoad);
        return IntStream.range(0, truckLoad.getTruckCount())
                .mapToObj(i -> new char[truckLoad.getHeight()][truckLoad.getWidth()])
                .collect(Collectors.toList());
    }
}

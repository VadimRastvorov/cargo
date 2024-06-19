package ru.homework.cargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class TruckService {

    public List<char[][]> createTrucks(int height, int width, int truckCount) {
        log.info("метод createTrucks: height = {}, width = {}, truckCount = {}", height, width, truckCount);
        return IntStream.range(0, truckCount)
                .mapToObj(i -> new char[height][width])
                .collect(Collectors.toList());
    }
}

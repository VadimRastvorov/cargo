package ru.homework.cargo.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.CargoStartPositionDto;
import ru.homework.cargo.dto.TruckDto;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.dto.telegram.LoadTruckDto;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.model.TelegramTruck;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoadingTruckService {
    private final ParcelTypeDataService parcelTypeDataService;
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final PrintLoadingTruck printLoadingTruck;
    private final TruckService truckService;
    private final CargoService cargoService;

    public String loadTrucksService(LoadTruckDto loadTruckDto) {
        log.info("метод loadTrucksService: {}", loadTruckDto);
        List<ParcelTypeDto> parcelTypeDtoList = getParcelTypeDtoList(loadTruckDto.getParcel());
        List<CarcaseTypeDto> carcaseTypeDtoList = getCarcaseTypeDtoList(loadTruckDto.getTruckTitle());
        validateCarcaseTypeDtoList(carcaseTypeDtoList, loadTruckDto.getTruckTitle());
        List<TelegramTruck> trucks = createTrucks(carcaseTypeDtoList, loadTruckDto.getTruckCount());
        loadParcelsIntoTrucks(parcelTypeDtoList, trucks);
        return printTruckLoading(trucks);
    }

    private List<ParcelTypeDto> getParcelTypeDtoList(String parcel) {
        return Arrays.stream(parcel.split(","))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(this::findParcelType)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<CarcaseTypeDto> getCarcaseTypeDtoList(String truckTitle) {
        return carcaseTypeDataService.findDataTitle(truckTitle);
    }

    private void validateCarcaseTypeDtoList(List<CarcaseTypeDto> carcaseTypeDtoList, String truckTitle) {
        if (carcaseTypeDtoList.isEmpty()) {
            throw new CustomException("В БД не найден автомобиль: " + truckTitle);
        }
    }

    private List<TelegramTruck> createTrucks(List<CarcaseTypeDto> carcaseTypeDtoList, long truckCount) {
        return IntStream.range(0, (int) truckCount)
                .mapToObj(i -> new TelegramTruck(
                        (int) carcaseTypeDtoList.get(0).getHeight(),
                        (int) carcaseTypeDtoList.get(0).getWidth()))
                .collect(Collectors.toList());
    }

    private void loadParcelsIntoTrucks(List<ParcelTypeDto> parcelTypeDtoList, List<TelegramTruck> trucks) {
        int truckIndex = 0;
        for (ParcelTypeDto parcelTypeDto : parcelTypeDtoList) {
            char[][] parcelChar = getParcelChars(parcelTypeDto.getParcel());
            trucks.get(truckIndex++ % trucks.size()).addParcelToTruck(parcelChar);
        }
    }

    private char[][] getParcelChars(String parcel) {
        return Arrays.stream(parcel.split(","))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private String printTruckLoading(List<TelegramTruck> trucks) {
        return printLoadingTruck.printStringFromListCharTrucks(trucks);
    }

    private List<ParcelTypeDto> findParcelType(String s) {
        List<ParcelTypeDto> parcelTypeDtoList = parcelTypeDataService.findDataTitle(s);
        if (parcelTypeDtoList.isEmpty()) throw new CustomException("В БД не найдена посылка: " + s);
        return parcelTypeDataService.findDataTitle(s);
    }
}

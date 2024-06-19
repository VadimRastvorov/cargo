package ru.homework.cargo.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.dto.jpa.CargoReportDto;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.dto.telegram.LoadTruckDto;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.CargoReportDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;
import ru.homework.cargo.service.json.JsonConvertService;
import ru.homework.cargo.type.AlgorithmType;
import ru.homework.cargo.util.Transformer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoadingTruckService {
    private final ParcelTypeDataService parcelTypeDataService;
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final PrintLoadingTruck printLoadingTruck;
    private final AlgorithmService algorithmService;
    private final JsonConvertService jsonConvertService;
    private final CargoReportDataService cargoReportDataService;

    public String loadTrucksService(LoadTruckDto loadTruckDto) {
        log.info("метод loadTrucksServiceVersion2: {}", loadTruckDto);
        List<String> parcelTypeDtoList = getParcelTypeDtoList(loadTruckDto.getParcel());
        List<CarcaseTypeDto> carcaseTypeDtoList = getCarcaseTypeDtoList(loadTruckDto.getTruckTitle());
        validateCarcaseTypeDtoList(carcaseTypeDtoList, loadTruckDto.getTruckTitle());
        List<char[][]> trucks = getTruckList(carcaseTypeDtoList, parcelTypeDtoList, loadTruckDto);
        String print = printTruckLoading(trucks);
        saveCargoReportData(trucks, print, loadTruckDto);
        return print;
    }

    private void saveCargoReportData(List<char[][]> trucks, String print, LoadTruckDto loadTruckDto) {
        cargoReportDataService.saveData(CargoReportDto.builder()
                .cargoJson(jsonConvertService.truckListJsonToJsonString(Transformer.trucksToTrucksJson(trucks)))
                .cargo(print)
                .truck(loadTruckDto.getTruckTitle())
                .parcels(loadTruckDto.getParcel())
                .truckCount(loadTruckDto.getTruckCount())
                .algorithm(loadTruckDto.getAlgorithmName())
                .build());
    }

    private List<char[][]> getTruckList(List<CarcaseTypeDto> carcaseTypeDtoList,
                                        List<String> parcelTypeDtoList,
                                        LoadTruckDto loadTruckDto) {
        return algorithmService.algorithmLoadTruck((int) carcaseTypeDtoList.get(0).getHeight(),
                (int) carcaseTypeDtoList.get(0).getWidth(),
                loadTruckDto.getTruckCount(),
                AlgorithmType.get(loadTruckDto.getAlgorithmName()),
                parcelTypeDtoList);
    }

    private List<String> getParcelTypeDtoList(String parcel) {
        return Arrays.stream(parcel.split(","))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(this::findParcelType)
                .flatMap(List::stream)
                .map(ParcelTypeDto::getParcel)
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

    private String printTruckLoading(List<char[][]> trucks) {
        return printLoadingTruck.printStringFromListCharTrucks(trucks);
    }

    private List<ParcelTypeDto> findParcelType(String parcel) {
        List<ParcelTypeDto> parcelTypeDtoList = parcelTypeDataService.findDataTitle(parcel);
        if (parcelTypeDtoList.isEmpty()) throw new CustomException("В БД не найдена посылка: " + parcel);
        return parcelTypeDataService.findDataTitle(parcel);
    }
}

package ru.homework.cargo.service.loadingTruck;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.jpa.ParcelType;
import ru.homework.cargo.repository.ParcelTypeRepository;
import ru.homework.cargo.service.ParcelService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final static String SPLIT_SYMBOL = ",";
    private final ParcelTypeRepository parcelTypeRepository;

    public List<String> splitParcels(String parcel) {
        return Arrays.stream(parcel.split(SPLIT_SYMBOL))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(parcelTypeRepository::findByTitleContainsIgnoreCaseOrderByIdAsc)
                .flatMap(List::stream)
                .map(ParcelType::getParcel)
                .collect(Collectors.toList());
    }
}

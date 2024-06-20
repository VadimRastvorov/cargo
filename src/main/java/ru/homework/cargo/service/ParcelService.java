package ru.homework.cargo.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.jpa.ParcelType;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.repository.ParcelTypeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelService {
    private final static String SPLIT_SYMBOL = ",";
    private final ParcelTypeRepository parcelTypeRepository;

    public List<String> getParcelList(String parcel) {
        return Arrays.stream(parcel.split(SPLIT_SYMBOL))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(this::findParcelType)
                .flatMap(List::stream)
                .map(ParcelType::getParcel)
                .collect(Collectors.toList());
    }

    private List<ParcelType> findParcelType(String parcel) {
        List<ParcelType> parcelTypeList = parcelTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(parcel);
        if (parcelTypeList.isEmpty()) throw new CustomException("В БД не найдена посылка: " + parcel);
        return parcelTypeList;
    }
}

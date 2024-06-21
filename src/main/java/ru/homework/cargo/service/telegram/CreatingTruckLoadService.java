package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.mapper.TruckLoadMapper;
import ru.homework.cargo.repository.CarcaseTypeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreatingTruckLoadService {
    private final ParcelService parcelService;
    private final CarcaseTypeRepository carcaseTypeRepository;
    private final TruckLoadMapper truckLoadMapper;

    public TruckLoad createTruckLoad(TelegramLoadTruck telegramLoadTruck) {
        List<String> parcels = parcelService.findParcels(telegramLoadTruck.getParcel());
        Optional.ofNullable(parcels)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CustomException("В БД не найдена посылка: " + parcels));

        List<CarcaseType> carcaseTypes =
                carcaseTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(telegramLoadTruck.getTruckTitle());

        Optional.ofNullable(carcaseTypes)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CustomException("В БД не найден автомобиль: " + telegramLoadTruck.getTruckTitle()));
        return truckLoadMapper.toEntity(telegramLoadTruck, carcaseTypes.get(0), parcels);
    }
}

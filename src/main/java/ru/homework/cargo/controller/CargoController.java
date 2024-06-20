package ru.homework.cargo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.cargo.dto.CarcaseTypeDto;
import ru.homework.cargo.dto.CargoReportDto;
import ru.homework.cargo.dto.ParcelTypeDto;
import ru.homework.cargo.dto.TransactionLogDto;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.CargoReportDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;
import ru.homework.cargo.service.jpa.TransactionLogDataService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/cargo")
public class CargoController {
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final ParcelTypeDataService parcelTypeDataService;
    private final TransactionLogDataService transactionLogDataService;
    private final CargoReportDataService cargoReportDataService;

    @GetMapping("/carcase")
    public ResponseEntity<List<CarcaseTypeDto>> findAllCarcaseType() {
        log.info("вызов метода findAllCarcaseType");
        return ResponseEntity.ok(carcaseTypeDataService.findAllData());
    }

    @GetMapping("/carcase-by-title/{title}")
    public ResponseEntity<List<CarcaseTypeDto>> findByCarcaseTypeTitle(@PathVariable String title) {
        log.info("вызов метода findByCarcaseTypeTitle");
        return ResponseEntity.ok(carcaseTypeDataService.findDataTitle(title));
    }

    @PostMapping(
            value = "/save-carcase-type", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarcaseTypeDto> saveCarcaseType(@RequestBody CarcaseTypeDto carcaseTypeDto) {
        log.info("вызов метода saveCarcaseType");
        return ResponseEntity.ok(carcaseTypeDataService.saveData(carcaseTypeDto));
    }

    @GetMapping("/parcels")
    public ResponseEntity<List<ParcelTypeDto>> findAllParcelType() {
        log.info("вызов метода findAllParcelType");
        return ResponseEntity.ok(parcelTypeDataService.findAllData());
    }

    @GetMapping("/parcels-by-title/{title}")
    public ResponseEntity<List<ParcelTypeDto>> findByParcelTypeTitle(@PathVariable String title) {
        log.info("вызов метода findByParcelTypeTitle");
        return ResponseEntity.ok(parcelTypeDataService.findDataTitle(title));
    }

    @GetMapping("/parcel/{id}")
    public ResponseEntity<ParcelTypeDto> findById(@PathVariable Long id) {
        log.info("вызов метода findById");
        return ResponseEntity.ok(parcelTypeDataService.findDataById(id));
    }

    @PostMapping(
            value = "/save-parcel-type", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ParcelTypeDto> saveParcelType(@RequestBody ParcelTypeDto parcelTypeDto) {
        log.info("вызов метода saveParcelType");
        return ResponseEntity.ok(parcelTypeDataService.saveData(parcelTypeDto));
    }

    @PostMapping(
            value = "/save-transaction-log", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TransactionLogDto> saveTransactionLog(@RequestBody TransactionLogDto transactionLogDto) {
        log.info("вызов метода saveTransactionLog");
        return ResponseEntity.ok(transactionLogDataService.saveData(transactionLogDto));
    }

    @PostMapping(
            value = "/save-cargo-report", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CargoReportDto> saveCargoReport(@RequestBody CargoReportDto cargoReportDto) {
        log.info("вызов метода saveCargoReport");
        return ResponseEntity.ok(cargoReportDataService.saveData(cargoReportDto));
    }
}

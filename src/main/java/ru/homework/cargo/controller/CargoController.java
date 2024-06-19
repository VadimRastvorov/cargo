package ru.homework.cargo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/cargo")
public class CargoController {
    @Autowired
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final ParcelTypeDataService parcelTypeDataService;

    @GetMapping("/carcase/all")
    public ResponseEntity<List<CarcaseTypeDto>> findAllCarcaseType() {
        log.info("вызов метода findAllCarcaseType");
        return ResponseEntity.ok(carcaseTypeDataService.findAllData());
    }

    @GetMapping("/parcels/all")
    public ResponseEntity<List<ParcelTypeDto>> findAllParcelType() {
        log.info("вызов метода findAllParcelType");
        return ResponseEntity.ok(parcelTypeDataService.findAllData());
    }

    @GetMapping("/parcelsByTitle/{title}")
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
            value = "/saveCarcaseType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarcaseTypeDto> saveCarcaseType(@RequestBody CarcaseTypeDto carcaseTypeDto) {
        log.info("вызов метода saveCarcaseType");
        return ResponseEntity.ok(carcaseTypeDataService.saveData(carcaseTypeDto));
    }

    @PostMapping(
            value = "/saveParcelType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ParcelTypeDto> saveParcelType(@RequestBody ParcelTypeDto parcelTypeDto) {
        log.info("вызов метода saveParcelType");
        return ResponseEntity.ok(parcelTypeDataService.saveData(parcelTypeDto));
    }
}

package ru.homework.cargo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CargoConfig {
    @Value("${file.directory.resources}")
    String directoryResources;

    @Value("${file.directory.json}")
    String fileDirectory;
}
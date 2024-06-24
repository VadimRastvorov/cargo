package ru.homework.cargo.service;

import java.util.List;

public interface BuilderImageService {
    String buildImageString(char[][] truck);

    String buildImageString(List<char[][]> trucks);
}

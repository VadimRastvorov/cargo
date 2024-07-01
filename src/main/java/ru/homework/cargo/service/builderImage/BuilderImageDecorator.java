package ru.homework.cargo.service.builderImage;

import ru.homework.cargo.service.BuilderImageService;

abstract class BuilderImageDecorator implements BuilderImageService {
    BuilderImageService builderImageService;

    public BuilderImageDecorator(BuilderImageService builderImageService) {
        this.builderImageService = builderImageService;
    }
}

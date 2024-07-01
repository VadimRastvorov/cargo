package ru.homework.cargo.service.builderImage;

import ru.homework.cargo.service.BuilderImageService;

import java.util.List;

public class BuilderImageTelegramDecorator extends BuilderImageDecorator {
    private final static String TELEGRAM_BOT_FORMAT_SYMBOL = "```";

    public BuilderImageTelegramDecorator(BuilderImageService builderImageService) {
        super(builderImageService);
    }

    @Override
    public String buildImageString(char[][] truck) {
        return wrap(builderImageService.buildImageString(truck));
    }

    @Override
    public String buildImageString(List<char[][]> trucks) {
        return wrap(builderImageService.buildImageString(trucks));
    }

    private String wrap(String string){
        return TELEGRAM_BOT_FORMAT_SYMBOL.concat(string.concat(TELEGRAM_BOT_FORMAT_SYMBOL));
    }
}

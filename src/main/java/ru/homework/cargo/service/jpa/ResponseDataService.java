package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.ResponseDto;
import ru.homework.cargo.mapper.ResponseMapper;
import ru.homework.cargo.repository.ResponseRepository;
import ru.homework.cargo.repository.entity.Response;

@Service
@RequiredArgsConstructor
public class ResponseDataService {
    private final ResponseRepository responseRepository;
    private final ResponseMapper responseMapper;

    public ResponseDto saveData(ResponseDto responseDto) {
        Response response = responseRepository.save(responseMapper.toEntity(responseDto));
        return responseMapper.toDto(response);
    }
}

package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.domain.ResponseDto;
import ru.homework.cargo.entity.Response;
import ru.homework.cargo.mapper.ResponseMapper;
import ru.homework.cargo.repository.ResponseRepository;

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

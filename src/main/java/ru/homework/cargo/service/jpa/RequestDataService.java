package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.domain.RequestDto;
import ru.homework.cargo.entity.Request;
import ru.homework.cargo.mapper.RequestMapper;
import ru.homework.cargo.repository.RequestRepository;

@Service
@RequiredArgsConstructor
public class RequestDataService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public void saveData(RequestDto requestDto) {
        Request request = requestRepository.save(requestMapper.toEntity(requestDto));
        requestMapper.toDto(request);
    }
}

package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.RequestDto;
import ru.homework.cargo.mapper.RequestMapper;
import ru.homework.cargo.repository.RequestRepository;
import ru.homework.cargo.repository.entity.Request;

@Service
@RequiredArgsConstructor
public class RequestDataService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public RequestDto saveData(RequestDto requestDto) {
        Request request = requestRepository.save(requestMapper.toEntity(requestDto));
        return requestMapper.toDto(request);
    }
}

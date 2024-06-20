package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.TransactionLogDto;
import ru.homework.cargo.entity.jpa.TransactionLog;
import ru.homework.cargo.mapper.jpa.TransactionLogMapper;
import ru.homework.cargo.repository.TransactionLogRepository;

@Service
@RequiredArgsConstructor
public class TransactionLogDataService {
    private final TransactionLogRepository transactionLogRepository;
    private final TransactionLogMapper transactionLogMapper;

    public TransactionLogDto saveData(TransactionLogDto transactionLogDto) {
        TransactionLog transactionLog = transactionLogRepository.save(transactionLogMapper.toEntity(transactionLogDto));
        return transactionLogMapper.toDto(transactionLog);
    }
}

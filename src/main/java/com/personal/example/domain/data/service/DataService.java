package com.personal.example.domain.data.service;

import com.personal.example.domain.data.repository.DataRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;
}

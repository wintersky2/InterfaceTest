package com.personal.example.domain.data.service;

import com.personal.example.domain.data.entity.Data;
import com.personal.example.domain.data.repository.DataRepository;
import com.personal.example.global.resultData.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;

    public ResultData<List<Data>> findAll() {
        List<Data> dataList = this.dataRepository.findAll();

        return ResultData.of("S-1", "success", dataList);
    }

    public ResultData<Data> findById(Long id) {
        Optional<Data> optionalData = this.dataRepository.findById(id);
        if (optionalData.isEmpty()) {
            return ResultData.of("F-1", "%d번 게시물은 존재하지 않습니다".formatted(id), null);
        }
        return ResultData.of("S-2", "success", optionalData.get());
    }

    @Transactional
    public ResultData create(String content) {
        Data data = Data.builder()
                .content(content)
                .build();

        this.dataRepository.save(data);

        return ResultData.of("S-3", "success", data);
    }

    public ResultData update(Long id, String content) {
        ResultData<Data> resultData = findById(id);
        if (resultData.isSuccess()) {
            Data data = resultData.getData().toBuilder()
                    .content(content)
                    .build();
            this.dataRepository.save(data);

            return ResultData.of("S-4", "success", data);
        }

        return resultData;
    }


    public ResultData delete(Long id) {
        ResultData<Data> resultData = findById(id);
        if (resultData.isSuccess()) {
            Data data = resultData.getData();

            this.dataRepository.delete(data);

            return ResultData.of("S-5", "delete success", data);
        }

        return resultData;
    }
}

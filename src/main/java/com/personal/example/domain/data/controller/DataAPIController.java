package com.personal.example.domain.data.controller;

import com.personal.example.domain.data.service.DataService;
import com.personal.example.global.resultData.ResultData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/datas")
public class DataAPIController {
    private final DataService dataService;


    @GetMapping("")
    public ResultData getDataList(){
        return this.dataService.findAll();
    }

    @GetMapping("/{id}")
    public ResultData getData(@PathVariable(value = "id")Long id){
        return this.dataService.findById(id);
    }

    @Data
    public static class CreateDataRequest{
        @NotBlank
        private String content;
    }

    @PostMapping("")
    public ResultData postData(@Valid @RequestBody CreateDataRequest createDataRequest){
        return this.dataService.create(createDataRequest.getContent());
    }

    @Data
    public static class UpdateDataRequest{
        @NotBlank
        private String content;
    }

    @PatchMapping("/{id}")
    public ResultData patchData(@PathVariable(value = "id")Long id, @Valid @RequestBody UpdateDataRequest updateDataRequest){
        return this.dataService.update(id,updateDataRequest.getContent());
    }

    @DeleteMapping("/{id}")
    public ResultData deleteData(@PathVariable(value = "id")Long id){
        return this.dataService.delete(id);
    }
}

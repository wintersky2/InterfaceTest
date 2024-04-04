package com.personal.example.domain.data.controller;

import com.personal.example.domain.data.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/data/api/v1")
public class DataController {
    private final DataService dataService;
}

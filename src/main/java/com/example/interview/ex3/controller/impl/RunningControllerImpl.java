package com.example.interview.ex3.controller.impl;

import com.example.interview.ex3.controller.RunningController;
import com.example.interview.ex3.service.RunningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/running")
public class RunningControllerImpl implements RunningController {

    private final RunningService runningService;

    @Override
    @PostMapping("/readLog")
    public ResponseEntity<String> readLog(@RequestBody String log) {
        return ResponseEntity.ok().body(runningService.readLog(log));
    }
}

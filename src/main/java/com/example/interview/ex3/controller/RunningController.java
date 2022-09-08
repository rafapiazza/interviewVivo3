package com.example.interview.ex3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface RunningController {

    @Operation(
            summary = "Read log from super heroes race",
            description = "Return position, code and super hero name, quantity laps and total time")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    ResponseEntity<String> readLog(@RequestBody String log);
}

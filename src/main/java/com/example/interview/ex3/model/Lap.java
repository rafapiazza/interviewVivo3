package com.example.interview.ex3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lap {
    //    private Long lapId;
    //    private LocalDateTime time;
    private String time;
    private int lapNumber;
    //    private Duration lapDuration;
    private String lapDuration;
    //    private Duration lapAverageVelocity;
    private String lapAverageVelocity;
}

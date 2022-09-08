package com.example.interview.ex3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RunningServiceTest {

    @Autowired
    private RunningService runningService;

    @Test
    void readLogInfoTest() {
        String log = "Hora;Super-Heroi;No Volta;Tempo Volta;Velocidade média da volta\n" +
                "23:49:08.277;038–Superman;1;1:02.852;44,275\n" +
                "23:49:11.075;002–Mercúrio;1;1:04.108;43,408\n" +
                "23:50:11.447;038–Superman;2;1:03.170;44,053\n" +
                "23:50:15.057;002–Mercúrio;2;1:03.982;43,493\n" +
                "23:51:14.216;038–Superman;3;1:02.769;44,334\n" +
                "23:51:19.044;002–Mercúrio;3;1:03.987;43,49\n" +
                "23:52:17.003;038–Superman;4;1:02.787;44,321\n" +
                "23:52:22.120;002–Mercúrio;4;1:03.076;44,118";

        String actual = runningService.readLog(log);

//        Assert apenas retornará verdadeiro se o objeto Race for criado com sucesso conforme a regra de negócio
        assertEquals("Super Hero race: \n" +
                "Position: 1  Code: 038 Name: Superman  Computed laps: 4  Total time: 4:11.578\n" +
                "Position: 2  Code: 002 Name: Mercúrio  Computed laps: 4  Total time: 4:15.153\n" +
                "\n" +
                "Bonus results:\n" +
                "Superman best lap: Lap: 3 with duration: 1:02.769 with average velocity: 44,334\n" +
                "Mercúrio best lap: Lap: 4 with duration: 1:03.076 with average velocity: 44,118\n" +
                "\n" +
                "Superman average of: 1:2.894 per lap\n" +
                "Mercúrio average of: 1:3.788 per lap\n" +
                "\n" +
                "Best race lap:\n" +
                "Superman lap number: 3 of duration: 1:02.769\n", actual);
    }

}

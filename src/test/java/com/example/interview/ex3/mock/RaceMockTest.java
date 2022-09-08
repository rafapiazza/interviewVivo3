package com.example.interview.ex3.mock;

import com.example.interview.ex3.model.Lap;
import com.example.interview.ex3.model.Race;
import com.example.interview.ex3.model.SuperHero;
import com.example.interview.ex3.util.RaceMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RaceMockTest {

    @Autowired
    private RaceMock raceMock;

    @Test
    void printRaceInfoTest() {
        Race race = new Race();
        List<SuperHero> superHeroes = new ArrayList<>();

        SuperHero papaleguas = new SuperHero();
        papaleguas.setName("Papaleguas");
        papaleguas.setCode("1");
        Lap lap1papa = new Lap("23:49:30.976", 1, "1:18.456", "35,47");
        Lap lap2papa = new Lap("23:50:37.987", 2, "1:07.011", "41,528");
        ArrayList<Lap> lapsPapa = new ArrayList<>();
        lapsPapa.add(lap1papa);
        lapsPapa.add(lap2papa);
        papaleguas.setLaps(lapsPapa);

        SuperHero superman = new SuperHero();
        superman.setName("Superman");
        superman.setCode("2");
        Lap lap1sup = new Lap("23:49:08.277", 1, "1:02.852", "44,275");
        Lap lap2sup = new Lap("23:50:11.447", 2, "1:03.170", "44,053");
        ArrayList<Lap> lapsSup = new ArrayList<>();
        lapsSup.add(lap1sup);
        lapsSup.add(lap2sup);
        superman.setLaps(lapsSup);

        superHeroes.add(papaleguas);
        superHeroes.add(superman);
        race.setSuperHeroes(superHeroes);

        String actual = raceMock.printRaceInfo(race);
//        Assert apenas retornará verdadeiro se todos os métodos privados em cadeia respeitarem as regras de negócio estabelecidas
        assertEquals("Super Hero race: \n" +
                "Position: 1  Code: 2 Name: Superman  Computed laps: 2  Total time: 2:6.22\n" +
                "Position: 2  Code: 1 Name: Papaleguas  Computed laps: 2  Total time: 2:25.467\n" +
                "\n" +
                "Bonus results:\n" +
                "Superman best lap: Lap: 1 with duration: 1:02.852 with average velocity: 44,275\n" +
                "Papaleguas best lap: Lap: 2 with duration: 1:07.011 with average velocity: 41,528\n" +
                "\n" +
                "Superman average of: 1:3.11 per lap\n" +
                "Papaleguas average of: 1:12.733 per lap\n" +
                "\n" +
                "Best race lap:\n" +
                "Superman lap number: 1 of duration: 1:02.852\n", actual);
    }

}

package com.example.interview.ex3.service;

import com.example.interview.ex3.model.Lap;
import com.example.interview.ex3.model.Race;
import com.example.interview.ex3.model.SuperHero;
import com.example.interview.ex3.util.RaceMock;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Service
public class RunningService {

    @Autowired
    private RaceMock raceMock;

    public String readLog(String log) {
        Race race = createRaceFromLog(log);
        return raceMock.printRaceInfo(race);
    }

    private Race createRaceFromLog(String data) {
        ArrayList<String> logRow = new ArrayList<>();
        data.lines().forEach(logRow::add);
        logRow.remove(0);

        Race race = new Race();

        ArrayList<SuperHero> superHeroesByLine = new ArrayList<>();
        logRow.forEach(line -> {
            SuperHero superHero = new SuperHero();
            String[] element = line.split(";");
            String[] superHeroCodeName = element[1].split("–");
            superHero.setCode(superHeroCodeName[0]);
            superHero.setName(superHeroCodeName[1]);

            ArrayList<Lap> laps = new ArrayList<>();
            Lap lap = new Lap();
            lap.setTime(element[0]);
            lap.setLapNumber(Integer.parseInt(element[2]));
            lap.setLapDuration(element[3]);
            lap.setLapAverageVelocity(element[4]);
            laps.add(lap);
            superHero.setLaps(laps);
            superHeroesByLine.add(superHero);
        });

        List<SuperHero> superHeroes = unifySuperHeroesLog(superHeroesByLine);
        race.setSuperHeroes(superHeroes);
        return race;
    }

    private List<SuperHero> unifySuperHeroesLog(List<SuperHero> superHeroesByLine) {
        ArrayList<SuperHero> filteredSuperHeroes = new ArrayList<>();
        Set<String> superHeroNamesAndCodes = new HashSet<>();
        superHeroesByLine.forEach(superHero -> {
            superHeroNamesAndCodes.add(superHero.getName() + ":" + superHero.getCode());

        });
        List<String> superHeroNamesIndexed = new ArrayList<>(superHeroNamesAndCodes);


        for (int index = 0; index < superHeroNamesAndCodes.size(); index++) {
            String[] superHeroPair = superHeroNamesIndexed.get(index).split(":");
            filteredSuperHeroes.add(new SuperHero(superHeroPair[0], superHeroPair[1]));
            for (SuperHero superHero : superHeroesByLine) {
                if (filteredSuperHeroes.get(index).getName().equals(superHero.getName())) {
                    int finalIndex = index;
                    superHero.getLaps().forEach(lap -> filteredSuperHeroes.get(finalIndex).getLaps().add(lap));
                }
            }
        }

        return filteredSuperHeroes;
    }
}

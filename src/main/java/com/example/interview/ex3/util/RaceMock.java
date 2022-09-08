package com.example.interview.ex3.util;

import com.example.interview.ex3.model.Lap;
import com.example.interview.ex3.model.Race;
import com.example.interview.ex3.model.SuperHero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RaceMock {
    public String printRaceInfo(Race race) {
        StringBuilder raceInfo = new StringBuilder();
        List<SuperHero> winnersOrdered;
        winnersOrdered = orderByTotalDurationSuperHeroes(race.getSuperHeroes());
        raceInfo.append(raceResults(winnersOrdered))
                .append(bonusResults(winnersOrdered));

        return String.valueOf(raceInfo);
    }

    private String raceResults(List<SuperHero> winnersOrdered) {
        StringBuilder result = new StringBuilder();
        result.append("Super Hero race: \n");
        AtomicInteger position = new AtomicInteger();
        winnersOrdered.forEach(superHero -> {
            position.getAndIncrement();
            result.append("Position: ")
                    .append(position.get())
                    .append(" ");

            result.append(" Code: ")
                    .append(superHero.getCode());

            result.append(" Name: ")
                    .append(superHero.getName())
                    .append(" ");

            result.append(" Computed laps: ")
                    .append(superHero.getLaps().get(superHero.getLaps().size() - 1).getLapNumber())
                    .append(" ");

            result.append(" Total time: ")
                    .append(transformFormatDuration(superHero.getTotalTime()))
                    .append("\n");
        });
        return String.valueOf(result);
    }

    private String bonusResults(List<SuperHero> winnersOrdered) {
        StringBuilder result = new StringBuilder();
        result.append("\nBonus results:\n");
        winnersOrdered.forEach(superHero -> {
            result.append(superHero.getName())
                    .append(" best lap: ")
                    .append(bestLapFormatFromSuperhero(bestLapFromSuperhero(superHero)))
                    .append("\n");
        });

        result.append("\n");
        winnersOrdered.forEach(superHero -> {
            result.append(superHero.getName())
                    .append(" average of: ")
                    .append(averageRaceVelocity(superHero))
                    .append(" per lap")
                    .append("\n");
        });

        result.append("\n")
                .append(bestLapFormatFromRace(winnersOrdered));

        return String.valueOf(result);

    }

    private String averageRaceVelocity(SuperHero superHero) {
        int averageRaceVelocity = superHero.getTotalTime() / superHero.getLaps().size();
        return transformFormatDuration(averageRaceVelocity);
    }

    private List<SuperHero> orderByTotalDurationSuperHeroes(List<SuperHero> superHeroes) {
        List<SuperHero> winnersOrdered = new ArrayList<>();
        HashMap<SuperHero, AtomicInteger> totalTimeSuperheroMap = new HashMap<>();
        superHeroes.forEach(superHero -> {
            AtomicInteger total = new AtomicInteger();
            superHero.getLaps().forEach(lap -> {
                total.addAndGet(transformLapDuration(lap.getLapDuration()));
            });
            totalTimeSuperheroMap.put(superHero, total);
        });
        LinkedHashMap<SuperHero, AtomicInteger> collect = totalTimeSuperheroMap.entrySet()
                .stream()
                .sorted(Comparator.<Map.Entry<SuperHero, AtomicInteger>>comparingInt(a -> a.getValue().get()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Set<Map.Entry<SuperHero, AtomicInteger>> ordered = collect.entrySet();
        ordered.forEach(superHeroAtomicIntegerEntry -> {
            SuperHero superHero = superHeroAtomicIntegerEntry.getKey();
            superHero.setTotalTime(superHeroAtomicIntegerEntry.getValue().get());
            winnersOrdered.add(superHero);
        });
        return winnersOrdered;
    }

    private List<SuperHero> orderByBestLapSuperHeroes(List<SuperHero> superHeroes) {
        List<SuperHero> winnersOrdered = new ArrayList<>();
        HashMap<SuperHero, AtomicInteger> bestLapSuperheroMap = new HashMap<>();
        superHeroes.forEach(superHero -> {
            AtomicInteger bestLapDuration = new AtomicInteger();
            bestLapDuration.set(transformLapDuration(bestLapFromSuperhero(superHero).getLapDuration()));
            bestLapSuperheroMap.put(superHero, bestLapDuration);
        });
        LinkedHashMap<SuperHero, AtomicInteger> collect = bestLapSuperheroMap.entrySet()
                .stream()
                .sorted(Comparator.<Map.Entry<SuperHero, AtomicInteger>>comparingInt(a -> a.getValue().get()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        Set<Map.Entry<SuperHero, AtomicInteger>> ordered = collect.entrySet();
        ordered.forEach(superHeroAtomicIntegerEntry -> {
            SuperHero superHero = superHeroAtomicIntegerEntry.getKey();
            superHero.setTotalTime(superHeroAtomicIntegerEntry.getValue().get());
            winnersOrdered.add(superHero);
        });
        return winnersOrdered;
    }

    private String transformFormatDuration(int timeInteger) {
        StringBuilder timeString = new StringBuilder();
        int minutes = timeInteger / 60000;
        int seconds = (timeInteger - minutes * 60000) / 1000;
        int milliseconds = (timeInteger - (minutes * 60000) - (seconds * 1000));
        timeString.append(minutes)
                .append(":");
        timeString.append(seconds)
                .append(".")
                .append(milliseconds);
        return String.valueOf(timeString);
    }

    private int transformLapDuration(String lapDuration) {
        String[] msTokens = lapDuration.split("\\.");
        String[] secondsTokens = msTokens[0].split(":");
        int minutes = Integer.parseInt(secondsTokens[0]);
        int seconds = Integer.parseInt(secondsTokens[1]);
        int milliseconds = Integer.parseInt(msTokens[1]);
        return 60000 * minutes + 1000 * seconds + milliseconds;
    }

    private Lap bestLapFromSuperhero(SuperHero superHero) {
        AtomicReference<Lap> bestLap = new AtomicReference<>(superHero.getLaps().get(0));

        superHero.getLaps().forEach(lap -> {
            if (transformLapDuration(lap.getLapDuration()) < transformLapDuration(bestLap.get().getLapDuration())) {
                bestLap.set(lap);
            }
        });

        return bestLap.get();
    }

    private String bestLapFormatFromSuperhero(Lap lap) {
        StringBuilder bestLapResult = new StringBuilder();
        bestLapResult.append("Lap: ")
                .append(lap.getLapNumber())
                .append(" with duration: ")
                .append(lap.getLapDuration())
                .append(" with average velocity: ")
                .append(lap.getLapAverageVelocity());

        return String.valueOf(bestLapResult);
    }

    private String bestLapFormatFromRace(List<SuperHero> superHeroes) {
        StringBuilder bestLapResult = new StringBuilder();
        List<SuperHero> orderedFromBestRace = orderByBestLapSuperHeroes(superHeroes);

        Lap bastRaceLap = bestLapFromSuperhero(orderedFromBestRace.get(0));

        bestLapResult.append("Best race lap:\n")
                .append(orderedFromBestRace.get(0).getName())
                .append(" lap number: ")
                .append(bastRaceLap.getLapNumber())
                .append(" of duration: ")
                .append(bastRaceLap.getLapDuration())
                .append("\n");
        return String.valueOf(bestLapResult);
    }

}

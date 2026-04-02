package MephiPackage.readers;

import MephiPackage.objects.Curse;
import MephiPackage.objects.Mission;
import MephiPackage.objects.Sorcerer;
import MephiPackage.objects.Technique;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TXTReader implements Reader {

    public TXTReader() {}

    @Override
    public Mission extract(File file) throws IOException {
        System.out.println("старый txt");
        List<String> lines = Files.readAllLines(file.toPath());
        if (lines.isEmpty()) {
            throw new IOException("Файл пуст");
        }

        Mission mission = new Mission();
        List<Curse> curses = new ArrayList<>();
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(": ", 2);
            if (parts.length < 2) continue;

            String key = parts[0].trim();
            String value = parts[1].trim();
            String baseKey = extractBaseKey(key);

            switch (baseKey) {
                case "missionId":
                    mission.setMissionId(value);
                    break;
                case "date":
                    mission.setDate(value);
                    break;
                case "location":
                    mission.setLocation(value);
                    break;
                case "outcome":
                    mission.setOutcome(value);
                    break;
                case "damageCost":
                    try {
                        mission.setDamageCost(Long.parseLong(value));
                    } catch (NumberFormatException e) {
                        throw new IOException("Ошибка в поле damageCost: '" + value + "' не является числом");
                    }
                    break;
                case "curse":
                    processCurseField(key, value, curses);
                    break;
                case "sorcerer":
                    processSorcererField(key, value, sorcerers);
                    break;
                case "technique":
                    processTechniqueField(key, value, techniques);
                    break;
            }
        }

        mission.setCurses(curses);
        mission.setSorcerers(sorcerers);
        mission.setTechniques(techniques);

        if (mission.getMissionId() == null || mission.getMissionId().isEmpty()) {
            throw new IOException("Файл не содержит обязательного поля missionId");
        }

        return mission;
    }

    private void processSorcererField(String key, String value, List<Sorcerer> sorcerers) {
        int index = parseIndex(key);
        String field = parseField(key);
        ensureSorcererListSize(sorcerers, index);

        Sorcerer sorcerer = sorcerers.get(index);

        switch (field) {
            case "name":
                sorcerer.setName(value);
                break;
            case "rank":
                sorcerer.setRank(value);
                break;
        }
    }

    private void processTechniqueField(String key, String value, List<Technique> techniques) {
        int index = parseIndex(key);
        String field = parseField(key);
        ensureTechniqueListSize(techniques, index);

        Technique technique = techniques.get(index);

        switch (field) {
            case "name":
                technique.setName(value);
                break;
            case "type":
                technique.setType(value);
                break;
            case "damage":
                technique.setDamage(Long.parseLong(value));
                break;
        }
    }

    private void processCurseField(String key, String value, List<Curse> curses) {
        if (key.contains("[")) {
            int index = parseIndex(key);
            String field = parseField(key);
            ensureCursesListSize(curses, index);

            Curse curse = curses.get(index);
            switch (field) {
                case "name":
                    curse.setName(value);
                    break;
                case "threatLevel":
                    curse.setThreatLevel(value);
                    break;
            }
        } else {
            int dotIndex = key.indexOf('.');
            if (dotIndex != -1) {
                String field = key.substring(dotIndex + 1);
                ensureCursesListSize(curses, 0);

                Curse curse = curses.get(0);
                switch (field) {
                    case "name":
                        curse.setName(value);
                        break;
                    case "threatLevel":
                        curse.setThreatLevel(value);
                        break;
                }
            }
        }
    }

    private String extractBaseKey(String key) {
        if (key.contains("[")) {
            return key.substring(0, key.indexOf('['));
        } else if (key.contains(".")) {
            return key.substring(0, key.indexOf('.'));
        }
        return key;
    }

    private void ensureSorcererListSize(List<Sorcerer> list, int index) {
        while (list.size() <= index) {
            list.add(new Sorcerer());
        }
    }

    private void ensureCursesListSize(List<Curse> list, int index) {
        while (list.size() <= index) {
            list.add(new Curse());
        }
    }

    private void ensureTechniqueListSize(List<Technique> list, int index) {
        while (list.size() <= index) {
            list.add(new Technique());
        }
    }

    private int parseIndex(String key) {
        String[] parts = key.split("\\[");  // ["sorcerer", "0].name"]
        String[] indexParts = parts[1].split("\\]");  //["0", ".name"]
        String indexStr = indexParts[0];

        return Integer.parseInt(indexStr);
    }

    private String parseField(String key) {
        String[] parts = key.split("\\[");           // ["sorcerer", "0].name"]
        String[] indexParts = parts[1].split("\\]"); // ["0", ".name"]

        return indexParts[1].substring(1); // без точкии в начале
    }
}
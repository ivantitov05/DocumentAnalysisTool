package MephiPackage.collection;

import MephiPackage.objects.Mission;
import MephiPackage.filter.MissionFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionCollection {

    private List<Mission> missions = new ArrayList<>();

    public void addMission(Mission mission) {
        if (mission != null) {
            missions.add(mission);
        }
    }

    public void addAll(List<Mission> missions) {
        this.missions.addAll(missions);
    }

    public List<Mission> getAll() {
        return new ArrayList<>(missions);
    }

    public int size() {
        return missions.size();
    }

    public boolean isEmpty() {
        return missions.isEmpty();
    }

    public Mission get(int index) {
        if (index >= 0 && index < missions.size()) {
            return missions.get(index);
        }
        return null;
    }

    public MissionCollection filter(MissionFilter filter) {
        MissionCollection result = new MissionCollection();
        for (Mission mission : missions) {
            if (filter.matches(mission)) {
                result.addMission(mission);
            }
        }
        return result;
    }

    public AggregatedStatistics getStatistics() {
        AggregatedStatistics stats = new AggregatedStatistics();
        stats.setTotalMissions(missions.size());

        long successCount = 0;
        long failureCount = 0;
        long partialCount = 0;
        long totalDamage = 0;
        Map<String, Integer> outcomeMap = new HashMap<>();

        for (Mission mission : missions) {
            String outcome = mission.getOutcome();
            if ("SUCCESS".equals(outcome)) successCount++;
            else if ("FAILURE".equals(outcome)) failureCount++;
            else if ("PARTIAL".equals(outcome)) partialCount++;

            outcomeMap.put(outcome, outcomeMap.getOrDefault(outcome, 0) + 1);
            totalDamage += mission.getDamageCost();
        }

        stats.setSuccessCount((int) successCount);
        stats.setFailureCount((int) failureCount);
        stats.setPartialCount((int) partialCount);
        stats.setTotalDamage(totalDamage);
        stats.setOutcomeDistribution(outcomeMap);

        return stats;
    }
}
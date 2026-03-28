package MephiPackage.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mission {

    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;

    private List<Curse> curses;
    private List<Sorcerer> sorcerers;
    private List<Technique> techniques;
    private String comment;

    private EconomicAssessment economicAssessment;      // экономическая оценка
    private CivilianImpact civilianImpact;              // влияние на гражданских
    private EnemyActivity enemyActivity;                // поведение противника
    private EnvironmentConditions environmentConditions; // условия среды
    private List<OperationTimeline> operationTimeline;  // хронология событий
    private List<String> operationTags;                 // теги миссии
    private List<String> supportUnits;                  // вспомогательные подразделения
    private List<String> recommendations;               // рекомендации
    private String notes;                               // примечания
    private List<String> artifactsRecovered;            // найденные артефакты
    private List<String> evacuationZones;               // зоны эвакуации
    private List<String> statusEffects;                 // эффекты/состояния

    public Mission() {}

    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }

    public long getDamageCost() { return damageCost; }
    public void setDamageCost(long damageCost) { this.damageCost = damageCost; }

    public List<Curse> getCurses() { return curses; }
    public void setCurses(List<Curse> curses) { this.curses = curses; }

    public List<Sorcerer> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<Sorcerer> sorcerers) { this.sorcerers = sorcerers; }

    public List<Technique> getTechniques() { return techniques; }
    public void setTechniques(List<Technique> techniques) { this.techniques = techniques; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public EconomicAssessment getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        this.economicAssessment = economicAssessment;
    }

    public CivilianImpact getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpact civilianImpact) {
        this.civilianImpact = civilianImpact;
    }

    public EnemyActivity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivity enemyActivity) {
        this.enemyActivity = enemyActivity;
    }

    public EnvironmentConditions getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        this.environmentConditions = environmentConditions;
    }

    public List<OperationTimeline> getOperationTimeline() { return operationTimeline; }
    public void setOperationTimeline(List<OperationTimeline> operationTimeline) {
        this.operationTimeline = operationTimeline;
    }

    public List<String> getOperationTags() { return operationTags; }
    public void setOperationTags(List<String> operationTags) {
        this.operationTags = operationTags;
    }

    public List<String> getSupportUnits() { return supportUnits; }
    public void setSupportUnits(List<String> supportUnits) {
        this.supportUnits = supportUnits;
    }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public void setArtifactsRecovered(List<String> artifactsRecovered) {
        this.artifactsRecovered = artifactsRecovered;
    }

    public List<String> getEvacuationZones() { return evacuationZones; }
    public void setEvacuationZones(List<String> evacuationZones) {
        this.evacuationZones = evacuationZones;
    }

    public List<String> getStatusEffects() { return statusEffects; }
    public void setStatusEffects(List<String> statusEffects) {
        this.statusEffects = statusEffects;
    }


    public Curse getCurse() {
        if (curses != null && !curses.isEmpty()) {
            return curses.get(0);
        }
        return null;
    }

    public void setCurse(Curse curse) {
        if (this.curses == null) {
            this.curses = new java.util.ArrayList<>();
        }
        this.curses.clear();
        if (curse != null) {
            this.curses.add(curse);
        }
    }
}
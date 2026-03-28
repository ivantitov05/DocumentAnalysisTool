package MephiPackage.objects;

import java.util.ArrayList;
import java.util.List;

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

    public Mission() {}


    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Curse getCurse() {
        if (curses != null && !curses.isEmpty()) {
            return curses.get(0);
        }
        return null;
    }

    public void setCurse(Curse curse) {
        if (this.curses == null) {
            this.curses = new ArrayList<>();
        }
        this.curses.clear();
        if (curse != null) {
            this.curses.add(curse);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public long getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(long damageCost) {
        this.damageCost = damageCost;
    }

    public List<Curse> getCurses() {
        return curses;
    }

    public void setCurses(List<Curse> curses) {  // ← этого не хватало
        this.curses = curses;
    }

    public List<Sorcerer> getSorcerers() {
        return sorcerers;
    }

    public void setSorcerers(List<Sorcerer> sorcerers) {  // ← этого не хватало
        this.sorcerers = sorcerers;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTechniques(List<Technique> techniques) {  // ← этого не хватало
        this.techniques = techniques;
    }
}
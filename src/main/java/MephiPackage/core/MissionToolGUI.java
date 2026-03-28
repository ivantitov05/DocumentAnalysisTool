package MephiPackage.core;

import MephiPackage.objects.*;

import javax.swing.*;
import java.awt.*;

public class MissionToolGUI extends JFrame {
    private JTextArea textArea;

    public MissionToolGUI(Mission mission){
        setTitle("Информация о миссии");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        textArea.setText(missionInfo(mission));
    }

    private String missionInfo(Mission mission){
        String str = "";
        str += "Информация о миссии\n";
        str += "  ID миссии: " + mission.getMissionId() + "\n";
        str += "  Дата: " + mission.getDate() + "\n";

        if (mission.getComment() != null && !mission.getComment().isEmpty()) {
            str += "  Комментарий: " + mission.getComment() + "\n";
        }

        str += "Проклятия\n";
        if (mission.getCurses() != null && !mission.getCurses().isEmpty()) {
            for (int i = 0; i < mission.getCurses().size(); i++) {
                Curse c = mission.getCurses().get(i);
                str += "  " + (i + 1) + ". " + nullSafe(c.getName()) + "\n";
                str += "     Уровень: " + nullSafe(c.getThreatLevel()) + "\n";
            }
        } else {
            str += "  (нет)\n";
        }
        str += "\n";

        str += "Участники\n";
        if (mission.getSorcerers() != null && !mission.getSorcerers().isEmpty()) {
            for (int i = 0; i < mission.getSorcerers().size(); i++) {
                Sorcerer s = mission.getSorcerers().get(i);
                str += "  " + (i + 1) + ". " + nullSafe(s.getName()) + "\n";
                str += "     Ранг: " + nullSafe(s.getRank()) + "\n";
            }
        } else {
            str += "  (нет)\n";
        }
        str += "\n";

        str += "Техники\n";
        if (mission.getTechniques() != null && !mission.getTechniques().isEmpty()) {
            for (int i = 0; i < mission.getTechniques().size(); i++) {
                Technique t = mission.getTechniques().get(i);
                str += "  " + (i + 1) + ". " + nullSafe(t.getName()) + "\n";
                str += "     Тип: " + nullSafe(t.getType()) + "\n";
                str += "     Владелец: " + nullSafe(t.getOwner()) + "\n";
                str += "     Урон: " + t.getDamage() + "\n";
            }
        }

        return str;
    }

    private String nullSafe(String str) {
        return str != null ? str : "—";
    }
}

package MephiPackage.core;

import MephiPackage.objects.Mission;
import MephiPackage.utils.FileChooser;
import MephiPackage.utils.MissionPrinter;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MissionTool {
    public static void main(String[] args) {
        File file = FileChooser.selectFile();
        if (file == null) return;

        MissionReader missionReader = new MissionReader();

        try {
            Mission mission = missionReader.readMission(file);

            if (mission != null) {
                MissionPrinter.print(mission);
                new MissionToolGUI(mission).setVisible(true);
            }

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Ошибка чтения файла:\n" + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
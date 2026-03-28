package MephiPackage.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {

    public static File selectFile() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Файлы миссий (*.json, *.xml, *.txt)", "json", "xml", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Выбран файл: " + selectedFile.getAbsolutePath());
            return selectedFile;
        } else {
            System.out.println("Файл не выбран");
            return null;
        }
    }
}
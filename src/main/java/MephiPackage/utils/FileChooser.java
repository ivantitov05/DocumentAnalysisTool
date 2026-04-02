package MephiPackage.utils;

import MephiPackage.utils.FileFormat;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FileChooser {

    public static File selectFile() {
        JFileChooser fileChooser = new JFileChooser();

        List<String> extensions = FileFormat.getAllExtensions();

        if (extensions != null && !extensions.isEmpty()) {
            List<String> validExtensions = extensions.stream()
                    .filter(ext -> ext != null && !ext.isEmpty())
                    .collect(Collectors.toList());

            if (!validExtensions.isEmpty()) {
                String[] extArray = validExtensions.toArray(new String[0]);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        FileFormat.getDescription(),
                        extArray
                );
                fileChooser.setFileFilter(filter);
            }
        }

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
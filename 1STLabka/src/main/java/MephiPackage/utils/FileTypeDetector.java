package MephiPackage.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileTypeDetector {
    public static String checkType(File file) {
        try {
            String content = Files.readString(file.toPath()).trim();
            if (content.isEmpty()) {
                return "txt";
            }

            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                jsonMapper.readTree(file);
                return "json";
            } catch (Exception e) {
                try {
                    XmlMapper xmlMapper = new XmlMapper();
                    xmlMapper.readTree(file);
                    return "xml";
                } catch (Exception ex) {
                    return "txt";
                }
            }
        } catch (IOException e) {
            return "txt";
        }
    }
}
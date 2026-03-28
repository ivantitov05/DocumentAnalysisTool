package MephiPackage.readers;

import MephiPackage.objects.Mission;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XMLReader implements Reader {


    public XMLReader(){}

    @Override
    public Mission extract(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        Mission mission = xmlMapper.readValue(file, Mission.class);

        if (mission.getMissionId() == null || mission.getMissionId().isEmpty()) {
            throw new IOException("XML файл не содержит missionId");
        }

        return mission;
    }
}

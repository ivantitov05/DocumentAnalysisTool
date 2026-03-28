package MephiPackage.readers;

import MephiPackage.objects.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReader implements Reader {

    File file;
    public JSONReader(File file) throws IOException {
        this.file = file;
    }

    @Override
    public Mission extract(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Mission mission = mapper.readValue(file, Mission.class);

        if (mission.getMissionId() == null || mission.getMissionId().isEmpty()) {
            throw new IOException("Файл не содержит обязательного поля missionId");
        }

        return mission;
    }
}

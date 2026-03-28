package MephiPackage.core;

import MephiPackage.objects.Mission;
import MephiPackage.utils.FileTypeDetector;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MissionReader {

    private static final Map<String, Supplier<ReaderCreator>> CREATORS = new HashMap<>();

    static {
        CREATORS.put("json", JSONReaderCreator::new);
        CREATORS.put("xml", XMLReaderCreator::new);
        CREATORS.put("txt", TXTReaderCreator::new);
    }

    public Mission readMission(File file) throws IOException {
        String type = FileTypeDetector.checkType(file);

        Supplier<ReaderCreator> creatorSupplier = CREATORS.get(type);
        if (creatorSupplier == null) {
            throw new IOException("Неподдерживаемый формат: " + type);
        }

        ReaderCreator creator = creatorSupplier.get();
        return creator.readMission(file);
    }
}
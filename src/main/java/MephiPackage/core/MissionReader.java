package MephiPackage.core;

import MephiPackage.objects.Mission;
import MephiPackage.utils.FileFormat;
import MephiPackage.utils.FileTypeDetector;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.function.Supplier;

public class MissionReader {

    private static final EnumMap<FileFormat, Supplier<ReaderCreator>> CREATORS = new EnumMap<>(FileFormat.class);

    static {
        CREATORS.put(FileFormat.JSON, JSONReaderCreator::new);
        CREATORS.put(FileFormat.XML, XMLReaderCreator::new);
        CREATORS.put(FileFormat.TXT, TXTReaderCreator::new);
        CREATORS.put(FileFormat.YAML, YAMLReaderCreator::new);
        CREATORS.put(FileFormat.INITXT, IniTXTReaderCreator::new);
        CREATORS.put(FileFormat.OLDTXT, TXTReaderCreator::new);
    }

    public Mission readMission(File file) throws IOException {
        FileFormat format = FileTypeDetector.checkType(file);

        Supplier<ReaderCreator> creatorSupplier = CREATORS.get(format);
        if (creatorSupplier == null) {
            throw new IOException("Неподдерживаемый формат: " + format.getFormatId());
        }

        ReaderCreator creator = creatorSupplier.get();
        return creator.readMission(file);
    }
}
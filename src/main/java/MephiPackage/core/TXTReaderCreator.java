package MephiPackage.core;

import MephiPackage.readers.Reader;
import MephiPackage.readers.TXTReader;

public class TXTReaderCreator extends ReaderCreator{

    @Override
    public Reader createReader(){
        Reader reader = new TXTReader();
        return reader;
    }
}

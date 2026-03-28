package MephiPackage.core;

import MephiPackage.readers.JSONReader;
import MephiPackage.readers.Reader;

public class JSONReaderCreator extends ReaderCreator{

    @Override
    public Reader createReader(){
        Reader reader = new JSONReader();
        return reader;
    }
}

package MephiPackage.core;

import MephiPackage.readers.Reader;
import MephiPackage.readers.XMLReader;

public class XMLReaderCreator extends ReaderCreator{

    @Override
    public Reader createReader(){
        Reader reader = new XMLReader();
        return reader;
    }
}

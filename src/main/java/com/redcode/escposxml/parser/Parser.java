package com.redcode.escposxml.parser;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.InputStream;

public class Parser {

    private XStream xStream;

    public Parser() {
        this.xStream = new XStream(new DomDriver());
        setup();
    }

    public Document unmarshall(InputStream xmlStream) {
        return (Document) xStream.fromXML(xmlStream);
    }

    private void setup() {
        this.xStream.alias("document", Document.class);
        this.xStream.alias("line", Line.class);

        this.xStream.aliasAttribute(Line.class, "align", "align");
        this.xStream.aliasAttribute(Line.class, "font", "font");
        this.xStream.aliasAttribute(Line.class, "cut", "cut");
        this.xStream.aliasAttribute(Line.class, "feed", "feed");

        this.xStream.addImplicitCollection(Document.class, "lines");

        this.xStream.registerConverter(new LineConverter());
    }
}

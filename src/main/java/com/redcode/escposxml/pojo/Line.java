package com.redcode.escposxml.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Line {

    @Attribute(name = "id")
    public String id;

    @Attribute(name = "align")
    public String alignment;

    @Attribute(name = "font")
    public String font;

    @Attribute(name = "cut")
    public String cut;

    @Attribute(name = "feed")
    public Integer feed;

    @Element(name = "text")
    public String text;
}
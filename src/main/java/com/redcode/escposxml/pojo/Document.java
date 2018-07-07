package com.redcode.escposxml.pojo;

import com.redcode.escposxml.pojo.Line;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Attribute;

import java.util.List;

@Root(name = "document")
public class Document {

    @Attribute(name = "code-page")
    public String codePage;
    
    @ElementList(name = "line", inline=true)
    public List<Line> lines = null;

    Document(){}

    Document(String codePage){this.codePage = codePage;}

    Document(String codePage, List<Line> lines) {
        this.lines = lines;
    }
}
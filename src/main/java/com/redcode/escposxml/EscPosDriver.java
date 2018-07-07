package com.redcode.escposxml;
 
import com.redcode.escposxml.pojo.Document;
import com.redcode.escposxml.pojo.Line;

import static com.redcode.escposxml.utils.constants.Commands.*;
import static com.redcode.escposxml.utils.constants.PrintMode.PRINT_MODE_MAP;
import static com.redcode.escposxml.utils.constants.Alignments.ALIGN_MAP;
import static com.redcode.escposxml.utils.constants.CutMode.CUT_MODE_MAP;
import static com.redcode.escposxml.utils.constants.CharCodeTable.CHAR_CODE_TABLE_MAP;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class EscPosDriver {

    private final ByteArrayOutputStream byteData;
    private Document doc;
    private InputStream template;

    public EscPosDriver() {
        this.byteData = new ByteArrayOutputStream();
    }

    public EscPosDriver(InputStream xmlFile){
        this.byteData = new ByteArrayOutputStream();
        this.setTemplate(xmlFile);
    }

    public void setTemplate(InputStream xmlFile) {
        this.template = xmlFile;
        this.doc = this.unmarshallTemplate(this.template);
    }

    public void setTemplateText(String lineId, String text) {
        int index = 0;
        for (Line line : this.doc.lines) {
            if (line.id.equals(lineId)) {
                line.text = text;
                this.doc.lines.set(index, line);
            }
            index++;
        }
    }

    public byte[] getBytes() {
        this.byteData.reset();
        
        for (Line line : this.doc.lines) {
            this.parseLine(line);
        }

        byte[] tmp = this.byteData.toByteArray();

        return tmp;
    }

    private Document unmarshallTemplate(InputStream xmlFile) {
        Serializer serializer = new Persister();
        try{
            Document doc = serializer.read(Document.class, xmlFile);
            return doc;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void parseLine(Line line) {
        // initalize printer.
        this.byteData.write(ESC);
        this.byteData.write(AT);

        if (this.doc.codePage != null) {
            this.parseCodePage(this.doc.codePage);
        }
        
        if (line.font != null) {
            parseFont(line.font);
        }

        if (line.alignment != null) {
            parseAlignment(line.alignment);
        }

        if (line.text != null) {
            parseText(line.text);
        }

        if (line.feed != null) {
            parseFeed(line.feed);
        }
        if (line.cut != null) {
            parseCut(line.cut);
        }
    }

    private void parseText(String text) {
        try {
            this.byteData.write(text.getBytes(this.doc.codePage.toLowerCase()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void parseAlignment(String alignment) {
        // Select justification
        this.byteData.write(ESC);
        this.byteData.write(a);

        this.byteData.write(ALIGN_MAP.get(alignment.toLowerCase()));
    }

    private void parseFont(String font) {
        // Select print mode(s)
        this.byteData.write(ESC);
        this.byteData.write(EXCLAMATION_MARK);
        System.out.println(PRINT_MODE_MAP);
        this.byteData.write(PRINT_MODE_MAP.get(font.toLowerCase()));
    }

    private void parseCut(String cut) {
        // Select cut mode and cut paper
        this.byteData.write(GS);
        this.byteData.write(V);

        this.byteData.write(CUT_MODE_MAP.get(cut.toLowerCase()));
    }

    private void parseFeed(Integer feed) {
        if (feed >= 0) {
            // Print and feed paper n lines
            this.byteData.write(ESC);
            this.byteData.write(d);

            this.byteData.write(feed);
        }
    }

    private void parseCodePage(String codePage) {

        // Select character code table
        this.byteData.write(ESC);
        this.byteData.write(t);

        this.byteData.write(CHAR_CODE_TABLE_MAP.get(codePage));
    }
}
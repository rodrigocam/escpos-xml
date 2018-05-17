package com.code.red.playvendas.utils.EscPosDriver;

import android.renderscript.ScriptGroup;

import com.code.red.playvendas.utils.EscPosDriver.EscPosXmlParser.Document;
import com.code.red.playvendas.utils.EscPosDriver.EscPosXmlParser.Line;
import com.code.red.playvendas.utils.EscPosDriver.EscPosXmlParser.Parser;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EscPosDriver {

    private ByteArrayOutputStream byteData;
    private XStream xStream;
    private Parser parser;
    private Document doc;
    private List<Line> lines;
    private InputStream xmlFile;

    public EscPosDriver(InputStream xmlFile){
        this.byteData = new ByteArrayOutputStream();
        this.xStream = new XStream(new DomDriver());
        this.parser = new Parser();
        this.xmlFile = xmlFile;

        this.doc = this.parser.unmarshall(this.xmlFile);
        this.lines = this.doc.lines;
    }

    public void setXmlFile(InputStream xmlFile){
        this.xmlFile = xmlFile;

        this.doc = this.parser.unmarshall(this.xmlFile);
        this.lines = this.doc.lines;
    }


    public void setLineText(String lineId, String text){
        int index = 0;
        for(Line line: this.lines){

            if(line.getId().equals(lineId)){
                line.setText(text);
                this.lines.set(index, line);
            }
            index++;
        }
    }

    public byte[] xmlToEsc(){

        for(Line line: this.lines){
            writeLine(line);
        }

        byte [] tmp = this.byteData.toByteArray();

        try {
            this.byteData.flush();
        }catch(IOException e){
            e.printStackTrace();
        }

        return tmp;
    }

    public byte[] customXmlToEsc(InputStream xmlFile, String lineId, String text){
        setLineText(lineId, text);

        for(Line line: this.lines){
            writeLine(line);
        }

        byte [] tmp = this.byteData.toByteArray();

        try {
            this.byteData.flush();
        }catch(IOException e){
            e.printStackTrace();
        }

        return tmp;
    }


    private void writeLine(Line line){
        // ESC @
        byteData.write(0x1B);
        byteData.write(0x40);

        if(line.getFont() != null) {
            writeFont(line.getFont());
        }
        if(line.getAlign() != null){
            writeAlign(line.getAlign());
        }
        if(line.getText() != null){
            writeText(line.getText());

            // LF
            //byteData.write(0xA);
        }
        if(line.getFeed() != null){
            writeFeed(line.getFeed());
        }
        if(line.getCut() != null) {
            writeCut(line.getCut());
        }
    }

    private void writeText(String text){
        try{
            byteData.write(text.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void writeAlign(String align){
        System.out.println("ALIGNMENT" + align);
        int code;

        byteData.write(0x1B);
        byteData.write(0x61);

        switch(align.toLowerCase()){
            case "left": code = 0x00;break;
            case "center": code = 0x01;break;
            case "right": code = 0x02;break;
            default: code = 0x00;
        }

        byteData.write(code);
    }

    private void writeFont(String font){

        int code;

        byteData.write(0x1B);
        byteData.write(0x21);

        switch(font.toLowerCase()){
            case "regular": code = 0x00;break;
            case "dh": code = 0x10;break;
            case "dw": code = 0x20;break;
            case "dwdh": code = 0x30;break;
            case "emphasized": code = 0x08;break;
            case "dh_emphasized": code = 0x18;break;
            case "dw_emphasized": code = 0x28;break;
            case "dwdh_emphasized": code = 0x38;break;
            default: code = 0x00;
        }

        byteData.write(code);
    }

    private void writeCut(String cut){

        int code;

        // GS V
        byteData.write(0x1D);
        byteData.write(0x56);

        switch(cut.toLowerCase()){
            case "full": code = 0x00;break;
            case "part": code = 0x01;break;
            default: code = 0x00;
        }

        // m function
        byteData.write(code);
    }

    private void writeFeed(Integer feed){

        if(feed >= 0){
            // ESC d
            byteData.write(0x1B);
            byteData.write(0x64);

            // n lines
            byteData.write(feed);
        }
    }
}

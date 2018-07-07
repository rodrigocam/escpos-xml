package com.redcode.escposxml.utils.constants;

import java.util.Map;
import java.util.HashMap;

public final class CharCodeTable {

    private CharCodeTable() {}

    public static final int CP437 = 0x00;
    public static final int KATANAKA = 0x01;
    public static final int CP850 = 0x02;
    public static final int CP860 = 0x03;
    public static final int CP863 = 0x04;
    public static final int CP865 = 0x05;
    public static final int WPC1252 = 0x16;
    public static final int CP866 = 0x17;
    public static final int CP852 = 0x18;
    public static final int CP858 = 0x19;
    public static final int BLANK = 0x255;

    public static Map<String, Integer> CHAR_CODE_TABLE_MAP;
    static {
        CHAR_CODE_TABLE_MAP = new HashMap<String, Integer>();
        CHAR_CODE_TABLE_MAP.put("CP437", CP437);
        CHAR_CODE_TABLE_MAP.put("KATANAKA", KATANAKA);
        CHAR_CODE_TABLE_MAP.put("CP850", CP850);
        CHAR_CODE_TABLE_MAP.put("CP860", CP860);
        CHAR_CODE_TABLE_MAP.put("CP863", CP863);
        CHAR_CODE_TABLE_MAP.put("CP865", CP865);
        CHAR_CODE_TABLE_MAP.put("WPC1252", WPC1252);
        CHAR_CODE_TABLE_MAP.put("CP866", CP866);
        CHAR_CODE_TABLE_MAP.put("CP852", CP852);
        CHAR_CODE_TABLE_MAP.put("CP858", CP858);
        CHAR_CODE_TABLE_MAP.put("BLANK", BLANK);
    }
}
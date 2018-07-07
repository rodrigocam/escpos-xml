package com.redcode.escposxml.utils.constants;

import java.util.Map;
import java.util.HashMap;

public final class Alignments {

    private Alignments() {}

    public static final Integer LEFT = 0x00;
    public static final Integer CENTER = 0x01;
    public static final Integer RIGHT = 0x02;

    public static Map<String, Integer> ALIGN_MAP;
    static{
        ALIGN_MAP = new HashMap<String, Integer>();

        ALIGN_MAP.put("left", LEFT);
        ALIGN_MAP.put("center", CENTER);
        ALIGN_MAP.put("right", RIGHT);
    }
}
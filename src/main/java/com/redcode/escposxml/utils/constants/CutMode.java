package com.redcode.escposxml.utils.constants;

import java.util.Map;
import java.util.HashMap;

public final class CutMode {

    private CutMode() {}

    public static final int FULL = 0x00;
    public static final int PART = 0x01;

    public static Map<String, Integer> CUT_MODE_MAP;
    static {
        CUT_MODE_MAP = new HashMap<String, Integer>();
        
        CUT_MODE_MAP.put("full", FULL);
        CUT_MODE_MAP.put("part", PART);
    }
}
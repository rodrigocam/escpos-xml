package com.redcode.escposxml.utils.constants;

import java.util.Map;
import java.util.HashMap;

public final class PrintMode {

    private PrintMode() {}

    public static final Integer REGULAR = 0x00;
    public static final Integer EMPHASIZED = 0x08;
    public static final Integer DOUBLE_HEIGHT = 0x10;
    public static final Integer DOUBLE_HEIGHT_EMPHASIZED = 0x18;
    public static final Integer DOUBLE_WIDTH = 0x20;
    public static final Integer DOUBLE_WIDTH_EMPHASIZED = 0x28;
    public static final Integer DOUBLE_HEIGHT_WIDTH = 0x30;
    public static final Integer DOUBLE_HEIGHT_WIDTH_EMPHASIZED = 0x38;

    public static Map<String, Integer> PRINT_MODE_MAP;
    static {
        PRINT_MODE_MAP = new HashMap<String, Integer>();
        
        PRINT_MODE_MAP.put("regular", REGULAR);
        PRINT_MODE_MAP.put("emphasized", EMPHASIZED);
        PRINT_MODE_MAP.put("dh", DOUBLE_HEIGHT);
        PRINT_MODE_MAP.put("dh_emphasized", DOUBLE_HEIGHT_EMPHASIZED);
        PRINT_MODE_MAP.put("dw", DOUBLE_WIDTH);
        PRINT_MODE_MAP.put("dw_emphasized", DOUBLE_WIDTH_EMPHASIZED);
        PRINT_MODE_MAP.put("dh_dw", DOUBLE_HEIGHT_WIDTH);
        PRINT_MODE_MAP.put("dh_dw_emphasized", DOUBLE_HEIGHT_WIDTH_EMPHASIZED);
    } 
}
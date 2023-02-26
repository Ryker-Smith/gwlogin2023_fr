package net.fachtnaroe.gwlogin2023_fr;

class colors {
    static final int MAIN_BACKGROUND = 0xFF59685f;
    static final int BUTTON_BACKGROUND = 0xFF06441f;
    static final int BUTTON_TEXT = 0xFFddebe2;
    static final int HEADING_TEXT = 0xFFddebe2;
    static final int SECTION_TOP_COLOR = 0xFF000000;
    static final int SECTION_BG_COLOR = 0xFF477c9b;
    static final int TEXTBOX_TEXT = 0xFF383500;
    static final int MAIN_TEXT_MUCHO = 0xFFFFFFBB;
    static final int TEXTBOX_BACKGROUND = 0xFFddebe2;
    static final int SUCCESS_GREEN = 0xFF569f4b;
    static final int WHITE = 0xFFFFFFFF;
    static final int BLACK = 0xFF000000;
    static final int RED = 0xFFFF0000;
    static final int GREEN = 0xFF00FF00;
    static final int BLUE = 0xFF0000FF;
    static final int MAIN_TEXT=0xFFbcdeFF;
    static final int TRANSPARENT=0x00000000;

    public static String withoutTransparencyValue (Integer x) {
        String s=x.toHexString(x);
        s=s.substring(2);
        s="#"+s;
        return s;
    }
}

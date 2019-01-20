package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    //700er Material Colors
    public static final int[] COLORS = {

            0xFF0288D1, //light blue
            0xFFD32F2F, //red
            0xFFAFB42B, //lime
            0xFF388E3C, //green
            0xFFEF6C00, //Orange
            0xFF512DA8, //Deep Purple
            0xFFC2185B, //Pink
            0xFF00796B, //Teal
    };

    //900er Material Colors
    public static final int[] DARKER_ACCENT_COLORS = {
            0xFF01579B, //light blue
            0xFFB71C1C, //red
            0xFF827717, //lime
            0xFF1B5E20, //green
            0xFFE65100, //orange
            0xFF311B92, //Deep Purple
            0xFF880E4F, //Pink
            0xFF004D40, //Teal
    };

    public static int getRandomColorIndex() {
        int index = Colors.COLORS.length;
        while (index == Colors.COLORS.length) {
            index = (int) (Math.random() * Colors.COLORS.length);
        }
        return index;
    }

}
package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    //700er Material Colors
    public static final int[] COLORS = {

            0xFF0288D1, //light blue
            0xFFD32F2F, //red
            0xFF616161, //gray
            0xFF388E3C, //green
    };

    //900er Material Colors
    public static final int[] DARKER_ACCENT_COLORS = {
            0xFF01579B, //light blue
            0xFFB71C1C, //red
            0xFF212121, //gray
            0xFF1B5E20, //green
    };

    public static int getRandomColorIndex() {
        int index = Colors.COLORS.length;
        while (index == Colors.COLORS.length) {
            index = (int) (Math.random() * Colors.COLORS.length);
        }
        return index;
    }

}
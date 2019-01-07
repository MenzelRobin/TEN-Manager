package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    //700er Material Colors
    public static final int[] COLORS = {

            0x0288D1, //light blue
            0xD32F2F, //red
            0x616161, //gray
            0x388E3C, //green
    };

    //900er Material Colors
    public static final int[] DARKER_ACCENT_COLORS = {
            0x01579B, //light blue
            0xD32F2F, //red
            0x212121, //gray
            0x1B5E20, //green
    };

    public static int getRandomColorIndex() {
        int index = Colors.COLORS.length;
        while (index == Colors.COLORS.length) {
            index = (int) Math.random() * Colors.COLORS.length;
        }
        return index;
    }

}
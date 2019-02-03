package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    //700er Material Colors
    public static final int[] COLORS = {

            0xFF0288D1, //light blue
            0xFFEF5350, //red
            0xFFAFB42B, //lime
            0xFF388E3C, //green
            0xFFFB8C00, //Orange
            0xFF7E57C2, //Deep Purple
            0xFFEC407A, //Pink
            0xFF009688, //Teal
    };

    //900er Material Colors
    public static final int[] DARKER_ACCENT_COLORS = {
            0xFF01579B, //light blue
            0xFFB71C1C, //red
            0xFF827717, //lime
            0xFF1B5E20, //green
            0xFFEF6C00, //orange
            0xFF512DA8, //Deep Purple
            0xFFC2185B, //Pink
            0xFF00796B, //Teal
    };

    public static int getRandomColorIndex() {
        int index = Colors.COLORS.length;
        while (index == Colors.COLORS.length) {
            index = (int) (Math.random() * Colors.COLORS.length);
        }
        return index;
    }

}
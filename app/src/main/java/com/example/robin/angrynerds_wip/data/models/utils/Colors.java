package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    private static int[] colors = {

            0xDD2C00,
            0x087f23,
            0x00766c,
            0x3d5afe,
            0x1a237e,
            0xb7008a,
            0xffab00,
            0xf57f17,
            0x60e10f,
            0x8c234d
    };

    public static int getRandomColor() {
        int index = 10;
        while (index == 10) {
            index = (int) Math.random() * 10;
        }
        return Colors.colors[index];
    }

}

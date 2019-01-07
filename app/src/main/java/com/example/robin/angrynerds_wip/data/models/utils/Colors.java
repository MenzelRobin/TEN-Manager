package com.example.robin.angrynerds_wip.data.models.utils;

public class Colors {

    private static int[] colors = {

            0x0077c2, //blue
            0x087f23, //green
            0x00227b, //dark blue
            0x005005, //dark green
            0x6a0080, //purple
            0x870000, //red
            0xc2185b, //pink
            0x5d4037 //brown
    };

    public static int getRandomColor() {
        int index = 8;
        while (index == 8) {
            index = (int) Math.random() * 8;
        }
        return Colors.colors[index];
    }

}
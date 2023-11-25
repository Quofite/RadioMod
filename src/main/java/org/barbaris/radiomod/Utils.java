package org.barbaris.radiomod;

import net.minecraft.block.*;

import java.util.List;

public class Utils {

    public static int max(List<Integer> list) {
        int maxValue = list.get(0);
        for (int el : list) {
            if(el > maxValue) {
                maxValue = el;
            }
        }

        return maxValue;
    }

    public static boolean isRedstoneSource(BlockState block) {
        boolean flag = false;

        if(block.getBlock() instanceof RedstoneTorchBlock) flag = true;
        if(block.getBlock() instanceof RedstoneBlock) flag = true;

        return flag;
    }

}

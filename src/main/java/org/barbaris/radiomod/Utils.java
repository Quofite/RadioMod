package org.barbaris.radiomod;

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

}

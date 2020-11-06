package com.napier.AI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreatePath {
    public static String findPath(List<Node> caverns) {
        List<Integer> route = new ArrayList<>();

        if (caverns.get(caverns.size() - 1).getPreviousNode() != -1) {
            int i = caverns.get(caverns.size() - 1).getNodeId();
            while (i != -1) {
                route.add(i + 1);
                i = caverns.get(i).getPreviousNode();
            }
        } else
            route.add(0);

        String strRoute = "";
        Collections.reverse(route);
        for (Integer i : route) {
            strRoute += (i + " ");
        }
        return strRoute.trim();
    }

}

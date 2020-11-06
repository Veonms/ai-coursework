package com.napier.AI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreatePath {

    /**
     * Starts the the last node and goes through all of the previous nodes until the value of the previous node is -1. If
     * No route is found then the route will just be the value 0.
     *
     * @param caverns - The value of all the caverns explored.
     * @return - returns the path from the starting node to the final node.
     */
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
        Collections.reverse(route); // Reverses the value of the route.
        for (Integer i : route)
            strRoute += (i + " "); // Appends to string.

        return strRoute.trim(); // Trims the last space of the route.
    }

}

package com.napier.AI;

import java.util.List;

public class Calculations {
    public static void calcGValue(Node currentNode, Node targetNode) {
        double xValue = Math.pow((currentNode.getX() - targetNode.getX()), 2);
        double yValue = Math.pow((currentNode.getY() - targetNode.getY()), 2);
        double distance = targetNode.getG() + Math.sqrt(xValue + yValue);

        if (distance < currentNode.getG()) {
            currentNode.setG(distance);
            currentNode.setPreviousNode(targetNode.getNodeId());
        }
    }

    public static void calcHValue(Node currentNode, Node targetNode) {
        double xValue = Math.pow((currentNode.getX() - targetNode.getX()), 2);
        double yValue = Math.pow((currentNode.getY() - targetNode.getY()), 2);
        double distance = Math.sqrt(xValue + yValue);
        currentNode.setH(distance);
    }

    public static int calcNextCavern(List<Node> openNodes) {
        double min = Double.MAX_VALUE;
        int index = -1;
        int id = 0;
        for (Node n : openNodes) {
            double f = n.getG() + n.getH();
            if (f < min) {
                min = f;
                index = id;
            }
            id++;
        }
        return index;
    }
}

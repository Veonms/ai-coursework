package com.napier.AI;

import java.util.List;

public class Calculations {
    /**
     * Takes in the current node and the target node, and finds the distance between them using the pythagoras theorem.
     * Calculates the value of g, the distance from the starting node to the current node.
     * Then checks if the value is smaller than the current g value. If so it sets the g value to the value of distance
     * and sets the previous node to the target node.
     *
     * @param currentNode - Takes in the current node.
     * @param targetNode  - Takes in the target node that we want to compare.
     */
    public static void calcGValue(Node currentNode, Node targetNode) {
        double xValue = Math.pow((currentNode.getX() - targetNode.getX()), 2);
        double yValue = Math.pow((currentNode.getY() - targetNode.getY()), 2);
        double distance = targetNode.getG() + Math.sqrt(xValue + yValue);

        if (distance < currentNode.getG()) {
            currentNode.setG(distance);
            currentNode.setPreviousNode(targetNode.getNodeId());
        }
    }

    /**
     * Takes in the current node and the target node, and finds the distance between them using the pythagoras theorem.
     * Calculates the value of h, the distance from the current node to the target node and sets that value to the
     * current node.
     *
     * @param currentNode - Takes in the current node.
     * @param targetNode  - Takes in the target node that we want to compare.
     */
    public static void calcHValue(Node currentNode, Node targetNode) {
        double xValue = Math.pow((currentNode.getX() - targetNode.getX()), 2);
        double yValue = Math.pow((currentNode.getY() - targetNode.getY()), 2);
        double distance = Math.sqrt(xValue + yValue);
        currentNode.setH(distance);
    }

    /**
     * @param openNodes - The list of all nodes which are known but have not been used
     * @return - returns the id/index of the next node.
     */
    public static int calcNextCavern(List<Node> openNodes) {
        double min = Double.MAX_VALUE; // Distance of the path before you calculate it is not known
        int index = -1;
        int id = 0;
        // Simple findMin algorithm to find the smallest f value
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

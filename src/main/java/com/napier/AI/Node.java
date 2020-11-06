package com.napier.AI;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int x;
    private final int y;
    private final int NodeId;
    private int previousNode;
    private List<Integer> connections = new ArrayList<>();
    private double g;
    private double h;
    private boolean visited;
    private boolean discovered;

    /**
     * @param id    - Allows each node to be identified.
     * @param xCord - x-coordinate so distance can be calculated.
     * @param yCord - x-coordinate so distance can be calculated.
     *              NodeID - Sets NodeID to the value of id.
     *              x - sets x to the value of xCord.
     *              y - sets y to the values of yCord.
     *              g - sets g to MAX_VALUE because the value of each distance is infinity as we dont know how far the path is.
     *              g is the value from the starting node to the current node.
     *              h - sets h to MAX_VALUE because the value of each distance is infinity as we dont know how far the path is.
     *              estimates the value from the current node to the goal node.
     *              visited - sets visited to false as the program hasn't visited any nodes.
     *              discovered - sets discovered to false as the program hasnt discovered the node
     *              previousNode - sets previousNode to -1 as there is no previous nodes to begin with.
     *              Allows program to search for -1 to find original node.
     */
    public Node(int id, int xCord, int yCord) {
        NodeId = id;
        x = xCord;
        y = yCord;
        g = Double.MAX_VALUE;
        h = Double.MAX_VALUE;
        visited = false;
        discovered = false;
        previousNode = -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNodeId() {
        return NodeId;
    }

    public int getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Integer node) {
        this.previousNode = node;
    }

    public List<Integer> getConnections() {
        return connections;
    }

    public void setConnections(List<Integer> newConnection) {
        this.connections = newConnection;
    }

    public double getG() {
        return g;
    }

    public void setG(Double newG) {
        this.g = newG;
    }

    public double getH() {
        return h;
    }

    public void setH(Double newH) {
        this.h = newH;
    }

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean b) {
        this.visited = b;
    }

    public boolean getDiscovered() {
        return discovered;
    }

    public void setDiscovered(Boolean b) {
        this.discovered = b;
    }
}


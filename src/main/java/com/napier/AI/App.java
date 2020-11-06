package com.napier.AI;

import java.util.ArrayList;
import java.util.List;

/**
 * This application is based on the A* algorithm.
 * <p>
 * References : https://www.youtube.com/watch?v=ySN5Wnu88nE
 */

public class App {
    public static void main(String[] args) {
        String filename = args[0]; // Takes the first argument from the command line and set it to the value of filename
        List<Node> caverns = FileHandler.read(filename + ".cav"); // Opens filename.cav and the returned nodes are saved

        List<Node> openNodes = new ArrayList<>();

        openNodes.add(caverns.get(0)); // adds first node to the openNodes
        openNodes.get(0).setG((double) 0); // gets the value of g, value from the first node to the current node to 0, as this is the first node

        Calculations.calcHValue(caverns.get(0), caverns.get(caverns.size() - 1)); // Finds value from first to last node

        int liveNode = 0; // Sets the index of the liveNode to 0.

        /**
         * This loop will continue while there are still nodes to explore and the program hasnt reached its goal node.
         * It will loop through all of the connecting nodes, checking for the best node to continue with.
         */
        while (openNodes.size() > 0 && liveNode != caverns.get(caverns.size() - 1).getNodeId()) {
            liveNode = Calculations.calcNextCavern(openNodes); // Looks for the closest target node

            for (int i : caverns.get(openNodes.get(liveNode).getNodeId()).getConnections()) { // Loops through all the connections.
                if (!(caverns.get(i).getVisited())) { // Checks if the node hasnt been visited, if not it calculates the g and h value.
                    Calculations.calcGValue(caverns.get(i), caverns.get(openNodes.get(liveNode).getNodeId()));
                    Calculations.calcHValue(caverns.get(i), caverns.get(caverns.size() - 1));

                    if (!(caverns.get(i).getDiscovered())) { // Checks if the node hasnt been discovered.
                        openNodes.add(caverns.get(i)); // Adds the current node to openNodes.
                        openNodes.get(openNodes.size() - 1).setDiscovered(true); // Sets discovered to true.
                    }
                }
            }
            openNodes.get(liveNode).setVisited(true); // Sets visited to true
            openNodes.get(liveNode).setDiscovered(false); // Sets discovered to false
            openNodes.remove(liveNode); // removes node from the openNodes
        }

        String route = CreatePath.findPath(caverns); // Finds path and sets value to route
        FileHandler.write(filename + ".csn", route); // Writes route to filename.csn
    }
}

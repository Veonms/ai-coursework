package com.napier.AI;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String filename = args[0];
        List<Node> caverns = FileHandler.read(filename + ".cav");

        List<Node> openNodes = new ArrayList<>();
        openNodes.add(caverns.get(0));
        openNodes.get(0).setG((double) 0);
        Calculations.calcHValue(caverns.get(0), caverns.get(caverns.size() - 1));

        int liveNode = 0;

        while (openNodes.size() > 0 && liveNode != caverns.get(caverns.size() - 1).getNodeId()) {
            liveNode = Calculations.calcNextCavern(openNodes);

            for (int i : caverns.get(openNodes.get(liveNode).getNodeId()).getConnections()) {
                if (!(caverns.get(i).getVisited())) {
                    Calculations.calcGValue(caverns.get(i), caverns.get(openNodes.get(liveNode).getNodeId()));
                    Calculations.calcHValue(caverns.get(i), caverns.get(caverns.size() - 1));

                    if (!(caverns.get(i).getUnvisited())) {
                        openNodes.add(caverns.get(i));
                        openNodes.get(openNodes.size() - 1).setUnvisited(true);
                    }
                }
            }
            openNodes.get(liveNode).setVisited(true);
            openNodes.get(liveNode).setUnvisited(false);
            openNodes.remove(liveNode);
        }

        String route = CreatePath.findPath(caverns);
        FileHandler.write(filename + ".csn", route);
    }
}

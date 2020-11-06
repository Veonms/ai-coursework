package com.napier.AI;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    /**
     * The method read, reads in a file (in the format of .cav) and filters, by comma, all the data to an ArrayList,
     * data. The first integer gives the number of caverns, N; from the second value to the N*2 +1 value, is the x and y
     * coordinates; and from N*2 +1 to the end of the ArrayList is the matrix, which shows all the connections to each
     * node. Assigns all the data to the corresponding node and returns the ArrayList of nodes.
     *
     * @param filename - Takes in the variable, string, filename to access the file.
     * @return - returns a list, List<Node>, of all the nodes and corresponding values.
     */

    //Reads in file and adds all the data to the ArrayList, data.
    public static List<Node> read(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.addAll(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int noCaverns = Integer.parseInt(data.get(0)); // Gets the number of caverns in the file

        // Creates ArrayList for each x and y coordinate
        List<Integer> xCord = new ArrayList<>();
        List<Integer> yCord = new ArrayList<>();

        // Goes through and appends the x and y coordinates to the ArrayList
        for (int i = 1; i < (noCaverns * 2 + 1); i++) {
            if (i % 2 == 0)
                yCord.add(Integer.parseInt(data.get(i)));
            else
                xCord.add(Integer.parseInt(data.get(i)));
        }

        // Creates an ArrayList for the matrix and adds the matrix to the ArrayList.
        List<Integer> matrix = new ArrayList<>();

        for (int i = (noCaverns * 2); i < data.size(); i++) {
            matrix.add(Integer.parseInt(data.get(i)));
        }

        // Creates an ArrayList for the connections
        List<List<Integer>> connections = new ArrayList<>();

        /**
         * The matrix is a N*N list of values, either 1 or 0. When you visualise the data in a N*N cube/table you notice
         * that each N+noCaverns is the connection from that node. The code below shows goes through each N+noCarverns
         * values and if the corresponding value in the matrix is 1 then the node is stored in a temporary array. Once
         * the program has cycled through all the connections for that node it will add the temporary ArrayList to the
         * ArrayList connections. 1 is then added to N+noCaverns so it does the next node. This cycles until the end of
         * the matrix.
         */
        for (int i = 0; i < noCaverns; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = i + 1; j < matrix.size(); j += noCaverns) {
                if (matrix.get(j) == 1)
                    temp.add(j / noCaverns);
            }
            connections.add(temp);
        }

        /**
         * An ArrayList is created to store all of the nodes. A node is created, providing the id, the x-coordinate and
         * the y-coordinate. The connections are then set, before the node is added to the ArrayList. Once all of the
         * nodes have been added they are then returned.
         */
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < noCaverns; i++) {
            Node a = new Node(i, xCord.get(i), yCord.get(i));
            a.setConnections(connections.get(i));
            nodes.add(a);
        }
        return nodes;
    }

    /**
     * Method, write, creates a file (if not already created) and writes in the path of nodes from the starting node to
     * the end node. The program calls the output file the same name as the input file, with the exception that the file
     * type is a .csn.
     *
     * @param filename - takes in filename to create file.
     * @param path     - takes in the path of the node to output to the file.
     */
    static void write(String filename, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

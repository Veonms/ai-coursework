package com.napier.AI;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    /**
     * The method read, reads in a file (in the format of .cav) and filters, by comma, all the data to an ArrayList,
     * data. The first integer gives the number of caverns, N; from the second value to the N*2 +1 value, is the x and y
     * coordinates; and from N*2 +
     *
     * @param filename - Takes in the variable, string, filename to access the file.
     * @return - returns a list, List<Node>, of all the nodes and corresponding values.
     */
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

        List<Integer> xCord = new ArrayList<>();
        List<Integer> yCord = new ArrayList<>();

        for (int i = 1; i < (noCaverns * 2 + 1); i++) {
            if (i % 2 == 0)
                yCord.add(Integer.parseInt(data.get(i)));
            else
                xCord.add(Integer.parseInt(data.get(i)));
        }

        // Saves the matrix which shows the connections between caverns in an arraylist
        List<Integer> matrix = new ArrayList<>();

        for (int i = (noCaverns * 2); i < data.size(); i++) {
            matrix.add(Integer.parseInt(data.get(i)));
        }

        // Find which caverns connect
        List<List<Integer>> connections = new ArrayList<>();

        for (int i = 0; i < noCaverns; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = i + 1; j < matrix.size(); j += noCaverns) {
                if (matrix.get(j) == 1)
                    temp.add(j / noCaverns);
            }
            connections.add(temp);
        }

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

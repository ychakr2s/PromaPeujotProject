package service.ReadFile;

import service.Graph.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class dimacsGraphCreator implements GraphCreator {

    /*
     * This method read a File and produce a Graph.
     */
    @Override
    public graph factoryGraph(String filename) {
        Path path = Paths.get(filename);
        graph gr = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
            String line = reader.readLine();
            while (line != null) {
                String[] splited = line.split("\\s+");
                if (splited[0].equals("p")) {
                    gr = new graph(Integer.parseInt(splited[2]));
                }

                if (splited[0].equals("e")) {
                    assert gr != null;
                    gr.addEdge(Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]) - 1);
                }

                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gr;
    }

}

package service.ReadFile;

import service.Graph.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class selfGeneratedCreator implements GraphCreator {

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
                    gr = new graph(Integer.parseInt(splited[1]));
                    gr.setEdge(Integer.parseInt(splited[2]));
                }
                if (isNumeric(splited[0])) {
                    assert gr != null;
                    gr.addEdge(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
                }

                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return gr;
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

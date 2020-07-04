
package service.ReadFile;

import service.Graph.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class readGraph {

    GraphCreator dimacsCreator = new dimacsGraphCreator();
    GraphCreator selfCreator = new selfGeneratedCreator();
    GraphCreator jsonCreator = new jsonGraphCreator();

    public readGraph() {
    }

    public String chooseCreation(String filename) {
        Path path = Paths.get(filename);
        String method = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
            String line = reader.readLine();
            while (line != null) {
                String[] splited = line.split("\\s+");
                if (splited[0].equals("p")) {
                    line = reader.readLine();
                    splited = line.split("\\s+");
                    if (splited[0].equals("e")) {
                        method = "dimacsToGraph";
                        break;
                    } else if (isNumeric(splited[0])) {
                        method = "selfGenerated";
                        break;
                    } else {
                        method = "wrong";
                        break;
                    }
                } else if (splited[0].equals("{")) {
                    method = "jsonToGraph";
                    break;
                }

                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return method;
    }

    /*
     ** This method creates the Graph according the typ of the Graph
     */
    public graph factoryGraph(String path) {
        switch (chooseCreation(path)) {
            case "dimacsToGraph":
                return dimacsCreator.factoryGraph(path);
            case "selfGenerated":
                return selfCreator.factoryGraph(path);
            case "jsonToGraph":
                return jsonCreator.factoryGraph(path);
            default:
                return null;
        }
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

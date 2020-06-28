package service.ReadFile;

import com.google.gson.Gson;
import service.Graph.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class readGraph {

    public readGraph() {
    }

    /*
     * This method read a File and produce a Graph.
     */
    public graph dimacsToGraph(String filename) {

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

    public graph selfGenerated(String filename) {

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
                } else if (splited[0].equals("")) {
                    break;
                } else {
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

    /*
     ** This method chooses the suitable create method for the Graph.
     */
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
     ** This method initialize the Graph according the inputted path.
     ** it will be determined which method will be used.
     */
    public graph initialize_Graph(String path) {
        switch (chooseCreation(path)) {
            case "dimacsToGraph":
                return dimacsToGraph(path);
            case "selfGenerated":
                return selfGenerated(path);
            case "jsonToGraph":
                return jsonToGraph(path);
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

    /*
     * this method get a Json file and parse it to Java. It is parsed to Graph.
     */
    public graph jsonToGraph(String filename) {

        Gson gson = new Gson();
        graph gr = new graph(0);

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filename));

            //convert the json string back to object
            graph graph = gson.fromJson(br, graph.class);

            gr = new graph(graph.getNumVertices());

            for (int i = 0; i < graph.getNumVertices(); i++) {
                for (Integer integer : graph.neighborhood(i)) {

                    double a = (double) integer;
                    int c = (int) a;
                    gr.addEdge(i, c);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gr;
    }


}

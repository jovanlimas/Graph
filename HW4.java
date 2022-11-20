import java.io.FileNotFoundException;

public class HW4 extends Graph {

    public HW4(String filename) throws FileNotFoundException {
        super(filename);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];

        // 1. Creates an empty graph.
        HW4 graph = new HW4(filename);

        // 2. Loads the graph using the specified input file.
        graph.load(filename); 

        // 3. Displays the adjacency matrix.
        graph.display();

        // 4. Displays a depth first search starting at vertex 0.
        graph.displayDFS(0);

        // 5. Display a breadth first search starting at vertex 0.
        graph.displayBFS(0);
    }
}

/**
 * Author: Jovan Limas
 * 
 * Undirected Graph model
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public abstract class Graph {

    private static int[][] adjMatrix;
    
    /**
     * Constructs a new {@code Graph} that is empty.
     * @param filename input file
     * @throws FileNotFoundException if file not found
     */
    public Graph(String filename) throws FileNotFoundException{
        Scanner scn = new Scanner(new File(filename));

        int vertices = scn.nextInt();
        adjMatrix = new int[vertices][vertices];

        scn.close();
    }

    /**
     * Creates the graph using the file passed into the function.
     * @param filename input file
     * @throws FileNotFoundException if file not found
     */
    public void load(String filename) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(filename));
        scn.nextLine();                 // skips first line (num of vertices)

        int edges = countEdges(filename);

        for (int i = 0; i < edges; i++) {
            int v1 = scn.nextInt();     // first vertex
            int v2 = scn.nextInt();     // second vertex

            adjMatrix[v1][v2] = 1;      // create adjacency
            adjMatrix[v2][v1] = 1;      // create adjacency
        }

        scn.close();
    }

    /**
     * Calculates the number of edges in the graph.
     * @param filename input file
     * @return number of edges
     * @throws FileNotFoundException if file not found
     */
    private static int countEdges(String filename) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(filename));
        int count = 0;

        scn.nextLine();             // skips first line (num of vertices)

        while (scn.hasNextLine()) {
            scn.nextLine();
            count++;
        }

        scn.close();
        return count;
    }

    /**
     * Displays the graph's adjacency matrix to the screen.
     */
    public void display() {
        System.out.println("Adjacency Matrix");

        for (int i = 0; i < adjMatrix.length; i++) {
            
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Displays the result of a depth first search starting at the provided vertex. 
     * @param vertex starting vertex
     */
    public void displayDFS(int vertex) {
        System.out.print("DFS at vertex " + vertex + ": ");

        dfs(vertex, new boolean[adjMatrix.length]);

        System.out.println();
    }

    /**
     * Performs depth first search recursively.
     * @param vertex starting vertex
     * @param done array of booleans indicating if a vertex has been visited
     */
    private void dfs(int vertex, boolean[] done) {
        System.out.print(vertex + " ");
        done[vertex] = true;

        for (int i = 0; i < adjMatrix[vertex].length; i++) {

            if (adjMatrix[i][vertex] == 1 && !done[i]) {
                dfs(i, done);
            }
        }
    }

    /**
     * Displays the result of a breadth first search starting at the provided vertex.
     * @param vertex starting vertex
     */
    public void displayBFS(int vertex) {
        System.out.print("BFS at vertex " + vertex + ": ");

        Queue<Integer> order = new LinkedList<>();

        bfs(vertex, new boolean[adjMatrix.length], order);

        System.out.println();
    }

    /**
     * Performs breadth first search using queue and recursion.
     * @param vertex starting vertex
     * @param done array of booleans indicating if a vertex has been visited
     * @param order queue of vertex to do a bfs on
     */
    private void bfs(int vertex, boolean[] done, Queue<Integer> order) {

        if (!done[vertex]) {
            System.out.print(vertex + " ");
            done[vertex] = true;
        }

        for (int i = 0; i < adjMatrix.length; i++) {

            if (adjMatrix[i][vertex] == 1 && !done[i]) {
                order.add(i);

                System.out.print(i + " ");

                done[i] = true;
            }
        }

        while (order.size() != 0) {
            bfs(order.remove(), done, order);   // pop and bfs
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
    }
}

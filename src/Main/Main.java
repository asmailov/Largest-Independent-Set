package Main;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.max;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * @author Aleksandr Šmailov
 */
public class Main {
    // True if you want additional data to be written to default IO
    private final static boolean DEBUG = true; 
    
    public static boolean isDEBUG() {
        return DEBUG;
    }

    /**
     * Largest independent set Recursive algorithm realization.
     * @param g Graph.
     * @return size of the largest independent set.
     */
    public static int maxsetRecursive(Graph g){
        if(g.getEdgesAmount() == 0){
            return g.getVerticesAmount();
        } else {
            String vertex = g.getFirstVertex();
            Graph g1 = new Graph(g);
            g1.remove(vertex);
            int n1 = maxsetRecursive(g1);
            Graph g2 = new Graph(g);
            Set<String> set = g2.getNeighbors(vertex);
            for(String s : set){
                g2.remove(s);
            }
            g2.remove(vertex);
            int n2 = maxsetRecursive(g2);
            return max(n1,1 + n2);
        }
    }
    
    /**
     * Largest independent set Backtracking algorithm realization.
     * @param g Graph.
     * @return Set of strings which are largest independent set.
     */
    public static Set<String> maxsetBacktracking(Graph g){
        if(g.getVerticesAmount() == 0){
            return new HashSet<>();
        }
        if(g.getVerticesAmount() == 1){
            return g.getVertices();
        }
        for(String vertex : g.getVertices()){
            Graph gOut = new Graph(g);
            gOut.remove(vertex);
            
            Graph gIn = new Graph(g);
            Set<String> set = gIn.getNeighbors(vertex);
            for(String s : set){
                gIn.remove(s);
            }
            gIn.remove(vertex);
            Set<String> sOut = maxsetBacktracking(gOut);
            Set<String> sIn = maxsetBacktracking(gIn);
            sIn.add(vertex);
            if(sIn.size() > sOut.size()) {
                return sIn;
            } else {
                return sOut;
            }
        }
        return null;
    }
    
    /**
     * Prints set
     * @param set Set of Strings
     */
    public static void printSet(Set<String> set){
        if(set != null) {
            String text = "{";
            for(String s : set){
                text += s + ", ";
            }
            text = text.substring(0, text.length() - 2);
            text += "}";
            System.out.println(text);
        }
    }
    
    /**
     * Reads file and creates Graph from it.
     * @param file file where Graph matrix is saved.
     * @return Graph
     * @throws FileNotFoundException
     */
    public static Graph readFileCreateGraph(String file) throws FileNotFoundException{
        Scanner input = new Scanner (new File(file));
        int size = 0;
        if(input.hasNextInt())
        {
            size = input.nextInt();
        }
        Graph g = new Graph();
        int v;
        for (int i = 0; i < size; i++){
            g.add(Integer.toString(i+1));
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (input.hasNextInt()) {
                    v = input.nextInt();
                    if(v == 1){
                        g.connect(Integer.toString(i+1), Integer.toString(j+1));
                    }
                }
            }
        }
        return g;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Check if number of arguments is correct
        if(args.length != 3){
            System.out.println("java -jar LargestIndependentSet.jar "
                    + "option(r - recursive algorithm, b - backtracking) "
                    + "howManyTimesToRepeat graphMatrixFile");
            return;
        }
        // Check if length of first param doesn't exceed 1
        if(args[0].length() != 1){
            System.out.println("Unknown option(s) " + args[0]);
             return;
        }
        // Set variables to true according to the input.
        boolean recursive = args[0].equals("r");
        boolean backtracking = args[0].equals("b");
        // Get hoManyTimesToRepeat parameter.
        int repeat = Integer.parseInt(args[1]);
        // Get filename.
        String filename = args[2];
        // Try to read from file and get results using specified algorithm.
        try {
            Graph g = readFileCreateGraph(filename);
            double time = 0;
            if(recursive){
                for(int i = 0; i < repeat; i++){
                    final long startTime = System.nanoTime();
                    int max = maxsetRecursive(g);
                    final long endTime = System.nanoTime();
                    System.out.println("Largest independent set size: " + max);
                    System.out.println("Running time: " + (endTime - startTime)/1000000000.0 + " seconds.");
                    time += (endTime - startTime)/1000000000.0;
                }
                time = time / repeat;
                System.out.println(time);
            }
            if(backtracking){
                for(int i = 0; i < repeat; i++){
                    final long startTime = System.nanoTime();
                    Set<String> s = maxsetBacktracking(g); 
                    final long endTime = System.nanoTime();
                    printSet(s);
                    System.out.println("Largest independent set size: " + s.size());
                    System.out.println("Running time: " + (endTime - startTime)/1000000000.0 + " seconds.");
                    time += (endTime - startTime)/1000000000.0;
                }
                time = time / repeat;
                System.out.println(time);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
}

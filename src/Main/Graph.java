package Main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Aleksandr Šmailov
 */

public class Graph {
    private Set<String> vertices;
    private Set<Edge> edges;
    
    /**
     * Constructor without parameters.
     */
    public Graph() {
        init();
    }
    
    /**
     * Constructor which creates a new graph from already existing one.
     * @param g Graph
     */
    public Graph(Graph g) {
        this.vertices = new HashSet(g.vertices);
        this.edges = new HashSet(g.edges);
    }
    
    private void init(){
        vertices = new HashSet<>();
        edges = new HashSet<>();
    }
    
    /**
     * Returns the amount of vertices Graph has.
     * @return integer
     */
    public int getVerticesAmount(){
        return this.vertices.size();
    }
    
    /**
     * Returns the amount of edges Graph has.
     * @return integer
     */
    public int getEdgesAmount(){
        return this.edges.size();
    }
    
    /**
     * Gets first vertex.
     * @return String containing the name of the vertex.
     */
    public String getFirstVertex(){
        Iterator<String> iter = vertices.iterator();
        return iter.next();
    }

    /**
     * Gets vertices.
     * @return Set of Strings.
     */
    public Set<String> getVertices() {
        return vertices;
    }
    
    /**
     * Adds vertex to the graph.
     * @param vertex
     */
    public void add(String vertex){
        vertices.add(vertex);
    }
    
    /**
     * Creates edge by connecting two vertices.
     * @param v1 first vertex.
     * @param v2 second vertex.
     */
    public void connect(String v1, String v2){
        Edge e = new Edge();
        if(vertices.contains(v1) && vertices.contains(v2)){
            e.setVertex1(v1);
            e.setVertex2(v2);
            if(!containsEdge(v1, v2)){
                edges.add(e);
            }
        } else {
            System.out.println("No such vertices!");
        }
    }
    
    /**
     * Checks if Graph contains edge.
     * @param v1 first vertex.
     * @param v2 second vertex.
     * @return true/false
     */
    public boolean containsEdge(String v1, String v2){
        for(Edge e : edges){
            if(e.contains(v1, v2)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Removes vertex from the Graph.
     * @param vertex vertex we want to remove.
     */
    public void remove(String vertex){
        vertices.remove(vertex);
        Iterator it = edges.iterator();
        while(it.hasNext()){
            Edge e = (Edge)it.next();
            if(e.contains(vertex)){
                it.remove();
            }
        }
    }
    
    /**
     * Find every neighbor(vertices which are connected to the given vertex) 
     * of the vertex.
     * @param vertex
     * @return Set of vertex Strings
     */
    public Set<String> getNeighbors(String vertex){
        Set<String> set = new HashSet<>();
        for(Edge e : edges){
            if(e.contains(vertex)){
                set.add(e.getConVertex(vertex));
            }
        }
        return set;
    }
    
    @Override
    public String toString() {
        String s;
        s = "Vertices: {";
        for(String q : vertices){
            s += q + ", ";
        }
        if(vertices.size() > 0) {
            s = s.substring(0, s.length() - 2);
        }
        s += String.format("}%n");
        s += "Edges: {";
        for(Edge e : edges){
            s += e.toString() + ", ";
        }
        if(edges.size() > 0){
            s = s.substring(0, s.length() - 2);
        }
        s += String.format("}%n");
        return s;
    }
}

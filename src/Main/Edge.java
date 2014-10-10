package Main;

/**
 * @author Aleksandr Šmailov
 */
public class Edge {
    private String vertex1;
    private String vertex2;
    
    // Setters
    public void setVertex1(String vertex1) {
        this.vertex1 = vertex1;
    }
    public void setVertex2(String vertex2) {
        this.vertex2 = vertex2;
    }
    
    // Getters
    public String getVertex1() {
        return vertex1;
    }
    public String getVertex2() {
        return vertex2;
    }
    
    // Other methods
    
    /**
     * Finds and returns connected vertex.
     * @param vertex one of edge's vertices.
     * @return Connected vertex.
     */
    public String getConVertex(String vertex){
        if(vertex.equals(getVertex1())){
            return getVertex2();
        }
        if(vertex.equals(getVertex2())){
            return getVertex1();
        }
        return null;
    }
    
    /**
     * Checks if edge contains vertex.
     * @param vertex vertex.
     * @return true/false.
     */
    public boolean contains(String vertex){
        return getVertex1().equals(vertex) || getVertex2().equals(vertex);
    }
    
    /**
     * Check if edge consists of these vertices.
     * @param v1 vertex1
     * @param v2 vertex2
     * @return true/false
     */
    public boolean contains(String v1, String v2){
        return (getVertex1().equals(v1) && getVertex2().equals(v2)) ||
               (getVertex1().equals(v2) && getVertex2().equals(v1));
    }
    
    @Override
    public String toString() {
        String s = "(" + vertex1 + ";" + vertex2 + ")";
        return s;
    }
}

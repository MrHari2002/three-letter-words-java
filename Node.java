public class Node {
    String name;
    Boolean discovered = false;
    Node predecessor;
    boolean processed = false;
    Node(String value){
        name = value;
    }
    public void setDiscovered(Boolean discovered) {
        this.discovered = discovered;
    }
    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}

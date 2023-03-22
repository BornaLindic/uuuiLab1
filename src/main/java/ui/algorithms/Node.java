package ui.algorithms;

import java.util.Objects;

public class Node {

    private Node parent;
    private String state;
    private double cost;

    public Node(Node parent, String state, double cost) {
        this.parent = parent;
        this.state = state;
        this.cost = cost;
    }

    public Node getParent() {
        return parent;
    }

    public String getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Double.compare(node.cost, cost) == 0 && Objects.equals(parent, node.parent) && Objects.equals(state, node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, state, cost);
    }
}

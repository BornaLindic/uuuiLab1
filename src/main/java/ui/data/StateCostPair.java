package ui.data;

public class StateCostPair {

    private String state;
    private double cost;

    public StateCostPair(String state, double cost) {
        this.state = state;
        this.cost = cost;
    }

    public String getState() {
        return state;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "StateCostPair{" +
                "state='" + state + '\'' +
                ", cost=" + cost +
                '}';
    }
}

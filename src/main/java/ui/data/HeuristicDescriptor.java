package ui.data;

import java.util.Map;

public class HeuristicDescriptor {

    private Map<String, Double> heuristics;

    public HeuristicDescriptor(Map<String, Double> heuristics) {
        this.heuristics = heuristics;
    }

    public Map<String, Double> getHeuristics() {
        return heuristics;
    }

    @Override
    public String toString() {
        return "HeuristicDescriptor{" +
                "heuristics=" + heuristics +
                '}';
    }
}

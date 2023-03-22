package ui.algorithms;

import ui.data.StateCostPair;

import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HeuristicEvaluator {

    public static boolean checkOptimistic(Path pathToHeuristic,
                                       Map<String, List<StateCostPair>> successors,
                                       String[] goalStates,
                                       Map<String, Double> heuristics) {

        System.out.println("# HEURISTIC-OPTIMISTIC " + pathToHeuristic);

        boolean isOptimistic = true;

        List<String> states = new LinkedList<>(heuristics.keySet());
        Collections.sort(states);

        for (String state : states) {
            double heuristicCostPrediction = heuristics.get(state);
            double realCost = UCS.uniformCostSearch(state, successors, goalStates, false);

            if(heuristicCostPrediction > realCost) isOptimistic = false;

            System.out.print("[CONDITION]: ");
            System.out.print(heuristicCostPrediction <= realCost ? "[OK] " : "[ERR] ");
            System.out.printf("h(%s) <= h*: %.1f <= %.1f\n", state, heuristicCostPrediction, realCost);
        }

        System.out.print("[CONCLUSION]: ");
        System.out.print(isOptimistic ? "Heuristic is optimistic." : "Heuristic is not optimistic.");

        return isOptimistic;
    }


    public static boolean checkConsistent(Path pathToHeuristic,
                                       Map<String, List<StateCostPair>> successors,
                                       Map<String, Double> heuristics) {
        System.out.println("# HEURISTIC-CONSISTENT " + pathToHeuristic);

        boolean isConsistent = true;

        List<String> states = new LinkedList<>(heuristics.keySet());
        Collections.sort(states);

        for (String state : states) {
            if(!successors.containsKey(state)) continue;

            double currHeuristic = heuristics.get(state);
            List<StateCostPair> neighbours = successors.get(state);

            for (StateCostPair neighbour : neighbours) {
                double nextHeuristic = heuristics.get(neighbour.getState());

                if (currHeuristic > nextHeuristic + neighbour.getCost()) isConsistent = false;

                System.out.print("[CONDITION]: ");
                System.out.print(currHeuristic <= nextHeuristic + neighbour.getCost() ? "[OK] " : "[ERR] ");
                System.out.printf("h(%s) <= h(%s) + c: %.1f <= %.1f + %.1f\n",
                        state, neighbour.getState(), currHeuristic, nextHeuristic, neighbour.getCost());
            }
        }

        System.out.print("[CONCLUSION]: ");
        System.out.print(isConsistent ? "Heuristic is consistent." : "Heuristic is not consistent.");

        return isConsistent;
    }


}

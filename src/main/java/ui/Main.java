package ui;

import ui.algorithms.ASTAR;
import ui.algorithms.BFS;
import ui.algorithms.HeuristicEvaluator;
import ui.algorithms.UCS;
import ui.data.DataLoader;
import ui.data.HeuristicDescriptor;
import ui.data.StateDescriptor;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        StateDescriptor stateDescriptor = DataLoader.loadStateDescription(Paths.get("istra.txt"));
        HeuristicDescriptor heuristicDescriptor = DataLoader.loadHeuristicDescription(Paths.get("istra_pessimistic_heuristic.txt"));

        // System.out.println(stateDescriptor);
        // System.out.println(heuristicDescriptor);

        BFS.breadthFirstSearch(stateDescriptor.getStartingState(),
                stateDescriptor.getSuccessors(),
                stateDescriptor.getGoalStates());

        System.out.println();

        UCS.uniformCostSearch(stateDescriptor.getStartingState(),
                stateDescriptor.getSuccessors(),
                stateDescriptor.getGoalStates(),
                true);

        System.out.println();

        ASTAR.aStarSearch(stateDescriptor.getStartingState(),
                stateDescriptor.getSuccessors(),
                stateDescriptor.getGoalStates(),
                heuristicDescriptor.getHeuristics(),
                Paths.get("istra_heurisic.txt"));

        System.out.println();

        HeuristicEvaluator.checkOptimistic(Paths.get("ai_fail.txt"),
                stateDescriptor.getSuccessors(),
                stateDescriptor.getGoalStates(),
                heuristicDescriptor.getHeuristics());

        System.out.println();

        HeuristicEvaluator.checkConsistent(Paths.get("ai_fail.txt"),
                stateDescriptor.getSuccessors(),
                heuristicDescriptor.getHeuristics());


    }


}

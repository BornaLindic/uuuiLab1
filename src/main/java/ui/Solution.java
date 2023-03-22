package ui;

import ui.algorithms.ASTAR;
import ui.algorithms.BFS;
import ui.algorithms.HeuristicEvaluator;
import ui.algorithms.UCS;
import ui.data.DataLoader;
import ui.data.HeuristicDescriptor;
import ui.data.StateDescriptor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String ... args) {

		List<String> arguments = new ArrayList<>(Arrays.asList(args));

		try {
			Path pathToStateDescriptor = Paths.get(arguments.get(arguments.indexOf("--ss") + 1));
			StateDescriptor stateDescriptor = DataLoader.loadStateDescription(pathToStateDescriptor);

			HeuristicDescriptor heuristicDescriptor = null;
			Path pathToHeuristicDescriptor = null;
			if (arguments.contains("--h")) {
				pathToHeuristicDescriptor = Paths.get(arguments.get(arguments.indexOf("--h") + 1));
				heuristicDescriptor = DataLoader.loadHeuristicDescription(pathToHeuristicDescriptor);
			}

			if (arguments.contains("--alg")) {
				switch (arguments.get(arguments.indexOf("--alg") + 1)) {
					case "bfs":
						BFS.breadthFirstSearch(stateDescriptor.getStartingState(),
								stateDescriptor.getSuccessors(),
								stateDescriptor.getGoalStates());
						return;
					case "ucs":
						UCS.uniformCostSearch(stateDescriptor.getStartingState(),
								stateDescriptor.getSuccessors(),
								stateDescriptor.getGoalStates(),
								true);
						return;
					case "astar":
						ASTAR.aStarSearch(stateDescriptor.getStartingState(),
								stateDescriptor.getSuccessors(),
								stateDescriptor.getGoalStates(),
								heuristicDescriptor.getHeuristics(),
								pathToHeuristicDescriptor);
						return;
					default: throw new InvalidParameterException("Invalid algorithm name.");
				}
			} else if (arguments.contains("--check-optimistic")) {
				HeuristicEvaluator.checkOptimistic(pathToHeuristicDescriptor,
						stateDescriptor.getSuccessors(),
						stateDescriptor.getGoalStates(),
						heuristicDescriptor.getHeuristics());
			} else if (arguments.contains("--check-consistent")) {
				HeuristicEvaluator.checkConsistent(pathToHeuristicDescriptor,
						stateDescriptor.getSuccessors(),
						heuristicDescriptor.getHeuristics());
			} else throw new InvalidParameterException();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}


	}

}

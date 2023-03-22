package ui.algorithms;

import ui.data.StateCostPair;

import java.nio.file.Path;
import java.util.*;

public class ASTAR {

    public static void aStarSearch(String startingState,
                                   Map<String, List<StateCostPair>> successors,
                                   String[] goalStates,
                                   Map<String, Double> heuristics,
                                   Path pathToHeuristics) {

        System.out.println("# ASTAR " + pathToHeuristics);

        List<Node> closed = new LinkedList<>();

        PriorityQueue<Node> open = new PriorityQueue<>((n1, n2) -> {
            if(n1.getCost() == n2.getCost()) {
                return n1.getState().compareTo(n2.getState());
            } else return Double.compare(n1.getCost() + heuristics.get(n1.getState()),
                    n2.getCost() + heuristics.get(n2.getState()));
        });

        open.add(new Node(null, startingState, 0));

        while (!open.isEmpty()) {
            Node currentNode = open.poll(); // where cost represents distance from starting node

            if (closed.stream().anyMatch(node -> node.getState().equals(currentNode.getState()))) continue;

            closed.add(currentNode);

            for (String goalState : goalStates) {
                if(currentNode.getState().equals(goalState)) {
                    System.out.println("[FOUND_SOLUTION]: yes");
                    System.out.println("[STATES_VISITED]: " + closed.size());

                    List<String> pathReversed = new ArrayList<>();
                    Node tmp = currentNode;
                    while (tmp != null) {
                        pathReversed.add(tmp.getState());
                        tmp = tmp.getParent();
                    }

                    System.out.println("[PATH_LENGTH]: " + pathReversed.size());
                    System.out.printf("[TOTAL_COST]: %.1f\n", currentNode.getCost());

                    System.out.print("[PATH]: ");
                    for (int i = pathReversed.size() - 1; i >= 0; i--) {
                        if (i == 0) {
                            System.out.print(pathReversed.get(i));
                            continue;
                        }
                        System.out.printf("%s => ", pathReversed.get(i));
                    }

                    return;
                }
            }

            if (!successors.containsKey(currentNode.getState())) continue;
            if (!heuristics.containsKey(currentNode.getState())) continue;

            for (StateCostPair stateCostPair : successors.get(currentNode.getState())) {
                Node newNode = new Node(
                        currentNode,
                        stateCostPair.getState(),
                        currentNode.getCost() + stateCostPair.getCost()
                );

                open.removeIf(node -> newNode.getState().equals(node.getState()) &&
                        newNode.getCost() < node.getCost());
                closed.removeIf(node -> newNode.getState().equals(node.getState()) &&
                        newNode.getCost() < node.getCost());

                open.add(newNode);
            }
        }

        System.out.println("[FOUND_SOLUTION]: no");
    }


}

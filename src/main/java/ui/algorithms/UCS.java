package ui.algorithms;

import ui.data.StateCostPair;

import java.util.*;

public class UCS {

    public static double uniformCostSearch(String startingState,
                                         Map<String, List<StateCostPair>> successors,
                                         String[] goalStates,
                                         boolean printResults) {

        Set<String> closed = new HashSet<>();

        PriorityQueue<Node> open = new PriorityQueue<>((n1, n2) -> {
            if(n1.getCost() == n2.getCost()) {
                return n1.getState().compareTo(n2.getState());
            } else return Double.compare(n1.getCost(), n2.getCost());
        });

        open.add(new Node(null, startingState, 0));

        while (!open.isEmpty()) {
            Node currentNode = open.poll(); // where cost represents distance from starting node
            if(closed.contains(currentNode.getState())) continue;

            closed.add(currentNode.getState());

            for (String goalState : goalStates) {
                if(currentNode.getState().equals(goalState)) {
                    if (printResults) {
                        System.out.println("# UCS");
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
                    }

                    return currentNode.getCost();
                }
            }

            if (!successors.containsKey(currentNode.getState())) continue;
            for (StateCostPair stateCostPair : successors.get(currentNode.getState())) {
                open.add(new Node(
                        currentNode,
                        stateCostPair.getState(),
                        currentNode.getCost() + stateCostPair.getCost())
                );
            }
        }

        System.out.println("[FOUND_SOLUTION]: no");
        return -1;
    }


}

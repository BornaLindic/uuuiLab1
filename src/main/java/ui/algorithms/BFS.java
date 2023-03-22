package ui.algorithms;

import ui.data.StateCostPair;

import java.util.*;

public class BFS {

    public static void breadthFirstSearch(String startingState,
                                          Map<String, List<StateCostPair>> successors,
                                          String[] goalStates) {
        System.out.println("# BFS");

        Set<String> closed = new HashSet<>();
        Queue<Node> open = new LinkedList<>();
        open.add(new Node(null, startingState, 0));

        while (!open.isEmpty()) {
            Node currentNode = open.poll(); // where cost represents distance to next node
            if(closed.contains(currentNode.getState())) continue;

            closed.add(currentNode.getState());

            for (String goalState : goalStates) {
                if(currentNode.getState().equals(goalState)) {
                    System.out.println("[FOUND_SOLUTION]: yes");
                    System.out.println("[STATES_VISITED]: " + closed.size());

                    List<String> pathReversed = new ArrayList<>();
                    double totalCost = 0.;
                    Node tmp = currentNode;
                    while (tmp != null) {
                        pathReversed.add(tmp.getState());
                        totalCost += tmp.getCost();
                        tmp = tmp.getParent();
                    }

                    System.out.println("[PATH_LENGTH]: " + pathReversed.size());
                    System.out.printf("[TOTAL_COST]: %.1f\n", totalCost);

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
            List<Node> neighbors = new ArrayList<>();
            for (StateCostPair stateCostPair : successors.get(currentNode.getState())) {
                neighbors.add(new Node(currentNode, stateCostPair.getState(), stateCostPair.getCost()));
            }

            neighbors.sort(Comparator.comparing(Node::getState));
            open.addAll(neighbors);
        }

        System.out.println("[FOUND_SOLUTION]: no");
    }



}

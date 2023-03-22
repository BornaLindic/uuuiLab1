package ui.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class DataLoader {

    public static StateDescriptor loadStateDescription(Path path) throws IOException {

        Scanner sc = new Scanner(path);
        String line = sc.nextLine();

        while (line.startsWith("#")) {
            line = sc.nextLine();
        }

        String startingState = line;
        String[] goalSates = sc.nextLine().split(" ");

        Map<String, List<StateCostPair>> successors = new HashMap<>();

        while (sc.hasNextLine() && !(line = sc.nextLine()).isEmpty()) {
            String[] nextStates = line.substring(line.indexOf(":")+1).strip().split(" ");
            if (nextStates[0].equals("")) continue;

            List<StateCostPair> stateCostPairs = new ArrayList<>();
            for (int i = 0; i < nextStates.length; i++) {
                String[] pair = nextStates[i].split(",");
                stateCostPairs.add(new StateCostPair(pair[0], Double.parseDouble(pair[1])));
            }

            successors.put(line.substring(0, line.indexOf(":")), stateCostPairs);
        }

        return new StateDescriptor(startingState, goalSates, successors);
    }


    public static HeuristicDescriptor loadHeuristicDescription(Path path) throws IOException {

        Scanner sc = new Scanner(path);
        String line;

        Map<String, Double> heuristics = new HashMap<>();
        while (sc.hasNextLine() && !(line = sc.nextLine()).isEmpty()) {
            String[] arguments = line.split(": ");
            heuristics.put(arguments[0], Double.parseDouble(arguments[1]));
        }

        return new HeuristicDescriptor(heuristics);
    }



}

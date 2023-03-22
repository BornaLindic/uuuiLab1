package ui.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StateDescriptor {

    private String startingState;
    private String[] goalStates;
    private Map<String, List<StateCostPair>> successors;

    public StateDescriptor(String startingState, String[] goalStates, Map<String, List<StateCostPair>> successors) {
        this.startingState = startingState;
        this.goalStates = goalStates;
        this.successors = successors;
    }

    public String getStartingState() {
        return startingState;
    }

    public String[] getGoalStates() {
        return goalStates;
    }

    public Map<String, List<StateCostPair>> getSuccessors() {
        return successors;
    }

    @Override
    public String toString() {
        return "StateDescriptor{" +
                "startingState='" + startingState + '\'' +
                ", goalStates=" + Arrays.toString(goalStates) +
                ", successors=" + successors +
                '}';
    }
}

package vfinf.zerok.zerok.classes.client;

public class Task {
    private String definition;
    private double reward;

    public Task(String definition, double reward) {
        this.definition = definition;
        this.reward = reward;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward =reward;
    }
}
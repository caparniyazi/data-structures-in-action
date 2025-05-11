package com.caparniyazi.ds.ai.agent.impl;

import com.caparniyazi.ds.ai.agent.*;
import com.caparniyazi.ds.ai.util.Tasks;

import java.util.*;

/**
 * @param <P> Type which is used to represent percepts
 * @param <A> Type which is used to represent actions
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Ruediger Lunde
 */
public abstract class AbstractEnvironment<P, A> implements Environment<P, A>, Notifier {
    // Data fields
    // Note: Use LinkedHashSet's to ensure order is respected.

    protected Set<Agent<? super P, ? extends A>> agents = new LinkedHashSet<>();
    protected Set<EnvironmentObject> envObjects = new LinkedHashSet<>();
    protected Set<EnvironmentListener<? super P, ? super A>> listeners = new LinkedHashSet<>();
    protected Map<Agent<?, ?>, Double> performanceMeasures = new LinkedHashMap<>();

    /**
     * @return Agents belonging to this environment.
     */
    @Override
    public List<Agent<?, ?>> getAgents() {
        return new ArrayList<>(agents);
    }

    /**
     * Add an agent to the environment.
     *
     * @param agent the agent to be added.
     */
    @Override
    public void addAgent(Agent<? super P, ? extends A> agent) {
        agents.add(agent);
        addEnvironmentObject(agent);
        notify(agent);
    }

    /**
     * Remove an agent from the environment.
     *
     * @param agent the agent to be removed.
     */
    @Override
    public void removeAgent(Agent<? super P, ? extends A> agent) {
        agents.remove(agent);
        removeEnvironmentObject(agent);
    }

    /**
     * Returns the EnvironmentObjects that exist in this Environment.
     *
     * @return the EnvironmentObjects that exist in this Environment.
     */
    @Override
    public List<EnvironmentObject> getEnvironmentObjects() {
        // Return as a List but also ensure the caller cannot modify.
        return new ArrayList<>(envObjects);
    }

    /**
     * Add an EnvironmentObject to the environment.
     *
     * @param eo the EnvironmentObject to be added.
     */
    @Override
    public void addEnvironmentObject(EnvironmentObject eo) {
        envObjects.add(eo);
    }

    /**
     * Remove an EnvironmentObject from the environment.
     *
     * @param eo the EnvironmentObject to be removed.
     */
    @Override
    public void removeEnvironmentObject(EnvironmentObject eo) {
        envObjects.remove(eo);
    }

    /**
     * Central template method for controlling agent simulation.
     * The concrete behavior is determined by the primitive operations
     * {@link #getPerceptSeenBy(Agent)}, {@link #execute(Agent, Object)},
     * and {@link #createExogenousChange()}.
     */
    @Override
    public void step() {
        for (Agent<? super P, ? extends A> agent : agents) {

            if (agent.isAlive()) {
                P percept = getPerceptSeenBy(agent);
                Optional<? extends A> anAction = agent.act(percept);

                if (anAction.isPresent()) {
                    execute(agent, anAction.get());
                    notify(agent, percept, anAction.get());
                } else {
                    executeNoOp(agent);
                }
            }
        }
        createExogenousChange();
    }

    /**
     * Returns true if the current task was canceled or no agent is alive anymore.
     */
    @Override
    public boolean isDone() {
        return Tasks.currIsCancelled() || agents.stream().noneMatch(Agent::isAlive);
    }

    public abstract void execute(Agent<?, ?> agent, A action);

    public abstract P getPerceptSeenBy(Agent<?, ?> agent);

    /**
     * Method for implementing dynamic environments in which not all changes are
     * directly caused by agent action execution.
     * The default implementation does nothing.
     */
    protected void createExogenousChange() {
    }

    /**
     * Method is called when an agent doesn't select an action when asked.
     * Default implementation does nothing.
     * Subclasses can, for example, modify the isDone status.
     */
    protected void executeNoOp(Agent<?, ?> agent) {
    }

    // Other methods of environment interface:

    /**
     * Retrieve the performance measure associated with an Agent.
     *
     * @param agent the Agent for which a performance measure is to be retrieved.
     * @return the performance measure associated with an Agent.
     */
    @Override
    public double getPerformanceMeasure(Agent<?, ?> agent) {
        return performanceMeasures.computeIfAbsent(agent, k -> 0.0);
    }

    /**
     * Add a listener which is notified about environment changes.
     *
     * @param listener the listener to be added.
     */
    @Override
    public void addEnvironmentListener(EnvironmentListener<? super P, ? super A> listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener.
     *
     * @param listener the listener to be removed.
     */
    @Override
    public void removeEnvironmentListener(EnvironmentListener<? super P, ? super A> listener) {
        listeners.remove(listener);
    }

    /**
     * Notify all environment listeners of a message.
     *
     * @param msg the message to notify the registered listeners with.
     */
    @Override
    public void notify(String msg) {
        listeners.forEach(listener -> listener.notify(msg));
    }

    //
    // Helper methods:

    protected void updatePerformanceMeasure(Agent<?, ?> forAgent, double addTo) {
        performanceMeasures.put(forAgent, getPerformanceMeasure(forAgent) + addTo);
    }

    /**
     * Indicates an Agent has been added to the environment and what it
     * perceives initially.
     *
     * @param agent The agent just added to the environment.
     */
    protected void notify(Agent<?, ?> agent) {
        listeners.forEach(listener -> listener.agentAdded(agent, this));
    }

    /**
     * Indicates the Environment has changed as a result of an Agent's action.
     *
     * @param agent   The Agent that performed the action.
     * @param percept The Percept the Agent received from the environment.
     * @param action  The Action the Agent performed.
     */
    protected void notify(Agent<?, ?> agent, P percept, A action) {
        listeners.forEach(listener -> listener.agentActed(agent, percept, action, this));
    }
}

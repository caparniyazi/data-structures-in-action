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

    public List<Agent<?, ?>> getAgents() {
        return new ArrayList<>(agents);
    }

    @Override
    public void addEnvironmentObject(EnvironmentObject eo) {
        envObjects.add(eo);
    }

    @Override
    public void removeEnvironmentObject(EnvironmentObject eo) {
        envObjects.remove(eo);
    }

    @Override
    public void addAgent(Agent<? super P, ? extends A> agent) {
        agents.add(agent);
        addEnvironmentObject(agent);
        notify(agent);
    }

    @Override
    public void removeAgent(Agent<? super P, ? extends A> agent) {
        agents.remove(agent);
        removeEnvironmentObject(agent);
    }

    protected void notify(Agent<?, ?> agent) {
        listeners.forEach(listener -> listener.agentAdded(agent, this));
    }


    /**
     * Returns true if the current task was canceled or no agent is alive anymore.
     */
    @Override
    public boolean isDone() {
        return Tasks.currIsCancelled() || agents.stream().noneMatch(Agent::isAlive);
    }
}

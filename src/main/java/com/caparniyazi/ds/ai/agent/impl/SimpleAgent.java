package com.caparniyazi.ds.ai.agent.impl;

import com.caparniyazi.ds.ai.agent.Agent;
import com.caparniyazi.ds.ai.agent.AgentProgram;

import java.util.Optional;

/**
 * The agent function is an abstract mathematical description;
 * the agent program is a concrete implementation, running
 * within some physical system.
 *
 * @param <P> Type which is used to represent percepts
 * @param <A> Type which is used to represent actions
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * @author Ruediger Lunde
 */

public class SimpleAgent<P, A> implements Agent<P, A> {
    // Data fields
    protected AgentProgram<P, A> program;
    private boolean alive = true;

    // Constructors
    public SimpleAgent() {
    }

    /**
     * Constructs an Agent with the specified AgentProgram.
     *
     * @param program the Agent's program, which maps any given percept sequences to
     *                an action.
     */
    public SimpleAgent(AgentProgram<P, A> program) {
        this.program = program;
    }

    @Override
    public Optional<A> act(P percept) {
        return null != program ? program.apply(percept) : Optional.empty();
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

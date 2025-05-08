package com.caparniyazi.ds.ai.agent;

import java.util.Optional;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.1, page 35.<br>
 * <p>
 * Figure 2.1 Agents interact with environments through sensors and actuators.
 * <p/>
 * For each possible percept sequence, a rational agent should select an action expected
 * to maximize its performance measure, given the evidence provided by the percept
 * sequence and whatever built-in knowledge the agent has.
 * <p/>
 * Our definition requires a rational agent not only to gather information but also to learn
 * as much as possible from what it perceives. The agent’s initial configuration could reflect
 * some prior knowledge of the environment, but as the agent gains experience, this may be
 * modified and augmented.
 * <p/>
 * To the extent that an agent relies on the prior knowledge of its designer rather than
 * on its own percepts, we say that the agent lacks autonomy. A rational agent should be
 * autonomous-it should learn what it can to compensate for partial or incorrect prior knowledge.
 * <p/>
 * When the agent has had little or no experience, it would have to act randomly unless the designer gave some assistance.
 * It would be reasonable to provide an artificial intelligent agent with some initial knowledge
 * as well as an ability to learn. After sufficient experience of its environment, the behavior
 * of a rational agent can become effectively independent of its prior knowledge.
 * Hence, the incorporation of learning allows one to design a single rational agent that will succeed in a
 * vast variety of environments.
 * <p/>
 *
 * @param <P> Type which is used to represent percepts
 * @param <A> Type which is used to represent actions
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Ruediger Lunde
 */
public interface Agent<P, A> extends EnvironmentObject {
    /**
     * Call the Agent's program, which maps any given percept sequences to an
     * action.
     *
     * @param percept The current percept of a sequence perceived by the Agent.
     * @return The Action to be taken in response to the currently perceived
     * percept. Empty replaces NoOp in earlier implementations.
     */
    Optional<A> act(P percept);

    /**
     * Life-cycle indicator as to the liveness of an Agent.
     *
     * @return Value true if the Agent is to be considered alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Set the current liveness of the Agent.
     *
     * @param alive Set to true if the Agent is to be considered alive, false
     *              otherwise.
     */
    void setAlive(boolean alive);
}

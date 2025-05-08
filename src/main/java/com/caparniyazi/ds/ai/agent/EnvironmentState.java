package com.caparniyazi.ds.ai.agent;

/**
 * An interface used to indicate a possible state of an Environment.
 * <p/>
 * When an agent is plunked down in an environment, it generates a
 * sequence of actions according to the percepts it receives.
 * This sequence of actions causes the environment to go through a sequence of states.
 * If the sequence is desirable, then the agent has performed well.
 * This notion of desirability is captured by a performance measure that
 * evaluates any given sequence of environment states.
 * <p/>
 * Notice that we said environment states, not agent states.
 * If we define success in terms of agent's opinion of its own performance,
 * an agent could achieve perfect rationality simply by deluding itself that its performance was perfect.
 * <p/>
 * As a general rule, it is better to design performance measures according to what one actually
 * wants in the environment, rather than according to how one thinks the agent should behave.
 *
 * @author Ciaran O'Reilly
 */
public interface EnvironmentState {
}

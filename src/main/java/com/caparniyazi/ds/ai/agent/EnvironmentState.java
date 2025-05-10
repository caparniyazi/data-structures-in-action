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
 * <p/>
 * If the next state of the environment is completely determined by the current state
 * and the action executed by the agent, then we say the environment
 * is deterministic; otherwise, it is stochastic.
 * Taxi driving is clearly stochastic, because one can never predict the behavior of traffic exactly;
 * moreover, one&rsquo;s tires blow out, and one&rsquo;s engine seizes up without warning.
 * <p/>
 * We say the environment is uncertain if it is not fully observable or not deterministic.
 * <p/>
 * In an episodic task environment, the agent&rsquo;s experience is
 * divided into atomic episodes.
 * In each episode, the agent receives a percept and then performs a single action.
 * Crucially, the next episode does not depend on the actions taken in previous episodes.
 * In sequential environments, on the other hand, the current decision could affect
 * all future decisions.
 * Chess and taxi driving are sequential.
 * Episodic environments are much simpler than sequential
 * environments because the agent does not need to think ahead.
 * <p/>
 * If the environment can change while an agent is deliberating, then
 * we say the environment is dynamic for that agent; otherwise, it is static.
 * If the environment itself does not change with the
 * passage of time but the agent&rsquo;s performance score does, then we say the environment is semi dynamic.
 * Taxi driving is clearly dynamic: the other cars and the taxi itself keep moving
 * while the driving algorithm dithers about what to do next.
 * Chess, when played with a clock, is semi-dynamic.
 * Crossword puzzles are static.
 * <p/>
 * The hardest case is partially observable, multi-agent, stochastic,
 * sequential, dynamic, continuous, and unknown.
 * <p/>
 * -------------------------------------------------------------------------------------
 * Task Environment: Crossword puzzle
 * Observable: Fully.
 * Agents: Single.
 * Deterministic: Deterministic.
 * Episodic: Episodic.
 * Static: Static.
 * Discrete: Discrete.
 * -------------------------------------------------------------------------------------
 * Task Environment: Chess with a clock.
 * Observable: Fully.
 * Agents: Multi.
 * Deterministic: Deterministic.
 * Episodic: Sequential.
 * Static: Semi.
 * Discrete: Discrete.
 * -------------------------------------------------------------------------------------
 * Task Environment: Backgammon.
 * Observable: Fully.
 * Agents: Multi.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Static.
 * Discrete: Discrete.
 * -------------------------------------------------------------------------------------
 * Task Environment: Taxi driving.
 * Observable: Partially.
 * Agents: Multi.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Dynamic.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Medical diagnosis.
 * Observable: Partially.
 * Agents: Single.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Dynamic.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Medical diagnosis.
 * Observable: Partially.
 * Agents: Single.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Dynamic.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Image analysis.
 * Observable: Fully.
 * Agents: Single.
 * Deterministic: Deterministic.
 * Episodic: Episodic.
 * Static: Semi.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Part-picking robot.
 * Observable: Partially.
 * Agents: Single.
 * Deterministic: Stochastic.
 * Episodic: Episodic.
 * Static: Dynamic.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Refinery controller.
 * Observable: Partially.
 * Agents: Single.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Dynamic.
 * Discrete: Continuous.
 * -------------------------------------------------------------------------------------
 * Task Environment: Interactive English tutor.
 * Observable: Partially.
 * Agents: Multi.
 * Deterministic: Stochastic.
 * Episodic: Sequential.
 * Static: Dynamic.
 * Discrete: Discrete.
 * -------------------------------------------------------------------------------------
 *
 * @author Ciaran O'Reilly
 */
public interface EnvironmentState {
}

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
 * In designing an agent, the first step must always be to specify the task environment as fully as possible.
 * We call this the PEAS (Performance, Environment, Actuators, Sensors) description.
 * <p/>
 * Agent Type: Taxi Driver.
 * Performance Measure: Safe, fast, legal, comfortable trip, maximize profits.
 * Environment: Roads, other traffic, pedestrians, customers.
 * Actuators: Steering, accelerator, brake, signal, horn, display.
 * Sensors: Cameras, sonar, speedometer, GPS, odometer, accelerometer, engine sensors, keyboard.
 * <p/>
 * Agent Type: Medical Diagnosis System
 * Performance Measure: Healthy patient, reduced costs.
 * Environment: Patient, hospital, staff.
 * Actuators: Display of questions, tests, diagnoses, treatments, referrals.
 * Sensors: Keyboard entry of symptoms, findings, patient's answers.
 * <p/>
 * Agent Type: Satellite image analysis system.
 * Performance Measure: Correct image categorization.
 * Environment: Down link from orbiting satellite.
 * Actuators: Display of scene categorization.
 * Sensors: Color pixel arrays.
 * <p/>
 * Agent Type: Part-picking robot.
 * Performance Measure: Percentage of parts in correct bins.
 * Environment: Conveyor belt with parts, bins.
 * Actuators: Jointed arm and hand.
 * Sensors: Camera, joint angle sensors.
 * <p/>
 * Agent Type: Refinery controller.
 * Performance Measure: Purity, yield, safety.
 * Environment: Refinery, operators.
 * Actuators: Valves, pumps, heaters, displays.
 * Sensors: Temperature, pressure, chemical sensors.
 * <p/>
 * Agent Type: Interactive English tutor.
 * Performance Measure: Student's score on test.
 * Environment: Set of students, testing agency.
 * Actuators: Display of exercises, suggestions, corrections.
 * Sensors: Keyboard entry.
 * <p/>
 * If an agent’s sensors give it access to the complete state of the environment at each point in time,
 * then we say that the task environment is fully observable.
 * Fully observable environments are convenient because the agent need
 * not maintain any internal state to keep track of the world.
 * An environment might be partially observable because of noisy and inaccurate sensors
 * or because parts of the state are simply missing from the sensor data.
 * If the agent has no sensors at all, then the environment is unobservable.
 * <p/>
 * An agent solving a crossword puzzle by itself is clearly in a single-agent environment,
 * whereas an agent playing chess is in a two-agent environment.
 * Chess is a competitive multi-agent environment.
 * In the taxi-driving environment, on the other hand, avoiding collisions maximizes the performance
 * measure of all agents, so it is a partially cooperative multi-agent environment.
 * <p/>
 * The agent-design problems in multi-agent environments are often quite different
 * from those in single-agent environments;
 * for example, communication often emerges as a rational behavior in multi-agent
 * environments; in some competitive environments, randomized behavior is rational because
 * it avoids the pitfalls of predictability.
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

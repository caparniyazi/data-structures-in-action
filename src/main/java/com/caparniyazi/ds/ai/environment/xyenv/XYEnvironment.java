package com.caparniyazi.ds.ai.environment.xyenv;

import com.caparniyazi.ds.ai.agent.*;
import com.caparniyazi.ds.ai.agent.impl.AbstractEnvironment;
import com.caparniyazi.ds.ai.agent.impl.DynamicPercept;
import com.caparniyazi.ds.ai.util.datastructure.XYLocation;

import java.util.*;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class XYEnvironment extends AbstractEnvironment<DynamicPercept, Action> {
    // Data fields
    private static class XYEnvironmentState implements EnvironmentState {
        int width;
        int height;

        private final Map<XYLocation, Set<EnvironmentObject>> objsAtLocation = new LinkedHashMap<>();

        public XYEnvironmentState(int width, int height) {
            this.width = width;
            this.height = height;

            for (int h = 1; h <= height; h++) {
                for (int w = 1; w <= width; w++) {
                    objsAtLocation.put(new XYLocation(h, w), new LinkedHashSet<>());
                }
            }
        }

        public void moveObjectToAbsoluteLocation(EnvironmentObject eo, XYLocation loc) {
            // Ensure is not yet at another location
            for (Set<EnvironmentObject> eos : objsAtLocation.values()) {
                if (eos.remove(eo)) {
                    break; // Should only every be at 1 location
                }
            }
            // Add it to the location specified
            getObjectsAt(loc).add(eo);
        }

        public Set<EnvironmentObject> getObjectsAt(XYLocation loc) {
            // Always ensure an empty Set is returned
            return objsAtLocation.computeIfAbsent(loc, k -> new LinkedHashSet<>());
        }

        public XYLocation getCurrentLocationFor(EnvironmentObject eo) {
            for (XYLocation loc : objsAtLocation.keySet()) {
                if (objsAtLocation.get(loc).contains(eo)) {
                    return loc;
                }
            }

            return null;
        }

        public Set<EnvironmentObject> getObjectsNear(Agent agent, int radius) {
            Set<EnvironmentObject> objsNear = new LinkedHashSet<>();
            XYLocation agentLocation = getCurrentLocationFor(agent);

            for (XYLocation loc : objsAtLocation.keySet()) {
                if (withinRadius(radius, agentLocation, loc)) {
                    objsNear.addAll(objsAtLocation.get(loc));
                }
            }
            // Ensure the 'agent' is not included in the Set of
            // objects near
            objsNear.remove(agent);

            return objsNear;
        }

        @Override
        public String toString() {
            return "XYEnvironmentState:" + objsAtLocation;
        }

        //
        // PRIVATE METHODS
        //
        private boolean withinRadius(int radius, XYLocation agentLocation, XYLocation objectLocation) {
            int xdifference = agentLocation.getX() - objectLocation.getX();
            int ydifference = agentLocation.getY() - objectLocation.getY();
            return Math.sqrt((xdifference * xdifference) + (ydifference * ydifference)) <= radius;
        }
    }

    private final XYEnvironmentState envState;

    //
    // PUBLIC METHODS
    //
    public XYEnvironment(int width, int height) {
        assert (width > 0);
        assert (height > 0);

        envState = new XYEnvironmentState(width, height);
    }

    @Override
    public void execute(Agent<?, ?> agent, Action action) {
    }

    @Override
    public DynamicPercept getPerceptSeenBy(Agent<?, ?> agent) {
        return new DynamicPercept();
    }

    public void addObjectToLocation(EnvironmentObject eo, XYLocation loc) {
        moveObjectToAbsoluteLocation(eo, loc);
    }

    public void moveObjectToAbsoluteLocation(EnvironmentObject eo, XYLocation loc) {
        // Ensure the object is not already at a location
        envState.moveObjectToAbsoluteLocation(eo, loc);

        // Ensure is added to the environment
        addEnvironmentObject(eo);
    }

    public void moveObject(EnvironmentObject eo, XYLocation.Direction direction) {
        XYLocation presentLocation = envState.getCurrentLocationFor(eo);

        if (null != presentLocation) {
            XYLocation locationToMoveTo = presentLocation.locationAt(direction);

            if (!(isBlocked(locationToMoveTo))) {
                moveObjectToAbsoluteLocation(eo, locationToMoveTo);
            }
        }
    }

    public XYLocation getCurrentLocationFor(EnvironmentObject eo) {
        return envState.getCurrentLocationFor(eo);
    }

    public Set<EnvironmentObject> getObjectsAt(XYLocation loc) {
        return envState.getObjectsAt(loc);
    }

    public Set<EnvironmentObject> getObjectsNear(Agent agent, int radius) {
        return envState.getObjectsNear(agent, radius);
    }

    public boolean isBlocked(XYLocation loc) {
        for (EnvironmentObject eo : envState.getObjectsAt(loc)) {
            if (eo instanceof Wall) {
                return true;
            }
        }

        return false;
    }

    public void makePerimeter() {
        for (int i = 0; i < envState.width; i++) {
            XYLocation loc = new XYLocation(i, 0);
            XYLocation loc2 = new XYLocation(i, envState.height - 1);
            envState.moveObjectToAbsoluteLocation(new Wall(), loc);
            envState.moveObjectToAbsoluteLocation(new Wall(), loc2);
        }

        for (int i = 0; i < envState.height; i++) {
            XYLocation loc = new XYLocation(0, i);
            XYLocation loc2 = new XYLocation(envState.width - 1, i);
            envState.moveObjectToAbsoluteLocation(new Wall(), loc);
            envState.moveObjectToAbsoluteLocation(new Wall(), loc2);
        }
    }
}

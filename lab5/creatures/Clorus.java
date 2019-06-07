package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int g,r,b;

    public Clorus (double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyneighbors = new ArrayDeque<>();
        Deque<Direction> nearbyplip = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyneighbors.add(d);
            }
            if (neighbors.get(d).name().equals("plip")) {
                nearbyplip.add(d);
            }
        }
        if (emptyneighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        if (nearbyplip.size() > 0) {
            return new Action(Action.ActionType.ATTACK,randomEntry(nearbyplip));
        }
        if (this.energy() >=1) {
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyneighbors));
        }
        return new Action(Action.ActionType.MOVE,randomEntry(emptyneighbors));
    }

    @Override
    public Color color() {
        return color(34,0,231);
    }
}

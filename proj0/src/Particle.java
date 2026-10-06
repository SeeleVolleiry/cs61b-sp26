import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Map;

public class Particle {
    public ParticleFlavor flavor;
    public int lifespan;

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        if (flavor == ParticleFlavor.PLANT
                || flavor == ParticleFlavor.FLOWER
                || flavor == ParticleFlavor.FIRE) {
            lifespan = LIFESPANS.get(flavor);
        }
        else {
            lifespan = -1;
        }
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY) {
            return Color.BLACK;
        }
        if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        }
        if (flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        }
        if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        }
        if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        }
        /*
        if (flavor == ParticleFlavor.PLANT) {
            return new Color(0, 255, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            return new Color(255, 0, 0);
        }
        if (flavor == ParticleFlavor.FLOWER) {
            return new Color(255, 141, 161);
        }*/
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }
        return Color.GRAY;
    }

    public void moveInto(Particle other) {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;
        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        Particle neighbor = neighbors.get(Direction.DOWN);
        if (neighbor.flavor == ParticleFlavor.EMPTY) {
            this.moveInto(neighbor);
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int choice = StdRandom.uniformInt(3);
        if (choice == 0) {return ;}
        if (choice == 1) {
            Particle left = neighbors.get(Direction.LEFT);
            if (left.flavor == ParticleFlavor.EMPTY) {
                this.moveInto(left);
            }
        }
        if (choice == 2) {
            Particle right = neighbors.get(Direction.RIGHT);
            if (right.flavor == ParticleFlavor.EMPTY) {
                this.moveInto(right);
            }
        }
    }

    public void grow(Map<Direction, Particle> neighbors) {
        int choice = StdRandom.uniformInt(10); // 0 ~ 9.
        if (choice == 0) {
            Particle up = neighbors.get(Direction.UP);
            if (up.flavor == ParticleFlavor.EMPTY) {
                up.flavor = this.flavor;
                up.lifespan = LIFESPANS.get(this.flavor);
            }
        }
        else if (choice == 1) {
            Particle left = neighbors.get(Direction.LEFT);
            if (left.flavor == ParticleFlavor.EMPTY) {
                left.flavor = this.flavor;
                left.lifespan = LIFESPANS.get(this.flavor);
            }
        }
        else if (choice == 2) {
            Particle right = neighbors.get(Direction.RIGHT);
            if (right.flavor == ParticleFlavor.EMPTY) {
                right.flavor = this.flavor;
                right.lifespan = LIFESPANS.get(this.flavor);
            }
        }
        else {
            return ;
        }
    }

    public void decrementLifespan() {
        if (this.lifespan > 0) {
            this.lifespan -= 1;
        }
        if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public void burn(Map<Direction, Particle> neighbors) {
        for (Direction direction : neighbors.keySet()) {
            if (neighbors.get(direction).flavor == ParticleFlavor.PLANT
                    || neighbors.get(direction).flavor == ParticleFlavor.FLOWER) {
                int choice = StdRandom.uniformInt(5);
                if (choice < 2) {
                    neighbors.get(direction).flavor = ParticleFlavor.FIRE;
                    neighbors.get(direction).lifespan = LIFESPANS.get(ParticleFlavor.FIRE);
                }
            }
        }
    }

    public void action(Map<Direction, Particle> neighbors) {
        if (this.flavor == ParticleFlavor.EMPTY) {
            return ;
        }
        if (this.flavor != ParticleFlavor.BARRIER) {
            this.fall(neighbors);
        }
        if (this.flavor == ParticleFlavor.WATER) {
            this.flow(neighbors);
        }
        if (this.flavor == ParticleFlavor.PLANT || this.flavor == ParticleFlavor.FLOWER) {
            this.grow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FIRE) {
            this.burn(neighbors);
        }
    }
}

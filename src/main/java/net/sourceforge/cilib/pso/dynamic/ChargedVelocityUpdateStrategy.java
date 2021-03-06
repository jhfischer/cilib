/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.pso.dynamic;

import java.util.Iterator;

import net.sourceforge.cilib.algorithm.AbstractAlgorithm;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.pso.PSO;
import net.sourceforge.cilib.pso.velocityupdatestrategies.StandardVelocityUpdate;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.util.Sequence;

/**
 * Velocity update strategy that the so called Charged PSO makes use of.
 * This is an implementation of the original Charged PSO algorithm
 * developed by Blackwell and Bentley and then further improved by
 * Blackwell and Branke.
 *
 * @author Anna Rakitianskaia
 *
 */
public class ChargedVelocityUpdateStrategy extends StandardVelocityUpdate {

    private static final long serialVersionUID = 365924556746583124L;
    private double pCore; // lower limit
    private double p; // upper limit

    public ChargedVelocityUpdateStrategy() {
        super();
        pCore = 1;
        p = 30;
    }

    public ChargedVelocityUpdateStrategy(ChargedVelocityUpdateStrategy copy) {
        this.inertiaWeight = copy.inertiaWeight.getClone();
        this.cognitiveAcceleration = copy.cognitiveAcceleration.getClone();
        this.socialAcceleration = copy.socialAcceleration.getClone();
        this.vMax = copy.vMax.getClone();

        this.p = copy.p;
        this.pCore = copy.pCore;
    }

    public ChargedVelocityUpdateStrategy getClone() {
        return new ChargedVelocityUpdateStrategy(this);
    }

    @Override
    public void updateVelocity(Particle particle) {
        Vector velocity = (Vector) particle.getVelocity();
        Vector position = (Vector) particle.getPosition();
        Vector bestPosition = (Vector) particle.getBestPosition();
        Vector nBestPosition = (Vector) particle.getNeighbourhoodBest().getBestPosition();

        Vector acceleration = Vector.copyOf(Sequence.repeat(0.0, velocity.size()));
        PSO pso = (PSO) AbstractAlgorithm.get();
        Iterator<Particle> iter = null;
        // make iter point to the current particle
        for (Iterator<Particle> i = pso.getTopology().iterator(); i.hasNext();) {
            if (i.next().getId() == particle.getId()) {
                iter = i;
                break;
            }
        }
        // Calculate acceleration of the current particle
        for (int i = 0; i < particle.getDimension(); ++i) {
            double accSum = 0;
            for (Iterator<Particle> j = pso.getTopology().neighbourhood(iter); j.hasNext();) {
                ChargedParticle other = (ChargedParticle) j.next();
                if (particle.getId() == other.getId()) {
                    continue;
                }
                double qi = ((ChargedParticle) particle).getCharge();
                double qj = other.getCharge();
                Vector rij = position.subtract(other.getPosition());
                double magnitude = rij.norm();
                if (pCore <= magnitude && magnitude <= p) {
                    accSum += (qi * qj / Math.pow(magnitude, 3)) * rij.doubleValueOf(i);
                }
            }
            acceleration.setReal(i, accSum);
        }

        for (int i = 0; i < particle.getDimension(); ++i) {
            double value = inertiaWeight.getParameter() * velocity.doubleValueOf(i)
                    + (bestPosition.doubleValueOf(i) - position.doubleValueOf(i)) * cognitiveAcceleration.getParameter()
                    + (nBestPosition.doubleValueOf(i) - position.doubleValueOf(i)) * socialAcceleration.getParameter()
                    + acceleration.doubleValueOf(i);
            velocity.setReal(i, value);

            clamp(velocity, i);
        }
    }

    /**
     * @return the p
     */
    public double getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(double p) {
        this.p = p;
    }

    /**
     * @return the pCore
     */
    public double getPCore() {
        return pCore;
    }

    /**
     * @param core the pCore to set
     */
    public void setPCore(double core) {
        pCore = core;
    }
}

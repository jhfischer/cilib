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
package net.sourceforge.cilib.coevolution;

import java.util.Iterator;

import net.sourceforge.cilib.algorithm.population.AbstractIterationStrategy;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.entity.Topology;
import net.sourceforge.cilib.pso.PSO;

/**
 * FIXME: Remove this class. How is it different to the normal {@linkplain SynchronousIterationStrategy}, or
 * even the normal {@linkplain ASynchronousIterationStrategy}???
 * @author Julien Duhain
 */
public class CoevolutionSynchronousIterationStrategy extends AbstractIterationStrategy<PSO> {
    private static final long serialVersionUID = 6617737228912852220L;

    /**
     * {@inheritDoc}
     */
    @Override
    public CoevolutionSynchronousIterationStrategy getClone() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public void performIteration(PSO pso) {
        Topology<Particle> topology = pso.getTopology();
        topology.update();

        for (Iterator<? extends Particle> i = topology.iterator(); i.hasNext();) {
            Particle current = i.next();
         //   current.calculateFitness(); add this line to get the standard SynchronousIterationStrategy
            for (Iterator<? extends Particle> j = topology.neighbourhood(i); j.hasNext();) {
                Particle other = j.next();
                if (current.getSocialFitness().compareTo(other.getNeighbourhoodBest().getSocialFitness()) > 0) {
                    other.setNeighbourhoodBest(current); // TODO: neighbourhood visitor?
                }
            }
        }

        for (Iterator<? extends Particle> i = topology.iterator(); i.hasNext();) {
           Particle current = i.next();
           current.updateVelocity();
           current.updatePosition();                // TODO: replace with visitor (will simplify particle interface)

           boundaryConstraint.enforce(current);
        }
    }

}

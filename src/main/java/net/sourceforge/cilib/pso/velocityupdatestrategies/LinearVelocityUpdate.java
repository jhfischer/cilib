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
package net.sourceforge.cilib.pso.velocityupdatestrategies;


import net.sourceforge.cilib.controlparameter.ConstantControlParameter;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.math.random.generator.KnuthSubtractive;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import net.sourceforge.cilib.type.types.container.Vector;


/**
 * TODO: test this.
 *
 * @author engel
 */
public class LinearVelocityUpdate extends StandardVelocityUpdate {

    private static final long serialVersionUID = -1624326615681760823L;

    private RandomProvider socialRandomGenerator;
    private RandomProvider cognitiveRandomGenerator;

    /**
     * Create an instance of {@linkplain LinearVelocityUpdate}.
     */
    public LinearVelocityUpdate() {
        super();

        // Resetting the social and cognitive components is required to ensure
        // that during the velocity update process, only 1 random number is used.
        this.cognitiveAcceleration = new ConstantControlParameter();
        this.socialAcceleration = new ConstantControlParameter();

        this.cognitiveAcceleration.setParameter(1.496180);
        this.socialAcceleration.setParameter(1.496180);

        socialRandomGenerator = new KnuthSubtractive();
        cognitiveRandomGenerator = new KnuthSubtractive();
    }


    /**
     * {@inheritDoc}
     */
    public void updateVelocity(Particle particle) {
        Vector velocity = (Vector) particle.getVelocity();
        Vector position = (Vector) particle.getPosition();
        Vector bestPosition = (Vector) particle.getBestPosition();
        Vector nBestPosition = (Vector) particle.getNeighbourhoodBest().getBestPosition();

        float social = socialRandomGenerator.nextFloat();
        float cognitive = cognitiveRandomGenerator.nextFloat();

        for (int i = 0; i < particle.getDimension(); ++i) {
            double tmp = inertiaWeight.getParameter()*velocity.doubleValueOf(i) +
                cognitive  * cognitiveAcceleration.getParameter() * (bestPosition.doubleValueOf(i) - position.doubleValueOf(i)) +
                social * socialAcceleration.getParameter() * (nBestPosition.doubleValueOf(i) - position.doubleValueOf(i));
            velocity.setReal(i, tmp);

            clamp(velocity, i);
        }
    }


    /**
     * Return the random number generator for the cognitive component.
     * @return Returns the random number generator for the cognitive component.
     */
    public RandomProvider getCongnitiveRandomGenerator() {
        return cognitiveRandomGenerator;
    }


    /**
     * @param congnitiveRandomGenerator The congnitiveRandomGenerator to set.
     */
    public void setCongnitiveRandomGenerator(RandomProvider congnitiveRandomGenerator) {
        this.cognitiveRandomGenerator = congnitiveRandomGenerator;
    }


    /**
     * @return Returns the socialRandomGenerator.
     */
    public RandomProvider getSocialRandomGenerator() {
        return socialRandomGenerator;
    }


    /**
     * @param socialRandomGenerator The socialRandomGenerator to set.
     */
    public void setSocialRandomGenerator(RandomProvider socialRandomGenerator) {
        this.socialRandomGenerator = socialRandomGenerator;
    }
}

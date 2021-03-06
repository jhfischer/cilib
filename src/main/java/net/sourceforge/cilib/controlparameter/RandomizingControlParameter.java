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
package net.sourceforge.cilib.controlparameter;

import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;

/**
 * <p>
 * A {@linkplain net.sourceforge.cilib.controlparameter.ControlParameter control parameter}
 * that is defined to return a constant multiplied with a random number.
 * </p>
 * <p>
 * This class defined the required functionality for the <tt>AccelerationComponent</tt> within a
 * <tt>VelocityUpdate</tt>.
 * </p>
 * @author Gary Pampara
 * @author Andries Engelbrecht
 */
public class RandomizingControlParameter implements ControlParameter {
    private static final long serialVersionUID = 5678971018262140893L;
    protected ControlParameter controlParameter;
    protected RandomProvider randomiser;

    /**
     * Create a new {@code RandomizingControlParameter} instance. This object will be
     * instantiated by default with a contained {@linkplain net.sourceforge.cilib.controlparameter.ControlParameter}
     * and a {@linkplain net.sourceforge.cilib.math.random.generator.MersenneTwister} as the random number generator.
     */
    public RandomizingControlParameter() {
        this.controlParameter = new ConstantControlParameter();
        this.randomiser = new MersenneTwister();
    }

    /**
     * Copy constructor.
     * @param copy The instance to copy.
     */
    public RandomizingControlParameter(RandomizingControlParameter copy) {
        this.controlParameter = copy.controlParameter.getClone();
        this.randomiser = new MersenneTwister();
    }

    /**
     * {@inheritDoc}
     */
    public RandomizingControlParameter getClone() {
        return new RandomizingControlParameter(this);
    }

    /**
     * Return the current <tt>Random</tt>.
     * @return The <tt>Random</tt> being used.
     */
    public RandomProvider getRandomiser() {
        return randomiser;
    }

    /**
     * Set the <tt>Random</tt> to be used.
     * @param randomiser The <tt>Random</tt> to be used.
     */
    public void setRandomiser(RandomProvider randomiser) {
        this.randomiser = randomiser;
    }

    /**
     * Get the currently used <tt>ControlParameterUpdateStrategy</tt>.
     * @return The used <tt>ControlParameterUpdateStrategy</tt>
     */
    public ControlParameter getControlParameter() {
        return controlParameter;
    }

    /**
     * Set the <tt>ControlParameterUpdateStrategy</tt> to be used.
     * @param controlParameter The <tt>ControlParameterUpdateStrategy</tt> to be used.
     */
    public void setControlParameter(ControlParameter controlParameter) {
        this.controlParameter = controlParameter;
    }

    /**
     * Get the value of this parameter after it has been multiplied with a uniform random number.
     * @return The value representing a uniform random number multiplied by the value of the
     *         underlying <tt>ControlParameterUpdateStrategy</tt>.
     */
    public double getParameter() {
        return this.randomiser.nextDouble() * this.controlParameter.getParameter();
    }

    /**
     * {@inheritDoc}
     */
    public double getParameter(double min, double max) {
        throw new UnsupportedOperationException("");
    }

    /**
     * Set the value of the parameter in the underlying <tt>ControlParameterUpdateStrategy</tt>.
     * @param value The value to have the parameter set to,
     */
    public void setParameter(double value) {
        this.controlParameter.setParameter(value);
    }

    /**
     * Update the contained <tt>ControlParameterUpdateStrategy</tt>.
     */
    public void updateParameter() {
        this.controlParameter.updateParameter();
    }
}

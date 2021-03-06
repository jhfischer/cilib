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
package net.sourceforge.cilib.util.selection.recipes;

import java.util.List;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.RandomProvider;
import net.sourceforge.cilib.util.selection.Samples;
import net.sourceforge.cilib.util.selection.Selection;
import net.sourceforge.cilib.util.selection.ordering.ProportionalOrdering;
import net.sourceforge.cilib.util.selection.ordering.SortedOrdering;
import net.sourceforge.cilib.util.selection.weighing.LinearWeighing;
import net.sourceforge.cilib.util.selection.weighing.Weighing;

/**
 * A recipe for Roulette wheel selection.
 * <p>
 * Roulette wheel selection is performed by:
 * <ol>
 *   <li>Weighing the elements of a selection.</li>
 *   <li>Performing a proportional ordering of the weighed elements.</li>
 *   <li>Returning the best result.</li>
 * </ol>
 * @param <E> The selection type.
 * @author Wiehann Matthysen
 */
public class RouletteWheelSelection<E extends Comparable<? super E>> implements SelectionRecipe<E> {

    private static final long serialVersionUID = 4194450350205390514L;
    private Weighing<E> weighing;
    private RandomProvider random;

    /**
     * Create a new instance.
     */
    public RouletteWheelSelection() {
        this.weighing = new LinearWeighing<E>();
        this.random = new MersenneTwister();
    }

    /**
     * Create a new instance with the provided weighing strategy.
     * @param weighing The weighing strategy to set.
     */
    public RouletteWheelSelection(Weighing<E> weighing) {
        this.weighing = weighing;
        this.random = new MersenneTwister();
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public RouletteWheelSelection(RouletteWheelSelection<E> copy) {
        this.weighing = copy.weighing.getClone();
        this.random = copy.random;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouletteWheelSelection<E> getClone() {
        return new RouletteWheelSelection<E>(this);
    }

    /**
     * Set the weighing strategy
     * @param weighing The strategy to set.
     */
    public void setWeighing(Weighing<E> weighing) {
        this.weighing = weighing;
    }

    /**
     * Get the current weighing strategy.
     * @return The current weighing strategy.
     */
    public Weighing<E> getWeighing() {
        return this.weighing;
    }

    /**
     * Set the random number generator to use.
     * @param random The value to set.
     */
    public void setRandom(RandomProvider random) {
        this.random = random;
    }

    /**
     * Get the current random number generator.
     * @return The current random number generator.
     */
    public RandomProvider getRandom() {
        return this.random;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E select(List<? extends E> elements) {
        // First, weigh and order from smallest to largest (natural ordering).
        // Boil largest elements to the front using proportional ordering
        // (as final step, elements get reversed such that largest elements are at the back).
        // Select the largest from the end and return.
        return Selection.from(elements).weigh(this.weighing).and().orderBy(new SortedOrdering<E>()).and()
                .orderBy(new ProportionalOrdering<E>(this.random)).select(Samples.last()).performSingle();
    }
}

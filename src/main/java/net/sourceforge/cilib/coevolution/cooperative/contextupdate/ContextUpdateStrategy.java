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
package net.sourceforge.cilib.coevolution.cooperative.contextupdate;

import net.sourceforge.cilib.algorithm.population.PopulationBasedAlgorithm;
import net.sourceforge.cilib.coevolution.cooperative.ContextEntity;
import net.sourceforge.cilib.coevolution.cooperative.CooperativeCoevolutionAlgorithm;
import net.sourceforge.cilib.coevolution.cooperative.problem.DimensionAllocation;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.util.Cloneable;

/**
 * This interface is used to update the context vector of a {@linkplain CooperativeCoevolutionAlgorithm}
 * with the best solution from a participating {@linkplain PopulationBasedAlgorithm}.
 * @author leo
 * @author Theuns Cloete
 */
public interface ContextUpdateStrategy extends Cloneable {
    /**
     * Decide if the given solution should be added to the given context vector.
     * @param context The current context vector.
     * @param solution The new participant solution.
     * @param allocation The {@linkplain DimensionAllocation} which indictates how the solution
     *      vector forms part of the context.
     */
    void updateContext(ContextEntity context, Vector solution, DimensionAllocation allocation);

    /**
     * {@inheritDoc}
     */
    @Override
    ContextUpdateStrategy getClone();

}

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
package net.sourceforge.cilib.functions;

import net.sourceforge.cilib.type.DomainRegistry;
import net.sourceforge.cilib.util.Cloneable;

/**
 * Function difinition. All functions apply some or other transformation
 * on a set of input variables and create an output that is representative of
 * the input.
 * @param <F> The "from" type.
 * @param <T> The "to" type.
 */
public interface Function<F, T> extends Cloneable {

    /**
     * Perfrom the evaluation of the input and return the result.
     * @param input The input for the function.
     * @return The result of the evaluation.
     */
    T apply(F input);

    /**
     * The maximum of the function.
     * @return The function maximum.
     */
    T getMaximum();

    /**
     * The minimum of the function.
     * @return The function minimum.
     */
    T getMinimum();

    /**
     * @return The dimension of the function.
     */
    int getDimension();

    /**
     * @return The domain {@linkplain String}.
     */
    String getDomain();

    /**
     * Accessor for the domain of the function. See {@link net.sourceforge.cilib.Domain.Component}.
     * @return The function domain.
     */
    DomainRegistry getDomainRegistry();

    /**
     * Sets the domain of the function.
     * @param representation the string representation for the function domain.
     */
    void setDomain(String representation);
    
}

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
package net.sourceforge.cilib.functions.continuous;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * TODO: Complete this javadoc.
 */
public class MultimodalFunction1 extends ContinuousFunction {
    private static final long serialVersionUID = -5261002551096587662L;

    /**
     * Create an instance of {@linkplain MultimodalFunction1}.
     */
    public MultimodalFunction1() {
        setDomain("R(0, 1)^1");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MultimodalFunction1 getClone() {
        return new MultimodalFunction1();
    }

    /**
     * Get the minimum value of the function. The minimum is defined to be <code>0.0</code>.
     */
    public Double getMinimum() {
        return 0.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double apply(Vector input) {
        double dResult = 0.0;
        for (int i = 0; i < getDimension(); ++i) {
            double x = Math.pow(Math.sin(5.0 * Math.PI * input.doubleValueOf(i)), 6.0);
            dResult += x;
        }
        return dResult;
    }
}

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
package net.sourceforge.cilib.problem;

import net.sourceforge.cilib.functions.Function;
import net.sourceforge.cilib.type.DomainRegistry;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * This class serves as a base class for function optimisation problems using a
 * {@link net.sourceforge.cilib.functions.Function}.
 *
 * @author  Edwin Peer
 */
public abstract class FunctionOptimisationProblem extends OptimisationProblemAdapter {
    private static final long serialVersionUID = 7944544624736580311L;

    protected Function<Vector, ? extends Number> function;

    /**
     * Creates a new instance of {@code FunctionOptimisationProblem} with {@code null} function.
     * Remember to always set a {@link net.sourceforge.cilib.functions.Function} before attempting to apply
     * an {@linkplain net.sourceforge.cilib.algorithm.Algorithm algorithm} to this problem.
     *
     * @see #setFunction(net.sourceforge.cilib.functions.Function)
     */
    public FunctionOptimisationProblem() {
        function = null;
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public FunctionOptimisationProblem(FunctionOptimisationProblem copy) {
        super(copy);
        function = copy.function;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract FunctionOptimisationProblem getClone();

    /**
     * Sets the function that is to be optimised.
     *
     * @param function The function.
     */
    public void setFunction(Function<Vector, ? extends Number> function) {
        this.function = function;
    }

    /**
     * Accessor for the function that is to be optimised.
     *
     * @return The function
     */
    public Function<Vector, ? extends Number> getFunction() {
        return function;
    }

    /**
     * Returns the component that describes the domain of the function.
     *
     * @return the domain component.
     */
    @Override
    public DomainRegistry getDomain() {
        return function.getDomainRegistry();
    }

    /**
     * Returns the error (as a double for now) that the given solution has with
     * respect to the actual optimum solution.
     *
     * @param solution the solution for which an error should be determined
     * @return the error with respect to the optimum solution
     */
    public abstract double getError(Type solution);

    @Override
    public void accept(ProblemVisitor visitor) {
        visitor.visit(this);
    }

}

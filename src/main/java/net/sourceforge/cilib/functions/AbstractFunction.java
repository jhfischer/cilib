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
import net.sourceforge.cilib.type.StringBasedDomainRegistry;

/**
 * All base function types should inherit from <code>AbstractFunction</code>.
 * @param <F> The "from" type.
 * @param <T> The "to" type.
 * @author Edwin Peer
 * @author Gary Pampara
 */
public abstract class AbstractFunction<F, T> implements Function<F, T> {
    private static final long serialVersionUID = -4843291761555348251L;

    private final DomainRegistry domainRegistry;

    /**
     * Create a new instance of {@linkplain AbstractFunction}.
     */
    protected AbstractFunction() {
        domainRegistry = new StringBasedDomainRegistry();
    }

    /**
     * Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public AbstractFunction(AbstractFunction copy) {
        domainRegistry = copy.domainRegistry.getClone();
    }

    /**
     * @return The dimension of the function.
     */
    @Override
    public int getDimension() {
        return this.getDomainRegistry().getDimension();
    }

    /**
     * Accessor for the domain of the function. See {@link net.sourceforge.cilib.Domain.Component}.
     * @return The function domain.
     */
    @Override
    public DomainRegistry getDomainRegistry() {
        return domainRegistry;
    }

    /**
     * @return The domain {@linkplain String}.
     */
    @Override
    public String getDomain() {
        return domainRegistry.getDomainString();
    }

    /**
     * Sets the domain of the function.
     * @param representation the string representation for the function domain.
     */
    @Override
    public void setDomain(String representation) {
        this.domainRegistry.setDomainString(representation);
    }

}

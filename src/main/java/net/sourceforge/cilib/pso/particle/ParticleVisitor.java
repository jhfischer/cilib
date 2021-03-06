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
package net.sourceforge.cilib.pso.particle;

import net.sourceforge.cilib.container.visitor.Visitor;
import net.sourceforge.cilib.entity.Particle;


/**
 * @author Edwin Peer
 */
public abstract class ParticleVisitor implements Visitor<Particle> {

    /**
     * Visit the provided particle.
     * @param entity The {@linkplain Particle} to apply the visitor to.
     */
    @Override
    public abstract void visit(Particle entity);
}

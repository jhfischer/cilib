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
package net.sourceforge.cilib.pso.moo.guideupdatestrategies;

import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.pso.moo.guideselectionstrategies.GuideSelectionStrategy;
import net.sourceforge.cilib.type.types.container.Vector;
import net.sourceforge.cilib.util.Cloneable;

/**
 * <p>
 * This class is used in combination with {@link GuideSelectionStrategy} to
 * determine when and if a particle's guides get updated.
 * </p>
 *
 * @author Wiehann Matthysen
 */
public interface GuideUpdateStrategy extends Cloneable {

    @Override
    GuideUpdateStrategy getClone();

    /**
     * Determines if {@code particle}'s guide (either local or global depending on {@code guideType})
     * should be updated, and updates it with {@code newGuide}.
     * @param particle The particle who's guide is to be updated.
     * @param guideType If the local or global guide should be updated.
     * @param newGuide The new guide that should replace the old guide, if it is to be updated.
     */
    void updateGuide(Particle particle, EntityType.Particle.Guide guideType, Vector newGuide);
}

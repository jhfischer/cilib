/**
 * Copyright (C) 2003 - 2008
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sourceforge.cilib.util.selection.weighingstrategies;

import java.util.Collection;
import java.util.List;

import net.sourceforge.cilib.container.Pair;

/**
 * @author Wiehann Matthysen
 * @param <T>
 */
public class InverseWeighingStrategyDecorator<T> implements WeighingStrategy<Double, T> {

    private static final long serialVersionUID = 3583023114607838669L;
    private WeighingStrategy<Double, T> weighingStrategy;

    public InverseWeighingStrategyDecorator() {
        this.weighingStrategy = null;
    }

    public InverseWeighingStrategyDecorator(InverseWeighingStrategyDecorator<T> copy) {
        this.weighingStrategy = copy.weighingStrategy.getClone();
    }

    @Override
    public InverseWeighingStrategyDecorator<T> getClone() {
        return new InverseWeighingStrategyDecorator<T>(this);
    }

    public void setWeighingStrategy(WeighingStrategy<Double, T> weighingStrategy) {
        this.weighingStrategy = weighingStrategy;
    }

    public WeighingStrategy<Double, T> getWeighingStrategy() {
        return this.weighingStrategy;
    }

    @Override
    public List<Pair<Double, T>> weigh(Collection<? extends T> objects) {
        List<Pair<Double, T>> weighedObjects = this.weighingStrategy.weigh(objects);
        for (Pair<Double, T> weighedObject : weighedObjects) {
            double weight = weighedObject.getKey();
            weighedObject.setKey(weight != 0 ? 1.0 / weight : Double.MAX_VALUE);
        }
        return weighedObjects;
    }
}
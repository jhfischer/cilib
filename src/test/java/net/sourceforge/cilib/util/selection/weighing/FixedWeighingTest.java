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
package net.sourceforge.cilib.util.selection.weighing;

import java.util.Arrays;
import java.util.List;
import net.sourceforge.cilib.util.selection.Selection;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author gpampara
 */
public class FixedWeighingTest {

    @Test
    public void fixedWeighing() {
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Selection.Entry<Integer>> weighedElements = Selection.from(elements).weigh(new FixedWeighing<Integer>(1.0)).and().entries();
        for (int i = 0; i < weighedElements.size(); ++i) {
            Assert.assertEquals(1.0, weighedElements.get(i).getWeight(), 0.0001);
        }
    }
}

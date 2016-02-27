/*
 * Copyright (c) 2015, Nifty GUI Community 
 * All rights reserved. 
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are 
 * met: 
 * 
 *  * Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer. 
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF 
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.lessvoid.nifty.types;

import de.lessvoid.niftyinternal.accessor.NiftyLinearGradientAccessor;
import de.lessvoid.niftyinternal.common.InternalNiftyColorStop;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NiftyLinearGradientTest {

  @Test
  public void testCopyConstructor() {
    NiftyLinearGradient source = createNiftyLinearGradient();
    source.addColorStop(0.0, NiftyColor.black());
    source.addColorStop(0.0, NiftyColor.blue());

    NiftyLinearGradient gradient = NiftyLinearGradientAccessor.getDefault().copyNiftyCanvas(source);
    assertEquals(Math.PI/2, gradient.getAngleInRadiants(), 0.0001);
    assertColorStops(gradient.getColorStops(), new InternalNiftyColorStop(0.0, NiftyColor.blue()));
  }

  @Test
  public void testPoints() {
    NiftyLinearGradient gradient = createNiftyLinearGradient();
    assertEquals(Math.PI/2, gradient.getAngleInRadiants(), 0.0001);
  }

  @Test
  public void testSingleColor() {
    NiftyLinearGradient gradient = createNiftyLinearGradient();
    gradient.addColorStop(0.0, NiftyColor.black());
    assertColorStops(gradient.getColorStops(), new InternalNiftyColorStop(0.0, NiftyColor.black()));
  }

  @Test
  public void testTwoColors() {
    NiftyLinearGradient gradient = createNiftyLinearGradient();
    gradient.addColorStop(0.0, NiftyColor.black());
    gradient.addColorStop(1.0, NiftyColor.green());
    assertColorStops(
        gradient.getColorStops(),
        new InternalNiftyColorStop(0.0, NiftyColor.black()),
        new InternalNiftyColorStop(1.0, NiftyColor.green()));
  }

  @Test
  public void testThreeColors() {
    NiftyLinearGradient gradient = createNiftyLinearGradient();
    gradient.addColorStop(0.0, NiftyColor.black());
    gradient.addColorStop(0.5, NiftyColor.red());
    gradient.addColorStop(1.0, NiftyColor.green());
    assertColorStops(
        gradient.getColorStops(),
        new InternalNiftyColorStop(0.0, NiftyColor.black()),
        new InternalNiftyColorStop(0.5, NiftyColor.red()),
        new InternalNiftyColorStop(1.0, NiftyColor.green()));
  }

  @Test
  public void testReplaceColor() {
    NiftyLinearGradient gradient = createNiftyLinearGradient();
    gradient.addColorStop(0.0, NiftyColor.black());
    gradient.addColorStop(0.0, NiftyColor.blue());
    assertColorStops(gradient.getColorStops(), new InternalNiftyColorStop(0.0, NiftyColor.blue()));
  }

  private NiftyLinearGradient createNiftyLinearGradient() {
    return NiftyLinearGradient.createFromAngleInRad(Math.PI/2);
  }

  private void assertColorStops(final List<InternalNiftyColorStop> colorStops, final InternalNiftyColorStop... stops) {
    assertEquals(colorStops.size(), stops.length);
    for (int i=0; i<stops.length; i++) {
      assertTrue(colorStops.get(i).equals(stops[i]));
      assertTrue(colorStops.get(i).getColor().equals(stops[i].getColor()));
    }
  }
}
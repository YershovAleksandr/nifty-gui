package de.lessvoid.nifty.effects.general;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.render.NiftyImageMode;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.tools.Color;
import de.lessvoid.nifty.tools.TimeProvider;
import de.lessvoid.nifty.tools.pulsate.Pulsator;

/**
 * ImagePulsate - image color pulsate.
 * @author void
 */
public class ImageOverlayPulsate implements EffectImpl {

  /**
   * overlay image.
   */
  private NiftyImage image;

  /**
   * the pulsater.
   */
  private Pulsator pulsater;

  /**
   * Initialize.
   * @param nifty Nifty
   * @param element Element
   * @param parameter Parameter
   */
  public void initialize(final Nifty nifty, final Element element, final Properties parameter) {
    image = nifty.getRenderDevice().createImage(parameter.getProperty("filename"), true);
    String subImageSizeMode = parameter.getProperty("imageMode", null);
    if (subImageSizeMode != null) {
      image.setImageMode(NiftyImageMode.valueOf(subImageSizeMode));
    }
    this.pulsater = new Pulsator(parameter, new TimeProvider());
  }

  /**
   * execute the effect.
   * @param element the Element
   * @param normalizedTime TimeInterpolator to use
   * @param r RenderDevice to use
   */
  public void execute(
      final Element element,
      final float normalizedTime,
      final NiftyRenderEngine r) {
    float value = pulsater.update();
    r.setColor(new Color(1.0f, 1.0f, 1.0f, value));
    r.renderImage(image, element.getX(), element.getY(), element.getWidth(), element.getHeight());
  }
}

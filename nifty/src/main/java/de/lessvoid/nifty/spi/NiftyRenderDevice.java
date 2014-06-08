package de.lessvoid.nifty.spi;

import java.nio.FloatBuffer;

import de.lessvoid.nifty.api.NiftyLinearGradient;

/**
 * NiftyRenderDevice is part of the SPI that allows Nifty to use different graphics backends. Everything that can
 * be rendered will go through implementations of this interface.
 *
 * @author void
 */
public interface NiftyRenderDevice {

  /**
   * Get the width of the display.
   * @return width of display mode
   */
  int getDisplayWidth();

  /**
   * Get the height of the display.
   * @return height of display mode
   */
  int getDisplayHeight();

  /**
   * Enable clearing the screen when rendering.
   */
  void clearScreenBeforeRender(boolean clearScreenBeforeRender);

  /**
   * Create a texture of the given width and height.
   *
   * @param width the width of the texture
   * @param height the height of the texture
   */
  NiftyTexture createTexture(int width, int height);

  void render(NiftyTexture renderTarget, FloatBuffer vertices);
  void renderColorQuads(FloatBuffer vertices);
  void renderLinearGradientQuads(NiftyLinearGradient gradientParams, FloatBuffer vertices);

  void begin();
  void end();

  void beginRenderToTexture(NiftyTexture texture);
  void endRenderToTexture(NiftyTexture texture);
}

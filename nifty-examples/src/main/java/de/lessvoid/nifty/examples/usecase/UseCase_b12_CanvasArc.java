package de.lessvoid.nifty.examples.usecase;

import java.io.IOException;

import de.lessvoid.nifty.api.ChildLayout;
import de.lessvoid.nifty.api.Nifty;
import de.lessvoid.nifty.api.NiftyCanvas;
import de.lessvoid.nifty.api.NiftyCanvasPainter;
import de.lessvoid.nifty.api.NiftyColor;
import de.lessvoid.nifty.api.NiftyFont;
import de.lessvoid.nifty.api.NiftyLineCapType;
import de.lessvoid.nifty.api.NiftyNode;
import de.lessvoid.nifty.api.NiftyStatisticsMode;
import de.lessvoid.nifty.api.UnitValue;
import de.lessvoid.nifty.api.VAlign;

/**
 * An example how to draw arcs into a NiftyCanvas.
 * @author void
 */
public class UseCase_b12_CanvasArc {
  private static NiftyFont font;
  private static float v = 1.99f;

  public UseCase_b12_CanvasArc(final Nifty nifty) throws IOException {
    font = nifty.createFont("fonts/aurulent-sans-16.fnt");

    nifty.showStatistics(NiftyStatisticsMode.ShowFPS);

    NiftyNode rootNode = nifty.createRootNodeFullscreen(ChildLayout.Horizontal);
    rootNode.setBackgroundColor(NiftyColor.BLACK());

    NiftyNode butt = rootNode.newChildNode(UnitValue.px(256), UnitValue.px(256));
    butt.setVAlign(VAlign.center);
    butt.startAnimatedRedraw(0, 16);
    butt.addCanvasPainter(new NiftyCanvasPainter() {
      @Override
      public void paint(final NiftyNode node, final NiftyCanvas canvas) {
        draw(node, canvas, NiftyLineCapType.Butt);
      }
    });

    NiftyNode square = rootNode.newChildNode(UnitValue.px(256), UnitValue.px(256));
    square.setVAlign(VAlign.center);
    square.startAnimatedRedraw(0, 16);
    square.addCanvasPainter(new NiftyCanvasPainter() {
      @Override
      public void paint(final NiftyNode node, final NiftyCanvas canvas) {
        draw(node, canvas, NiftyLineCapType.Square);
      }
    });

    NiftyNode round = rootNode.newChildNode(UnitValue.px(256), UnitValue.px(256));
    round.setVAlign(VAlign.center);
    round.startAnimatedRedraw(0, 16);
    round.addCanvasPainter(new NiftyCanvasPainter() {
      @Override
      public void paint(final NiftyNode node, final NiftyCanvas canvas) {
        draw(node, canvas, NiftyLineCapType.Round);
      }
    });

    NiftyNode animated = rootNode.newChildNode(UnitValue.px(256), UnitValue.px(256));
    animated.setVAlign(VAlign.center);
    animated.startAnimatedRedraw(0, 16);
    animated.addCanvasPainter(new NiftyCanvasPainter() {
      @Override
      public void paint(final NiftyNode node, final NiftyCanvas canvas) {
        canvas.setLineCap(NiftyLineCapType.Square);
        canvas.setFillStyle(NiftyColor.BLUE());
        canvas.fillRect(0, 0, node.getWidth(), node.getHeight());

        canvas.setTextColor(NiftyColor.WHITE());
        canvas.text(font, 5, 5, "Animated ;)");

        canvas.beginPath();
        canvas.arc(128., 128., 100., v * Math.PI, (v + 1.25) * Math.PI);
        canvas.setLineWidth(24);
        canvas.setStrokeColor(NiftyColor.WHITE());
        canvas.stroke();

        canvas.beginPath();
        canvas.arc(128., 128., 100., v * Math.PI, (v + 1.25) * Math.PI);
        canvas.setLineWidth(1);
        canvas.setStrokeColor(NiftyColor.BLACK());
        canvas.stroke();

        v += 0.001;
        if (v > 2) {
          v -= 2;
        }
      }
    });
  }

  private static void draw(final NiftyNode node, final NiftyCanvas canvas, final NiftyLineCapType cap) {
    canvas.setLineCap(cap);
    canvas.setFillStyle(NiftyColor.BLUE());
    canvas.fillRect(0, 0, node.getWidth(), node.getHeight());

    canvas.setTextColor(NiftyColor.WHITE());
    canvas.text(font, 5, 5, cap.toString());

    canvas.beginPath();
    canvas.arc(128., 128., 100., 0.5 * Math.PI, 1.25 * Math.PI);
    canvas.setLineWidth(24);
    canvas.setStrokeColor(NiftyColor.WHITE());
    canvas.stroke();

    canvas.beginPath();
    canvas.arc(128., 128., 100., 0.5 * Math.PI, 1.25 * Math.PI);
    canvas.setLineWidth(1);
    canvas.setStrokeColor(NiftyColor.BLACK());
    canvas.stroke();
  }

  public static void main(final String[] args) throws Exception {
    UseCaseRunner.run(UseCase_b12_CanvasArc.class, args);
  }
}

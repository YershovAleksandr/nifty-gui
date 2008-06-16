package de.lessvoid.nifty.loader.xpp3.processor;


import de.lessvoid.nifty.loader.xpp3.Attributes;
import de.lessvoid.nifty.loader.xpp3.NiftyLoader;
import de.lessvoid.nifty.loader.xpp3.XmlParser;

/**
 * UseStylesProcessor.
 * @author void
 */
public class UseStylesTypeProcessor implements XmlElementProcessor {
  /**
   * nifty loader.
   */
  private NiftyLoader niftyLoader;

  /**
   * create the processor.
   * @param newNiftyLoader nifty loader
   */
  public UseStylesTypeProcessor(final NiftyLoader newNiftyLoader) {
    niftyLoader = newNiftyLoader;
  }

  /**
   * process.
   * @param xmlParser XmlParser
   * @param attributes Attributes
   * @throws Exception exception
   */
  public void process(final XmlParser xmlParser, final Attributes attributes) throws Exception {
    if (attributes.isSet("filename")) {
      niftyLoader.loadNiftyStyles(attributes.get("filename"));
    }
    xmlParser.nextTag();
  }
}

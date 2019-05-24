package gov.cancer.pageobject.section;

import java.net.URL;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.Link;
import gov.cancer.pageobject.image.Image;

public class FeatureCard {

  static final String TITLE_SELECTOR = ":scope h3";
  static final String IMAGE_SELECTOR = ":scope img";
  static final String DESCR_SELECTOR = ":scope p";
  static final String LINK_SELECTOR = ":scope a";

  // WebElement containing the entire feature card.
  WebElement card;

  // The image appearing on the feature card.
  Image image;

  // Card title
  WebElement title;

  // Card description (May be null).
  WebElement description;

  // The link the card goes to.
  Link cardLink;

  /**
   * Constructor
   * @param element WebElement containing the feature card's markup.
   */
  public FeatureCard(WebElement element) {
    this.card = element;

    title = ElementHelper.findElement(card, TITLE_SELECTOR);
    description = ElementHelper.findElement(card, DESCR_SELECTOR);

    WebElement im = ElementHelper.findElement(card, IMAGE_SELECTOR);
    image = new Image(im);

    /*
        Link retrieval is removed temporarily due to NCIOCPL/cgov-digital-platform issue #1820
        NOTE: The LINK_SELECTOR value will need to be modified to account for the possibility
        of a second link containing the exit link disclaimer. (It will need to make sure
        it only the *first* link of the two is retrieved.)
    */
    // WebElement lnk = ElementHelper.findElement(card, LINK_SELECTOR);
    // cardLink = new Link(lnk);
  }

  /**
   * Retrieve the card's promotional image or override.
   * @return An Image object containing the card's promotional image.
   */
  public Image getImage() {
    return this.image;
  }

  /**
   * Retrieve the card's title.
   * @return String containing the title text.
   */
  public String getTitle() {
    return this.title.getText();
  }

  /**
   * Retrieve the card's description.
   *
   * @return String containing the description if present, null otherwise.
   */
  public String getDescription() {
    if (description != null)
      return description.getText();
    else
      return null;
  }

  /**
   * Retrieve the link the card goes to.
   *
   * @return A Link instance representing the link.
   */
  public Link getLink() {
    return cardLink;
  }
}

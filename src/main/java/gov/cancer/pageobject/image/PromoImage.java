package gov.cancer.pageobject.image;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class PromoImage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#page")
  WebElement pageBody;

  /********* FOOTER SELECTORS ***********************/

  final public String imagecontainer = ".feature-primary";
  final public String featurecard = "[class='large-6 columns topic-feature card gutter ']:nth-of-type(1) .feature-card";
  final public String promoimage = "div[class='image-hover'] img";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public PromoImage(String path) {
    super(path);

  }

  /* Returns true if Lead image is displayed on the page */
  public boolean isPromoImageVisible() {
    return ElementHelper.isVisible(pageBody, promoimage);
  }

  /* Returns the caption of the image */
  public boolean isPromoImageSrcCorrect(String expectedSrc) {
    String scrPromo = ElementHelper.findElement(pageBody, promoimage).getAttribute("src");
    return scrPromo.contains(expectedSrc);
  }

  /* Returns the alt text of the image */
  public String getAltText() {
    WebElement ImageDiv = ElementHelper.findElement(pageBody, promoimage);
    return ImageDiv.getAttribute("alt");
  }

}

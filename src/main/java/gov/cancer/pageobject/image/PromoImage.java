package gov.cancer.pageobject.image;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.Configuration;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.image.PagewithFeatureCard;

public class PromoImage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#page")
  WebElement pageBody;

  /********* FOOTER SELECTORS ***********************/

  final public String imagecontainer = ".feature-primary";
  final public String featurecard = "[class='large-6 columns topic-feature card gutter ']:nth-of-type(1) .feature-card";
  final public String promoimage = "div[class='image-hover'] img";
  
  WebElement element;
  WebElement featureCard= ElementHelper.findElement(pageBody, featurecard);
  PagewithFeatureCard cardpage = new PagewithFeatureCard(featureCard);
  
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
  public boolean isPromoImageVisibleCard() {
    return ElementHelper.isVisible(pageBody, promoimage);
  }

  /* Returns the caption of the image */
  public boolean isPromoImageSrcCorrect(String expectedSrc) {
    
    String srcPromo = cardpage.getFeaturedImage().getAttribute("src");
    System.out.println(srcPromo);
    return srcPromo.contains(expectedSrc);
  }

  /* Returns the alt text of the image 
  public String getAltText() {
    WebElement ImageDiv = ElementHelper.findElement(pageBody, promoimage);
    return ImageDiv.getAttribute("alt");
  }
  
   //Returns true if Lead image is displayed on the page 
  public List<WebElement> getListItems() {
   List<WebElement> listItems= ElementHelper.findElements(pageBody, pagelists);
         return listItems;
    } */

}

package gov.cancer.pageobject.image;

import java.net.URL;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

public class Image extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#page")
  WebElement pageBody;

  /********* Image SELECTORS ***********************/

  final public String image = "div[class='image-hover'] img";

  WebElement figure;

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public Image(String path) {
    super(path);

  }

  /* Returns the source url of the image */
  public URL getSrcUrl() {
    throw new NotImplementedException("TODO:implement getSrcUrl method");
  }

  /* Returns the caption of the image */
  public String getCaption() {
    throw new NotImplementedException("TODO:implement getCaption method");
  }

  /* Returns the caption of the image */
  public String getCredit() {
    throw new NotImplementedException("TODO:implement getCredit method");
  }

  /* Returns the alt text of the image */
  public String getAltText() {
    throw new NotImplementedException("TODO:implement getAltText method");
  }

}

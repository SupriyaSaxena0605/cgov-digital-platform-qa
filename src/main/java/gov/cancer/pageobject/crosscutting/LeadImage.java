package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class LeadImage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#cgvBody")
  WebElement pageBody;

  /********* LEAD IMAGE SELECTORS ***********************/

  final public String imagecontainer = "div#cgvBody > div > figure";
  final public String imagecaption = "div.caption-container p";
  final public String imagecredit = ".image-photo-credit";
  final public String imagediv = "div[class='centered-element'] img";

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public LeadImage(String path) {
    super(path);

  }

  /* Returns true if Lead image is displayed on the page */
  public boolean isLeadImageVisible() {
    return ElementHelper.isVisible(getBrowser(), imagecontainer);
  }

  /* Returns the caption of the image */
  public String getCaption() {
    return ElementHelper.findElement(getBrowser(), imagecaption).getText();
  }

  /* Returns the credit of the image */
  public String getCredit() {

    return ElementHelper.findElement(getBrowser(), imagecredit).getText();
  }

  /* Returns the alt text of the image */
  public String getAltText() {
    WebElement ImageDiv = ElementHelper.findElement(getBrowser(), imagediv);
    return ImageDiv.getAttribute("alt");
  }

}

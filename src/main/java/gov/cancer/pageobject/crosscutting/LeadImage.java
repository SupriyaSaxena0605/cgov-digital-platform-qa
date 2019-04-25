package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class LeadImage extends PageObjectBase {

  
  /********* FOOTER SELECTORS ***********************/

  final public String imagecontainer = ".embedded-entity";
  
  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public LeadImage(String path) {
    super(path);
     

  }
  
  /* Returns true if Lead image is displayed on the page */
  public boolean isLeadImageVisible() {
    return ElementHelper.findElement(getBrowser(), imagecontainer).isDisplayed();
  }
}

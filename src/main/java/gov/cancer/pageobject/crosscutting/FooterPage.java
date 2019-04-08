package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The FooterPage class
 * is used solely for verifying attributes of a page's Footer section.
 */
public class FooterPage extends PageObjectBase {

  @FindBy(how = How.CSS, using = "#nvcgSlFooter")
  WebElement pageFooter;

  final public String Footer_header = "National Cancer Institute";
  final public String Footer_headerEsp = "Instituto Nacional del Cáncer";

  /********* FOOTER SELECTORS ***********************/

  final public String footerheader = "div[class='site-footer__header']>h1";
  final public String backtoTop = "a.back-to-top";

  /********* FOOTER Page Methods ***********************/

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public FooterPage(String path) {
    super(path);
  }

  /* Returns true if Back to Top is displayed */
  public boolean isbackToTopVisible() {
    return ElementHelper.findElement(pageFooter, backtoTop).isDisplayed();
  }

  /* Returns true if Back to Top label is correct */
  public boolean getbackToTop() {
    Boolean header_match = false;
    header_match = ElementHelper.findElement(pageFooter, backtoTop).getText().contains("TOP");
    return header_match;
  }

  /* Returns true if Spanish Back to Top label is correct */
  public boolean getbackToTopEsp() {
    Boolean header_matchesp = false;
    header_matchesp = ElementHelper.findElement(pageFooter, backtoTop).getText().contains("SUBIR");
    return header_matchesp;
  }

  /* Returns true if Footer is displayed on the page */
  public boolean isFooterVisible() {
    return pageFooter.isDisplayed();
  }

  /* Returns true if header of Footer is displayed */
  public boolean isFooterHeaderVisible() {
    return ElementHelper.findElement(pageFooter, footerheader).isDisplayed();
  }

  /* Returns true if header of the Footer is correct */
  public boolean getFooterHeader() {
    Boolean header_match = false;
    header_match = ElementHelper.findElement(pageFooter, footerheader).getText().contains(Footer_header);
    return header_match;
  }

  /* Returns true if Spanish header of the Footer is correct */
  public boolean getFooterHeaderEsp() {
    Boolean header_matchesp = false;
    header_matchesp = ElementHelper.findElement(pageFooter, footerheader).getText().contains(Footer_headerEsp);
    return header_matchesp;
  }

  /* Returns text of Footer */
  public String footerText() {

    return pageFooter.getText().trim();
  }

  /* Returns true if Footer is displayed once on the page */
  public boolean isFooterVisibleOnce() {
    List<WebElement> findfooter = ElementHelper.findElements(pageFooter, "footer[class='site-footer']");
    int footerexists = findfooter.size();
    if (footerexists == 1) {
      return true;
    } else {
      return false;
    }
  }

}

package gov.cancer.pageobject.crosscutting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;

public class LanguageToggle extends PageObjectBase {

  /********* FOOTER SELECTORS ***********************/

  final public String languagetoggle = "#LangList1";

  @FindBy(how = How.CSS, using = ".language-bar")
  WebElement languagebar;

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public LanguageToggle(String path) {
    super(path);

  }

  /* Returns true if language toggle is displayed on the page */
  public boolean isToggleVisible() {
    return ElementHelper.findElement(getBrowser(), languagetoggle).isDisplayed();
  }

  /* Returns label of the language toggle */
  public String getTogglelabel() {
    return ElementHelper.findElement(languagebar, languagetoggle).getText();
  }

}

package gov.cancer.pageobject.cts;

import org.openqa.selenium.WebElement;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.CTS_Section;

/**
 * This class represents Basic Search page of CTS module
 */
public class BasicSearchPage extends CTS_Section {

  //String basicpath = "/about-cancer/treatment/clinical-trials/search";
  final public String css_introtext = ".search-page__header p";
  final public String css_searchtip = ".cts-search-tip";
  final public String css_cancertype = "#fieldset--type";
  WebElement introtext;
  WebElement searchtip;
  WebElement cancertype;
  final public String pageheader = "Find NCI-Supported Clinical Trials";
  CTS_Section cts;
  /**
   * Constructor
   * 
   * @param path
   */
  public BasicSearchPage(String path) {
    super(path);
    introtext = ElementHelper.findElement(getBrowser(), css_introtext);
    searchtip = ElementHelper.findElement(getBrowser(), css_searchtip);
    cancertype = ElementHelper.findElement(getBrowser(), css_cancertype);
  }

  /* Check if header element is correct */

  public boolean ispageHeaderCorrect(String path) {
System.out.println("Header" + getHeader(path));
    return getHeader(path).equals(pageheader);
  }

  /* Retrieve Introtext */
  public String getIntroText() {
    System.out.println("Intro: " + introtext.getText());
    return introtext.getText();
  }

  /* Retrieve Steps URL */
  public String getStepsURL() {
    System.out.println("Steps URL: " + ElementHelper.findElement(introtext, ":scope a").toString());
    return ElementHelper.findElement(introtext, ":scope a").toString();
  }

  /* Retrieve web element Search Tip */
  public WebElement getSearchTip() {
    System.out.println("Steps URL: " + ElementHelper.findElement(introtext, ":scope a").toString());
    return searchtip;
  }

  /* return true if web element Search Tip Icon is displayed */
  public boolean isSearchTipIconDisplayed() {
    System.out.println("Steps URL: " + ElementHelper.findElement(getSearchTip(), ".cts-search-tip__icon").getClass());
    return ElementHelper.findElement(getSearchTip(), ".cts-search-tip__icon").isDisplayed();
  }
  
  /* Retrieve web element Search Tip Text */
  public String getSearchTiptext() {
    System.out.println("Steps URL: " + ElementHelper.findElement(getSearchTip(), ".cts-search-tip__body").getText());
    return ElementHelper.findElement(getSearchTip(), ".cts-search-tip__body").getText();
  }
  
  /* Retrieve web element Advanced Search link */
  public WebElement getAdvSearchLink() {
    System.out.println("Adv Search URL: " + ElementHelper.findElement(getSearchTip(), ".btnAsLink").getText());
    return ElementHelper.findElement(getSearchTip(), ".btnAsLink");
  }
  
  /* Retrieve label of Cancer Type/Keyword */
  public WebElement getCancerType() {
    //System.out.println("Steps URL: " + ElementHelper.findElement(getSearchTip(), ".cts-search-tip__body").getText());
    return cancertype;
  }
  
  /* Retrieve label of Cancer Type/Keyword */
  public String getCancerTypeLabel() {
    System.out.println("Cancer type label: " + ElementHelper.findElement(cancertype, ":scope .cts-fieldset__legend span").getText());
    return ElementHelper.findElement(cancertype, ":scope .cts-fieldset__legend span").getText();
  }
  
  /* Retrieve label of Cancer Type/Keyword */
  public boolean isCancerTypeHelpDisplayed() {
    System.out.println("Cancer type label: " + ElementHelper.findElement(cancertype, ":scope .text-icon-help").getText());
    return ElementHelper.findElement(cancertype, ":scope .text-icon-help").isDisplayed();
  }
  
  /* Retrieve label of Cancer Type/Keyword */
  public boolean isCancerTypeInputfieldDisplayed() {
    System.out.println("Cancer type input: " + ElementHelper.findElement(cancertype, ":scope #ctk").getClass());
    return ElementHelper.findElement(cancertype, ":scope #ctk").isDisplayed();
  }
  
  /* Retrieve label of Cancer Type/Keyword */
  public String getCancerTypeHelpText() {
    System.out.println("Cancer type help text: " + ElementHelper.findElement(cancertype, ":scope .cts-input__help-text").getText());
    return ElementHelper.findElement(cancertype, ":scope .cts-input__help-text").getText();
  }
}

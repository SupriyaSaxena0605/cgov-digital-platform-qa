package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementChange;
import gov.cancer.framework.ElementHelper;
import gov.cancer.framework.ScrollUtil;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.components.RadioButton;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents location section on Advanced Search Page
 */
public class LocationSection extends Component {

  // Veteran Affairs toggle
  private WebElement limitResultsToggle;
  //section title
  private WebElement title;
  // help link
  private Link helpLink;
  //webdriver instance is used to initialize CountryStateCitySubSection and HospitalsInstitutionSubSection,
  //which in it's turn contain AutoSuggest fields (where driver is needed)
  private WebDriver driver;
  //main element
  private WebElement scope;


  /************LOCATORS***************/
  private final static String LIMIT_RESULT_LOCATOR = "div.cts-toggle label[for='search-location-toggle']";
  private final static String HELP_LINK_LOCATOR = ":scope legend a";
  private final static String TITLE_LOCATOR = ":scope legend span";
  private final static String RADIO_BUTTONS_LOCATOR = ":scope .group-locations >.cts-radio >label";


  /**
   * Constructor
   */
  public LocationSection(WebDriver driver, WebElement element) {
    super(element);
    this.driver = driver;
    this.scope = element;
    limitResultsToggle = ElementHelper.findElement(scope, LIMIT_RESULT_LOCATOR);
    title = ElementHelper.findElement(scope, TITLE_LOCATOR);
    helpLink = new Link(ElementHelper.findElement(scope, HELP_LINK_LOCATOR));

  }
  /**
   * Getter for all radioButton
   */
  public List<RadioButton> getAllRadioButtons() {
    List<RadioButton> allRadioButtonsObject = new ArrayList<>();
     List<WebElement> allRadioButtons = new ArrayList<>(ElementHelper.findElements(scope,RADIO_BUTTONS_LOCATOR));
    for (int i=0;i<allRadioButtons.size();i++){
      allRadioButtonsObject.add(new RadioButton(allRadioButtons.get(i)));
    }
     return allRadioButtonsObject;
  }

  /**
   * Getter for Zip Code radioButton
   */
  public RadioButton getRadioButton(int i) {
    List <RadioButton> radioButtons = getAllRadioButtons();
    return radioButtons.get(i);
  }


   /**
   * methods to click on limitResultToggle
   *
   * @param limit - switchToggle if 'Yes'(true) and it is not selected already, then method clicks on a toggle
   *              *                     if 'No' (false) and it is selected, then click to unselect
   */

  public void limitToVaOnly(boolean limit) {
    if (limit && !(ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed())) {
      limitResultsToggle.click();
      ElementChange.WaitForText(driver, limitResultsToggle, "Yes");
    } else if (!limit && ElementHelper.findElement(limitResultsToggle, ":scope .pos").isDisplayed()) {
      limitResultsToggle.click();
      ElementChange.WaitForText(driver, limitResultsToggle, "No");
    }
  }

  /**
   * Returns title text
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * Getter for Help Link
   *
   * @return
   */
  public Link getHelpLink() {
    return helpLink;
  }

   /**
   * Getter for Country/State/City SubSection
   *
   * @return
   */
  public CountryStateCitySubSection getCountryStateCitySubSection() {
    return new CountryStateCitySubSection(driver, ElementHelper.findElement(scope, ":scope div[class='search-location__block search-location__country']"));
  }

  /**
   * Getter for ZipCode subsection
   *
   * @return
   */
  public ZipCodeSubSection getZipCodeSubSection() {
    return new ZipCodeSubSection(ElementHelper.findElement(scope, ":scope div[class='search-location__block search-location__zip']"));
  }

    /**
   * Getter for Hospitals subsection
   */
  public HospitalsInstitutionSubSection getHospitalsInstitutionSubSection() {
    return new HospitalsInstitutionSubSection(driver, ElementHelper.findElement(scope, ".search-location__block"));
  }

  /**
   *This method is scrolling until the Veteran Affairs toggle is visible
   * It is necessary, because of the presence of 'sticky block (form action)' which receives the click
   */
  public void scrollUntilToggleVisible(){
    ScrollUtil.scrollIntoview(driver, limitResultsToggle);
    }

  /**
   * This method is using JavaScript to get the state of toggle (which behaves as an invisible checkbox)
   */
  public boolean getToggleState() {
    JavascriptExecutor javaScript = (JavascriptExecutor)driver;
    return Boolean.parseBoolean(javaScript.executeScript("return document.getElementById('search-location-toggle').checked").toString());
  }

  /**
   * This method is using JavaScript to get the state of Search All Locations radio button input tag (which appears to be invisible )
   */
  public boolean isSelected(int index) {
    JavascriptExecutor javaScript = (JavascriptExecutor)driver;
    return Boolean.parseBoolean(javaScript.executeScript("return document.querySelectorAll('.group-locations >.cts-radio input')["+index+"].checked").toString());

  }

}

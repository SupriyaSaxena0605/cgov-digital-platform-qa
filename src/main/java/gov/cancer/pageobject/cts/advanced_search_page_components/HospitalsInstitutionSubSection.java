package gov.cancer.pageobject.cts.advanced_search_page_components;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.components.Component;
import gov.cancer.pageobject.cts.components.AutoSuggest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represent Hospitals and Institutions SubSection on Location Section- hidden until
 * corresponding radioButton is selected
 */
public class HospitalsInstitutionSubSection extends Component {

  //AutoSuggest field for hospital/institutions
  private AutoSuggest hospitalInput;
  //main element
  private WebElement element;
  //WebDriver is used for AutoSuggest initialization that is outside of constructor
  private WebDriver driver;

  /**
   * Constructor
   *
   * @param element - component element
   */
  public HospitalsInstitutionSubSection(WebDriver driver, WebElement element) {
    super(element);
    this.element = element;
    this.driver=driver;

  }

  /**
   * Getter method for hospital input field
   * Initialized this autosuggest field outside the constructor as it is not displayed on page load.
   * It is only displayed when Hospitals Radio button is clicked
   *
   * @return
   */
  public AutoSuggest getHospitalInput() {
    hospitalInput = new AutoSuggest(driver, ElementHelper.findElement(element, "div.cts-autocomplete"));
    return hospitalInput;
  }
}

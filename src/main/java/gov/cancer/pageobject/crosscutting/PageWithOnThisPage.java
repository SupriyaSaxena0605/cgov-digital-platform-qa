package gov.cancer.pageobject.crosscutting;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.section.BodyOfPage;
import gov.cancer.pageobject.section.OnThisPage;
public class PageWithOnThisPage extends PageObjectBase {
  private WebElement otpSection;
  private WebElement bodySection;
  // "On This Page" section
  final private String otpSectionSelector = ".on-this-page";
  // Body section
  final private String bodySectionSelector = "#cgvBody";
  // Retrieve "On This Page" accordion titles
  final private String accSection = "div[class^='accordion']";
  // OnThisPage object
  private OnThisPage otp;
  //Content body object
  private BodyOfPage body;
  private WebElement accordion;
  /**
   * Constructor
   *
   * @param path server-relative path of the page to load.
   */
  public PageWithOnThisPage(String path) {
    super(path);
    this.otpSection = ElementHelper.findElement(this.getBrowser(), otpSectionSelector);
    this.bodySection = ElementHelper.findElement(this.getBrowser(), bodySectionSelector);
    if (otpSection != null) {
      otp = new OnThisPage(otpSection);
    }
    if (bodySection != null) {
      body = new BodyOfPage(bodySection);
    }
  }
  /**
   * Retrieve "On This Page" section
   */
  public OnThisPage getOnThisPage() {
    return otp;
  }
  /**
   * Retrieve Body section
   */
  public BodyOfPage getBodySection() {
    return body;
  }
  /**
   * * Returns true if the page has a On This Page section, false otherwise.
   */
  public boolean isOnThisPageSectionVisible() {
    return otpSection != null && otpSection.isDisplayed();
  }
  /**
   * Checks if accordion is displayed on mobile breakpoint
   */
  public boolean isAccordionDisplayed() {
     accordion = ElementHelper.findElement(this.bodySection, accSection);
    if (accordion != null)
      return accordion.isDisplayed();
    else
      return false;
  }

  /**
   * Checks if accordion class has role attribute
   */
  public boolean isAccordionTagPresent() {
        if (accordion.getAttribute("role")!=null && accordion.getAttribute("role").contentEquals("tablist"))
      return true;
    else
      return false;
  }
}

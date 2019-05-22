package gov.cancer.pageobject.section;

import java.util.ArrayList;
import java.util.List;
import gov.cancer.pageobject.helper.Link;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;

public class OnThisPage {
  // "On This Page" section
  private WebElement otpSection;
  // private WebElement otpAnchors;
  // Retrieve "On This Page" links
  final public String otpLinks = ":scope > ul > li >a";
  // Retrieve "On This Page" section header
  final public String otpSectionHeader = ":scope h6";
  // all anchor links
  private List<Link> anchorLinkList = new ArrayList<>();

  /**
   *
   * Constructor
   *
   * @param element
   *          WebElement containing "On This Page" section and anchor links.
   */
  public OnThisPage(WebElement element) {
    this.otpSection = element;
    
    List<WebElement> rawLinks = ElementHelper.findElements(this.otpSection, otpLinks);
    for (WebElement links : rawLinks) {
      anchorLinkList.add(new Link(links));
    }
  }

  /**
   * Retrieve the Header of "On This Page" section on the page
   *
   * @return String Header of On This Page.
   *
   */
  public String getOnThisPagesSectionHeaderText() {
    return ElementHelper.findElement(otpSection, otpSectionHeader).getText();
  }

  /**
   *
   * Retrieve the list of "On This Page" anchor links on the page.
   */
  public List<Link> getOnThisPageAnchorLinks() {
    return anchorLinkList;
  }
}

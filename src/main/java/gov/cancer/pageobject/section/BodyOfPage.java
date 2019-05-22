package gov.cancer.pageobject.section;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
/**
 * This object represents the entire body of the content.
 * Used to retrieve all headers of the different page sections
 */
public class BodyOfPage {
  private WebElement bodySection;
  // Retrieves Body Headers
  public String bodyHeaders = ".accordion section h2";
  /**
   * Constructor
   *
   * @param element WebElement containing Body section markup.
   */
  public BodyOfPage(WebElement element) {
    this.bodySection = element;
  }
  /**
   * Retrieve the String list of Body Headers
   */
  public List<String> getBodySectionHeadersAsString() {
    List<String> bodyHeaderListAsString = new ArrayList<>();
    List<WebElement> headers = ElementHelper.findElements(this.bodySection, bodyHeaders);
    for (WebElement list : headers) {
      bodyHeaderListAsString.add(list.getText());
    }
    return bodyHeaderListAsString;
  }
}
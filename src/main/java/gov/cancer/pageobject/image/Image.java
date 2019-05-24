package gov.cancer.pageobject.image;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;

public class Image {

  // The element containg the image.
  WebElement element;

  /**
   * Constructor
   *
   * @param element WebElement containing the markup for the image.
   */
  public Image(WebElement element) {
    this.element = element;

  }

  /**
   * Returns the image src attribute as a URL object.
   *
   * @return A URL instance containing the parsed link from the elements href
   *         attribute, or NULL if the href was missing, empty, or malformed.
   */
  public URL getUrl() {
    URL theUrl = null;

    String href = element.getAttribute("src");
    if (href != null && !href.trim().isEmpty()) {
      try {
        theUrl = new URL(href);
      } catch (MalformedURLException e) {
        theUrl = null;
      }
    }

    return theUrl;
  }


  /**
   * Retrieves the content of the image's <b>alt</b> attribute.
   *
   * @return String containing the images alt text, or NULL if the attribute is
   *         not present.
   */
  public String getAltText() {
    return element.getAttribute("alt");
  }

}

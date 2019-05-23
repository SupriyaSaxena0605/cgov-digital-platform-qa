package gov.cancer.pageobject.section;

import java.net.URL;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebElement;

import gov.cancer.pageobject.image.Image;

public class FeatureCard {

  public FeatureCard(WebElement we) {
    // TODO Auto-generated constructor stub
  }

  public Image getImage() {
    throw new NotImplementedException("TODO:implement getImage method");
  }

  public String getTitle() {
    throw new NotImplementedException("TODO:implement getTitle method");
  }

  public String getDescription() {
    throw new NotImplementedException("TODO:implement getDescription method");
  }

  public URL getLink() {
    throw new NotImplementedException("TODO:implement getURL method");
  }
}

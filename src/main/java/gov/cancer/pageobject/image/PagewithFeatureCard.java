package gov.cancer.pageobject.image;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.PageObjectBase;
import gov.cancer.pageobject.section.FeatureCard;

public class PagewithFeatureCard extends PageObjectBase {


  private final String FEATURE_CARD_SELECTOR = ".feature-primary .feature-card";

  public PagewithFeatureCard(String path) {
    super(path);
  }

  public List<FeatureCard> getFeatureCards() {

    List<FeatureCard> cardlist = new ArrayList<FeatureCard>();

    List<WebElement> cardelement = ElementHelper.findElements(getBrowser(), FEATURE_CARD_SELECTOR);
    for (WebElement we : cardelement) {
      cardlist.add(new FeatureCard(we));
    }
    return cardlist;

  }

}

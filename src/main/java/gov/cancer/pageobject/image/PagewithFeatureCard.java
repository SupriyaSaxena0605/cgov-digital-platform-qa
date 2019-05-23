package gov.cancer.pageobject.image;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import gov.cancer.framework.ElementHelper;

import gov.cancer.pageobject.section.FeatureCard;

public class PagewithFeatureCard {
  @FindBy(how = How.CSS, using = "#page")
  WebElement pageBody;

  WebElement element;

  WebElement image;

  final public String featurecard = "[class='large-6 columns topic-feature card gutter '].feature-card";

  public PagewithFeatureCard(String path) {
    super();
  }

  public List<FeatureCard> getFeatureCard() {

    List<FeatureCard> card = new ArrayList<FeatureCard>();

    List<WebElement> cardelement = ElementHelper.findElements(pageBody, featurecard);
    for (WebElement we : cardelement) {
      card.add(new FeatureCard(we));
    }
    return card;

  }

}

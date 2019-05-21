package gov.cancer.pageobject.image;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.RelatedResource;



public class PagewithFeatureCard {
  @FindBy(how = How.CSS, using = "#page")
  WebElement pageBody;

 WebElement element;
 
 WebElement image;

  //final public String featurecard = "[class='large-6 columns topic-feature card gutter ']:nth-of-type(1) .feature-card";
  final public String promoimage = "div[class='image-hover'] img";
  
  public PagewithFeatureCard(WebElement element) {
    this.element = element;
          }
  
  public WebElement getFeaturedImage(){
       image = element.findElement(By.cssSelector(promoimage));
    return image;
  }
  
    
  public List<PagewithFeatureCard> getFeaturedImageList(){
    
    List<PagewithFeatureCard> images = new ArrayList<PagewithFeatureCard>();

    List<WebElement> findimage = ElementHelper.findElements(element, promoimage);

    for (WebElement link : findimage) {
      images.add(new PagewithFeatureCard(link));
    }
    System.out.println(images);
    return images;
    
  }
  
  

}

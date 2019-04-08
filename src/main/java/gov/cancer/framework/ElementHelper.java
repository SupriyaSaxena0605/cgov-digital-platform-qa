package gov.cancer.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementHelper {

  public static WebElement findElement(SearchContext parent, String selector) {

    List<WebElement> findList = parent.findElements(By.cssSelector(selector));

    if (findList.size() == 1) {
      return findList.get(0);
    } else if (findList.size() > 1) {
      throw new RuntimeException("There should be only one Element");

    } else
      return null;

  }

  public static List<WebElement> findElements(SearchContext parent, String selector) {

    List<WebElement> findList = parent.findElements(By.cssSelector(selector));
    if (findList.size() > 0) {
      return findList;

    } else
      return null;

  }
}

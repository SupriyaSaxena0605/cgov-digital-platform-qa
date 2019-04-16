package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.LanguageToggle;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for Language Toggle.
 */
public class LanguageToggle_Test extends TestObjectBase {

  /**
   * Is the language toggle displayed?
   *
   * @param path
   *          Path of the page to check.
   * @param language
   *          language of the page.
   * @param translation
   *          translated path of the page.
   */
  @Test(dataProvider = "getLanguageToggle")
  public void isToggleVisible(String path, String language, String translation) {

    TestRunner.run(LanguageToggle.class, path, (LanguageToggle page) -> {

      Assert.assertTrue(page.isToggleVisible(), "Toggle is displayed.");

    });
  }

  /**
   * Is the Language Toggle is present as 'Espanol' on English pages and as
   * 'English' on Espanol pages?
   *
   * @param path
   *          Path of the page to check.
   * @param language
   *          language of the page.
   * @param translation
   *          translated path of the page.
   */

  @Test(dataProvider = "getLanguageToggle")
  public void isToggleLabelCorrect(String path, String language, String translation) {

    TestRunner.run(LanguageToggle.class, path, (LanguageToggle page) -> {
      String expected_label;
      if (language == "English" && translation != null) {
        expected_label = "Espanol";
        Assert.assertEquals(page.getTogglelabel(), expected_label, "Toggle label is correct.");
      } else if (language == "Espanol" && translation != null) {
        expected_label = "English";
        Assert.assertEquals(page.getTogglelabel(), expected_label, "Toggle label is correct.");
      }
    });
  }

  /**
   * Is the language toggle absent for pages having no translation pages?
   *
   * @param path
   *          Path of the page to check.
   * @param language
   *          language of the page.
   * @param translation
   *          translated path of the page.
   */
  @Test(dataProvider = "getLanguageToggleNoTranslation")
  public void isToggleNotVisible(String path, String language, String translation) {

    TestRunner.run(LanguageToggle.class, path, (LanguageToggle page) -> {
      Assert.assertTrue(page.isToggleVisible() && page.getTogglelabel().isEmpty(), "Toggle is not displayed.");

    });
  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/
  /**
   * Retrieves a list of paths, language and translation path.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getLanguageToggle")
  public Iterator<Object[]> getLanguageToggle() {
    String[] columns = { "path", "language", "translation" };
    return new ExcelDataReader(getDataFilePath("languagetoggle-data.xlsx"), "pages_with_translation", columns);
  }

  /**
   * Retrieves a list of paths and expected browser titles.
   *
   * @return An iterator over a collection of object arrays containing the
   *         requested data.
   */
  @DataProvider(name = "getLanguageToggleNoTranslation")
  public Iterator<Object[]> getLanguageToggleNoTranslation() {
    String[] columns = { "path", "language", "translation" };
    return new ExcelDataReader(getDataFilePath("languagetoggle-data.xlsx"), "pages_without_translation", columns);
  }

}

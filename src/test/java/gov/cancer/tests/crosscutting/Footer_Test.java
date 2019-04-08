package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.FooterPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with footer.
 */
public class Footer_Test extends TestObjectBase {

  /**
   * This method is checking if the Footer exists on the pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void footerIsVisible(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisible(), "Page Footer is visible.");

    });

  }

  /**
   * This method is checking if the header on the Footer exists and is correctly
   * displayed on the pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterHeader(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterHeaderVisible(), "Footer Header is visible.");
      Assert.assertTrue(page.getFooterHeader(), "Footer Header is displayed correct.");

    });

  }

  /**
   * This method is checking if Back to Top icon is displayed on the footer on
   * all pages and have correct link and label
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyBackToTop(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isbackToTopVisible(), "Back to top link is visible.");
      Assert.assertTrue(page.getbackToTop(), "Back to Top button is displayed correct.");
    });
  }

  /**
   * This method is checking if the length of text displayed on footer on the
   * pages is in considerable range
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterTextlengthCorrect(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.footerText().length() > 400 && page.footerText().length() < 800,
          "footer text is in range of 400 to 800 chrs");
    });

  }

  /**
   * This method is checking if the length of text displayed on footer on the
   * pages is in considerable range
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void isFooterRenderCorrect(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertFalse(page.footerText().contains("<a"), "footer text contains '<a' ");
    });

  }

  /**
   * This method is checking if footer is displayed only once on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterExistOnce(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisibleOnce(), "Footer is visible once.");

    });
  }

  /********* Espanol ***************************************************/

  /**
   * This method is checking if the Footer exists on the pages
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void footerIsVisibleEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisible(), "Page Footer is visible.");

    });

  }

  /**
   * This method is checking if the header on the Footer exists and is correctly
   * displayed on the pages
   * 
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterHeaderEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterHeaderVisible(), "Footer Header is visible.");
      Assert.assertTrue(page.getFooterHeaderEsp(), "Spanish Footer Header is displayed correct.");

    });

  }

  /**
   * This method is checking if the length of text displayed on footer on the
   * Spanish pages is in considerable range
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterTextlengthCorrectEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.footerText().length() > 400 && page.footerText().length() < 800,
          "footer text is in range of 400 to 800 chrs");
    });

  }

  /**
   * This method is checking if Back to Top icon is displayed on the footer on
   * all pages and have correct link and label
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyBackToTopEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isbackToTopVisible(), "Back to top link is visible.");
      Assert.assertTrue(page.getbackToTopEsp(), "Back to top icon is displayed correct.");

    });
  }

  /**
   * This method is checking if footer is displayed only once on all Spanish
   * pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterExistOnceEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterVisibleOnce(), "Footer is visible once.");

    });
  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages in English which are expected to have
   * Footer
   * 
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPageFooterPaths")
  public Iterator<Object[]> getPageFooterPaths() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("footer-data.xlsx"), "pages_with_footer", columns);

  }

  /**
   * Retrieves a list of paths to pages in Spanish which are expected to have
   * Footer
   * 
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getPageFooterPathsEsp")
  public Iterator<Object[]> getPageFooterPathsEsp() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("footer-data.xlsx"), "pages_with_footer_spanish", columns);

  }

}

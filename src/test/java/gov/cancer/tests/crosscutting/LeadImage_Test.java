package gov.cancer.tests.crosscutting;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.FooterPage;
import gov.cancer.pageobject.crosscutting.LeadImage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

/**
 * Tests for pages with lead images.
 */
public class LeadImage_Test extends TestObjectBase {

  /**
   * This method is checking if the Footer exists on the pages
   * 
   * @param path
   *          Path of the page to check.
   * @param language
   *          Language of the page to check.
   */
   @Test(dataProvider = "getPageWithLeadImage")
  public void isLeadImageVisible(String path, String caption, String credit, String alt) {

    TestRunner.run(LeadImage.class, path, (LeadImage page) -> {

      Assert.assertTrue(page.isLeadImageVisible(), "Lead Image is visible.");

    });

  }
   
   @Test(dataProvider = "getPageWithoutLeadImage")
   public void verifyLeadImagenotVisible(String path) {

     TestRunner.run(LeadImage.class, path, (LeadImage page) -> {

       Assert.assertFalse(page.isLeadImageVisible(), "Lead Image is not visible.");

     });

   }


   @Test(dataProvider = "getPageWithLeadImage_Caption")
  public void verifyImageCaption(String path, String caption) {

    TestRunner.run(LeadImage.class, path, (LeadImage page) -> {

      Assert.assertTrue(page.getCaption().equals(caption), "Lead Image caption is correct");

    });

  }

 @Test(dataProvider = "getPageWithLeadImage_Credit")
  public void verifyImageCredit(String path, String credit) {

    TestRunner.run(LeadImage.class, path, (LeadImage page) -> {

      Assert.assertTrue(page.getCredit().matches(credit), "Lead Image credit is correct");

    });

  }

  @Test(dataProvider = "getPageWithLeadImage_Alt")
  public void verifyImageAltText(String path, String alt) {

    TestRunner.run(LeadImage.class, path, (LeadImage page) -> {
      Assert.assertTrue(page.getAltText().equals(alt), "Lead Image alt is correct");
      ;

    });

  }

  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have Lead Image
   * 
   * @return An iterable list of two element arrays, each containing a path and
   *         language.
   */
  @DataProvider(name = "getPageWithLeadImage")
  public Iterator<Object[]> getPageWithLeadImage() {
    String[] columns = { "path", "Caption", "Credit", "Alt text" };
    return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_leadimage", columns);

  }

  @DataProvider(name = "getPageWithLeadImage_Caption")
  public Iterator<Object[]> getPageWithLeadImage_Caption() {
    String[] columns = { "path", "Caption" };
    return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_leadimage_Caption", columns);

  }

  @DataProvider(name = "getPageWithLeadImage_Alt")
  public Iterator<Object[]> getPageWithLeadImage_Alt() {
    String[] columns = { "path", "Alt text" };
    return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_leadimage_Alt", columns);

  }

  @DataProvider(name = "getPageWithLeadImage_Credit")
  public Iterator<Object[]> getPageWithLeadImage_Credit() {
    String[] columns = { "path", "Credit" };
    return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_leadimage_Credit", columns);

  }
  @DataProvider(name = "getPageWithoutLeadImage")
  public Iterator<Object[]> getPageWithoutLeadImage() {
    String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_noleadimage", columns);

  }
}

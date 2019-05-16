package gov.cancer.tests.image;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.image.PromoImage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class PromoImage_Test extends TestObjectBase {

  /**
   * This method is checking if the Lead Image exists on the pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageWithPromoImage")
  public void verifyPromoImageVisible(String path, String srcPromo, String altPromo) {

    TestRunner.run(PromoImage.class, path, (PromoImage page) -> {

      Assert.assertTrue(page.isPromoImageVisible(), "Promo Image is visible.");

    });

  }

  /**
   * This method is checking if the Lead Image does not exist on the pages with
   * no lead image
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageWithPromoImage")
  public void verifyPromoImageSource(String path, String srcPromo, String altPromo) {

    TestRunner.run(PromoImage.class, path, (PromoImage page) -> {

      Assert.assertTrue(page.isPromoImageSrcCorrect(srcPromo), "Promo Image is correctly displayed.");

    });

  }

  /**
   * This method is checking if the correct alt text is displayed with Lead
   * Image
   * 
   * @param path
   *          Path of the page to check.
   * @param alt
   *          Alternative text of Image..
   */
  @Test(dataProvider = "getPageWithPromoImage")
  public void verifyPromoImageAltText(String path, String srcPromo, String altPromo) {

    TestRunner.run(PromoImage.class, path, (PromoImage page) -> {
      Assert.assertTrue(page.getAltText().equals(altPromo), "Lead Image alt is correct");

    });

  }

  /**
   * This method is checking if the Lead Image does not exist on the pages with
   * no lead image
   * 
   * @param path
   *          Path of the page to check.
   */
  //@Test(dataProvider = "getPageWithnoPromobutLeadImage")
  public void verifyPromoLeadImageSource(String path, String srcLead, String altLead) {

    TestRunner.run(PromoImage.class, path, (PromoImage page) -> {

      Assert.assertTrue(page.isPromoImageSrcCorrect(srcLead), "Promo Image is correctly displayed.");

    });

  }

  /**
   * This method is checking if the correct alt text is displayed with Lead
   * Image
   * 
   * @param path
   *          Path of the page to check.
   * @param alt
   *          Alternative text of Image..
   */
 // @Test(dataProvider = "getPageWithnoPromobutLeadImage")
  public void verifyPromoLeadImageAltText(String path, String srcLead, String altLead) {

    TestRunner.run(PromoImage.class, path, (PromoImage page) -> {
      Assert.assertTrue(page.getAltText().equals(altLead), "Lead Image alt is correct");

    });

  }


  /*******************************************
   * DATA PROVIDER
   *******************************************/

  /**
   * Retrieves a list of paths to pages which are expected to have Lead Image
   * 
   * @return An iterable list of 4 element arrays, each containing a path,
   *         caption, credit, and alt text.
   */
  @DataProvider(name = "getPageWithPromoImage")
  public Iterator<Object[]> getPageWithPromoImage() {
    String[] columns = { "path", "Promo Image Src", "Promo Alt text" };
    return new ExcelDataReader(getDataFilePath("promoimage-data.xlsx"), "pages_with_promoimage", columns);

  }

  /**
   * Retrieves a list of paths to pages which are expected to have Lead Image
   * with Caption
   * 
   * @return An iterable list of two element arrays, each containing a path and
   *         caption.
   */
  @DataProvider(name = "getPageWithnoPromobutLeadImage")
  public Iterator<Object[]> getPageWithnoPromobutLeadImage() {
    String[] columns = { "path","Lead Image Src", "Lead Alt text" };
    return new ExcelDataReader(getDataFilePath("promoimage-data.xlsx"), "pages_with_lead_nopromoimage", columns);

  }

  }

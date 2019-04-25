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
 * Tests for pages with lead images.
 */
public class LeadImage_Test extends TestObjectBase{
  
  
    final public String Footer_header = "National Cancer Institute";
    final public String Footer_headerEsp = "Instituto Nacional del Cáncer";

    /**
     * This method is checking if the Footer exists on the pages
     * 
     * @param path
     *          Path of the page to check.
     * @param language
     *          Language of the page to check.
     */
    @Test(dataProvider = "getPageFooterPaths")
    public void footerIsVisible(String path, String caption, String credit, String alt) {

      TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

        Assert.assertTrue(page.isFooterVisible(), "Page Footer is visible.");

      });

    }
    
    /*******************************************
    * DATA PROVIDER
    *******************************************/

   /**
    * Retrieves a list of paths to pages which are expected to have Footer
    * 
    * @return An iterable list of two element arrays, each containing a path and
    *         language.
    */
   @DataProvider(name = "getPageWithLeadImage")
   public Iterator<Object[]> getPageWithLeadImage() {
     String[] columns = { "path", "Caption", "Credit","Alt text" };
     return new ExcelDataReader(getDataFilePath("leadimage-data.xlsx"), "pages_with_leadimage", columns);

   }

    
}

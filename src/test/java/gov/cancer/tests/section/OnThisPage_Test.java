package gov.cancer.tests.section;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import gov.cancer.pageobject.helper.Link;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.crosscutting.PageWithOnThisPage;
import gov.cancer.pageobject.section.BodyOfPage;
import gov.cancer.pageobject.section.OnThisPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
/**
 * Tests Cases: Verify On this Page
 *
 */
public class OnThisPage_Test extends TestObjectBase {
  /**
   * This method is checking all pages with "On this Page" section visibility
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getOnThisPagePaths")
  public void verifyOnThisPageIsVisible( String path) {
    TestRunner.run(PageWithOnThisPage.class, path, ( PageWithOnThisPage page) -> {
      Assert.assertTrue(page.isOnThisPageSectionVisible(), "On This Pages section is visible");
    });
  }
  /**
   * This method is checking all pages that do NOT display "On this Page"
   * section
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageWithoutOnThisPagePaths")
  public void verifyOnThisPageIsNotVisible( String path) {
    TestRunner.run(PageWithOnThisPage.class, path, ( PageWithOnThisPage page) -> {
      Assert.assertFalse(page.isOnThisPageSectionVisible(), "On This Pages section is visible");
    });
  }
  /**
   * This method compares "On This Page" Section header for correct translation
   *
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPagesWithSectionHeaderTranslations")
  public void verifyOnThisPageSectionHeaderText( String path,  String otpHeader) {
    TestRunner.run(PageWithOnThisPage.class, path, ( PageWithOnThisPage page) -> {
       OnThisPage otp = page.getOnThisPage();
      Assert.assertEquals(otp.getOnThisPagesSectionHeaderText(), otpHeader);
    });
  }
  /**
   *
   * This method is checking all of the "On This Page" Section links on the page
   * and verifying links Text/HREF is not blank and anchor tags are present
   *
   * @param path
   *          Path of the page to check.
   *
   */
  @Test(dataProvider = "getOnThisPagePathsWithCount")
  public void verifyOnThisPageAnchorLinks( String path,  int anchorcount ) {
    TestRunner.run(PageWithOnThisPage.class, path, (PageWithOnThisPage page) -> {
       OnThisPage otp = page.getOnThisPage();
       List<Link> anchorList = otp.getOnThisPageAnchorLinks();
      //Checking all anchorlinks are rendered before verifying individual links
      Assert.assertEquals(anchorList.size(), anchorcount);
      // For each anchor link execute the following assertions.
      for (int i=0; i<anchorList.size(); i++) {
        // Does it have non-blank text?
        Assert.assertFalse(anchorList.get(i).getText().isEmpty(), "Linktext is blank.");
        // Does it have a non-empty href?
        Assert.assertFalse(anchorList.get(i).getUrl().toString().isEmpty(), "HREF Is blank.");
        // Does it have a non-empty anchor tag?
        Assert.assertFalse(anchorList.get(i).getUrl().getRef().isEmpty(), "Anchor tag is not present");
      }
    });
  }
  /**
   *
   * This method is comparing "On This Page" anchor links to "On This Page" body
   * headers to verify text and size match
   *
   * @param path
   *          Path of the page to check.
   *
   */
  @Test(dataProvider = "getOnThisPagePathsWithCount")
  public void verifyAnchorAndHeaderMatch( String path,  int anchorcount) {
    TestRunner.run(PageWithOnThisPage.class, path, (PageWithOnThisPage page) -> {
      // Get the list of Body Headers
       BodyOfPage body = page.getBodySection();
       List<String> bodyHeaderList = body.getBodySectionHeadersAsString();
      // Get the list of OTP anchor list
       OnThisPage otp = page.getOnThisPage();
       List<Link> anchorList = otp.getOnThisPageAnchorLinks();
      //Checking all anchorlinks are rendered before verifying individual links
      Assert.assertEquals(anchorList.size(), anchorcount, "Anchor link count is not correct.");
      // asserting header list and anchor list size match
      Assert.assertEquals(bodyHeaderList.size(), anchorList.size(), "Header count and Anchor link count is not matching.");
      // looping through each header list and anchor list item text to find a
      // match
      for (int i = 0; i < bodyHeaderList.size(); i++) {
        Assert.assertEquals(bodyHeaderList.get(i), (anchorList.get(i).getText()), "Header and Anchor link title is not matching.");
      }
    });
  }
  /**
   *
   * This method is checking if accordion class have 'role' attribute on pages with "On This
   * Page" on Mobile
   *
   * @param path
   *          Path of the page to check.
   *
   */
  @Test(dataProvider = "getOnThisPagePaths")
  public void verifyAccordionOnMobile (String path) {
    TestRunner.runMobile(PageWithOnThisPage.class, path, (PageWithOnThisPage page) -> {
      Assert.assertTrue(page.isAccordionDisplayed(),"Accordion is not displayed");
      Assert.assertTrue(page.isAccordionTagPresent(),"Role attribute is not displayed");
    });
  }
  /**
   *
   * This method is checking if accordion class does not have 'role' attribute on pages with "On This
   * Page" on Tablet
   *
   * @param path
   *          Path of the page to check.
   *
   */
  @Test(dataProvider = "getOnThisPagePaths")
  public void verifyAccordionOnTablet (String path) {
    TestRunner.runTablet(PageWithOnThisPage.class, path, (PageWithOnThisPage page) -> {
      Assert.assertTrue(page.isAccordionDisplayed(),"Accordion is not displayed");
      Assert.assertFalse(page.isAccordionTagPresent(),"Role attribute is displayed");
    });
  }
  /**
   *
   * This method is checking if accordion class does not have 'role' attribute on pages with "On This
   * Page" on Desktop
   *
   * @param path
   *          Path of the page to check.
   *
   */
  @Test(dataProvider = "getOnThisPagePaths")
  public void verifyAccordionOnDesktop (String path) {
    TestRunner.runDesktop(PageWithOnThisPage.class, path, (PageWithOnThisPage page) -> {
      Assert.assertTrue(page.isAccordionDisplayed(),"Accordion is not displayed");
      Assert.assertFalse(page.isAccordionTagPresent(),"Role attribute is displayed");
    });
  }
  /*******************************************
   * DATA PROVIDER
   *******************************************/
  /**
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path.
   */
  @DataProvider(name = "getOnThisPagePaths")
  public Iterator<Object[]> getOnThisPagePaths() {
     String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("on-this-page-data.xlsx"), "pages_with_on_this_page", columns);
  }
  /**
  *
  * @return An iterable list of single element arrays, each containing a single
  *         path.
  */
 @DataProvider(name = "getOnThisPagePathsWithCount")
 public Iterator<Object[]> getOnThisPagePathsWithCount() {
    String[] columns = { "path", "anchorlinkscount" };
   //return new ExcelDataReader(getDataFilePath("on-this-page-data.xlsx"), "pages_with_on_this_page", columns);
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("on-this-page-data.xlsx"), "pages_with_on_this_page", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
   while (values.hasNext()) {
      Object[] item = values.next();
      String anchorlinkscount = (String) item[1];
      int anchorcount = Integer.parseInt(anchorlinkscount);
     converted.add(new Object[] { item[0], anchorcount});
   }
   return converted.iterator();

 }
  /**
   *
   * @return An iterable list of single element arrays, each containing a single
   *         path and Expected header text
   */
  @DataProvider(name = "getPagesWithSectionHeaderTranslations")
  public Iterator<Object[]> getPagesWithSectionHeaderTranslations() {
     String[] columns = { "path", "otpHeader" };
    return new ExcelDataReader(getDataFilePath("on-this-page-data.xlsx"), "pages_with_on_this_page", columns);
  }
  /**
   *
   *
   *
   * @return path Returns a list of paths for pages which Does Not expected to
   *         display "On This Page" Section
   */
  @DataProvider(name = "getPageWithoutOnThisPagePaths")
  public Iterator<Object[]> getPageWithoutOnThisPagePaths() {
     String[] columns = { "path" };
    return new ExcelDataReader(getDataFilePath("on-this-page-data.xlsx"), "pages_without_on_this_page", columns);
  }
}

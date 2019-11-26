package gov.cancer.tests.cts;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.pageobject.cts.BasicSearchPage;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;

public class BasicSearchForm_Test extends TestObjectBase {

  String path;

  /**
   * Check whether the header is correct
   *
   * @param path
   *          Path of the page to check.
   */
  @Test (dataProvider = "getCTSPages")
  public void verifyHeader(String path) {

    //path = "/about-cancer/treatment/clinical-trials/search";
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {

      Assert.assertTrue(page.ispageHeaderCorrect(path), "Page Header is not correct.");

    });

  }

  /**
   * Check whether the header is correct
   *
   * @param path
   *          Path of the page to check.
   */
  public void verifyIntroText(String path) {
    TestRunner.run(BasicSearchPage.class, path, (BasicSearchPage page) -> {

      Assert.assertTrue(page.ispageHeaderCorrect(path), "Page Header is not correct.");

    });

  }
  
  /**
   * Data provider retrieves paths to Basic and Advance Search paths, Search Results
   * @return
   */
  @DataProvider(name = "getCTSPages")
  public Iterator<Object[]> getCTSPages() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts-data.xlsx"), "basicsearch_form", columns);
  }

}

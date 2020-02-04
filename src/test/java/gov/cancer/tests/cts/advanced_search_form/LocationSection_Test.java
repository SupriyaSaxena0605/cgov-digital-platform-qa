package gov.cancer.tests.cts.advanced_search_form;

import gov.cancer.framework.ExcelDataReader;
import gov.cancer.framework.QueryParametersComparator;
import gov.cancer.pageobject.cts.AdvancedSearchPage;
import gov.cancer.pageobject.cts.SearchNavigationResult;
import gov.cancer.tests.TestObjectBase;
import gov.cancer.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * This test class contains all tests related to the Location section on the Advanced Search Form
 */
public class LocationSection_Test extends TestObjectBase {

  /**
   * Verify that Location Section is visible
   *
   * parameters
   *@param path url
   */
  @Test(dataProvider = "getLocation")
  public void verifyLocationSectionIsVisible(String path) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      Assert.assertTrue(page.getLocationSection().isVisible(), "Location Section is not visible");
    });
  }

  /**
   * Verify that correct no. of radio buttons are displayed when Veteran Affairs toggle is switched to Yes or No
   *
   *  parameters
   *@param path url
   * @param VAToggleTrue no. of radio buttons when Toggle is Yes
   * @param VAToggleFalse no. of radio buttons when Toggle is No
   */
  @Test(dataProvider = "getVAToggle")
  public void verifyVAToggleEffectOnRadioButtonCount(String path, int VAToggleTrue,int VAToggleFalse) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      //Veteran affair toggle switched to 'YES'
      page.getLocationSection().limitToVaOnly(true);
      //asserts the count of radio buttons displayed when VA toggle is Yes
      Assert.assertEquals(page.getLocationSection().getAllRadioButtons().size(), VAToggleTrue, "Radio buttons displayed are wrong");
      page.getLocationSection().limitToVaOnly(false);
      //asserts the count of radio buttons displayed when VA toggle is No
      Assert.assertEquals(page.getLocationSection().getAllRadioButtons().size(), VAToggleFalse, "Radio buttons displayed are wrong");
    });
  }

  /**
   * Verify that when Veteran Affairs toggle is switched to Yes and Find Trials button is clicked,
   * correct results are displayed
   *
   *  parameters
   *@param path url
   *@param queryParams query param for Advanced Search Results Page url
   */
  @Test(dataProvider = "getVAToggleTrue")
  public void verifyLocationVAToggleTrueParameter(String path, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {

      page.getLocationSection().scrollUntilToggleVisible();
      //Veteran affair toggle switched to 'YES'
      page.getLocationSection().limitToVaOnly(true);

      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for VA toggle set to true");

    });
  }


  /**
   * Verify that when Veteran Affairs toggle is switched to No and Find Trials button is clicked, correct results are
   * displayed
   *
   *  parameters
   *@param path url
   *@param queryParams list of query parameters
   */
  @Test(dataProvider = "getVAToggleFalse")
  public void verifyVAToggleFalseParameter(String path, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      //Veteran affair toggle switched to 'No' which is default state
      Assert.assertFalse(page.getLocationSection().getToggleState(),
        "Veteran affair toggle is in default state of 'NO");
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for VA toggle set to false");

    });
  }

  /**
   *  When the user switches the Veteran Affairs toggle first to 'YES' and then  to "NO" in the Location section and
   *  clicks Find Trials button, verify that system goes back to displaying default
   *  (not filtered by Veteran Affairs) results after search submitted
   *
   * @param path    url
   * @param queryParams query param for Advanced Search Results Page url
   *
   */
  @Test(dataProvider = "getVAToggleFalse")
  public void verifyToggleSwitchPositions(String path, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //scroll until the first location toggle is visible(to get rid of 'sticky block')
      page.getLocationSection().scrollUntilToggleVisible();
      //Veteran affair toggle switched to 'YES'
      page.getLocationSection().limitToVaOnly(true);

      //verify that Toggle is indeed has been switched to Yes
      Assert.assertTrue(page.getLocationSection().getToggleState(),
        "Veteran affair toggle is not switched to 'YES'");
      //now switch the toggle back to 'NO'
      page.getLocationSection().limitToVaOnly(false);
      //verify that Toggle is indeed has been switched back to No
      Assert.assertFalse(page.getLocationSection().getToggleState(),
        "Veteran affair toggle is not switched to 'NO");
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        "query param does not match for VA toggle set to false" );
    });
  }

  /**
   * Verify that the "Search All Locations" radio button is selected by default for the 'Location' section.
   *
   * @param path url
   * @param searchAllIndex    index of Radio button for Search All Locations
   * @param queryParams query param for Advanced Search Results Page url
   */
  @Test(dataProvider = "getSearchAllButton")
  public void verifyDefaultSearchAllLocation(String path, int searchAllIndex, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      // Verify that the "Search All Locations" radio button is selected
      Assert.assertTrue(page.getLocationSection().isSelected(searchAllIndex),
        " 'Search All Locations' radio button is not selected");
    });
  }

  /**
   * Verify correct search criteria is passed when 'Search All Locations' radio button is selected and user clicks
   * Find Trials button
   *
   * @param path    url
   * @param searchAllIndex    index of Radio button for Search All Locations
   * @param queryParam query param for Advanced Search Results Page url
   */
  @Test(dataProvider = "getSearchAllButton")
  public void verifySearchAllLocationParameter(String path, int searchAllIndex, String queryParam) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      //scroll until the first location toggle is visible(to get rid of 'sticky block')
      page.getLocationSection().scrollUntilToggleVisible();
      // Assert that the "Search All Locations" radio button is selected
      Assert.assertTrue(page.getLocationSection().isSelected(searchAllIndex),
        " 'Search All Locations' radio button is not selected");
      // create a navigation event redirect to search results page
      SearchNavigationResult searchResult = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(searchResult.getPageURL().getQuery());
      //Assert that url contains expected query params
      Assert.assertTrue(querycomp.compareQueryParams(queryParam),
        "query param does not match for Location" );
    });
  }


  /**
   * Verify that on clicking ZipCode radio button , U.S. ZIP Code and Radius fields are displayed
   *
   *  parameters
   * @param path url
   * @param zipIndex Index of ZipCode Radio button
   */
  @Test(dataProvider = "getZip")
  public void verifyZipRadioButton(String path, int zipIndex) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(zipIndex).select();
      //verify the U.S. ZIP Code text field is displayed when ZipCode radio button is selected
      Assert.assertTrue(page.getLocationSection().getZipCodeSubSection().getUSZipCodeField().isVisible(),
        "U.S. ZIP Code field is not displayed");
      //verify the Radius dropdown field is displayed when ZipCode radio button is selected
      Assert.assertTrue(page.getLocationSection().getZipCodeSubSection().isRadiusDropdownVisible(),
        "Radius dropdown field is not displayed");
    });
  }

  /**
   * Verify that when user enter zip code and select the radius from the 'Radius' drop-down and click Find Trials button,
   * correct parameters are passed.
   *
   *  parameters
   *
   * @param path   url
   * @param zip    zip code value
   * @param radius Radius value
   * @param zipIndex Index of ZipCode Radio button
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getZipRadius")
  public void verifyZipRadius(String path, String zip, String radius, int zipIndex, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(zipIndex).select();
      page.getLocationSection().getZipCodeSubSection().getUSZipCodeField().enterText(zip);
      page.getLocationSection().getZipCodeSubSection().selectRadius(radius);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for Zip code and Radius");
    });
  }

  /**
   * Verify that when user inputs invalid U.S. ZIP Code, following error message is displayed:
   * "Please enter a valid 5 digit U.S. zip code"
   * Assertion is performed by verifying the error message
   *
   * parameters
   * @param path    url
   * @param zipIndex Index of ZipCode Radio button
   * @param zipCode zipcode value
   * @param error error message
   */

  @Test(dataProvider = "getZipCodeInvalidEntries")
  public void verifyZipCodeError(String path, int zipIndex, String zipCode, String error) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(zipIndex).select();
      page.getLocationSection().getZipCodeSubSection().getUSZipCodeField().enterText(zipCode);
      page.getLocationSection().getZipCodeSubSection().getUSZipCodeField().setFocusAway();
      // assert that error message is displayed on the zipcode field
      Assert.assertTrue(page.getLocationSection().getZipCodeSubSection().getErrorDisplay().isVisible(),
        "error message is not displayed on U.S. Zip Code field");
      String res = page.getLocationSection().getZipCodeSubSection().getErrorDisplay().getMessage();

      // Assert error message matches
      Assert.assertEquals(res, error, "error message doesn't match");
    });
  }

  /**
   * Verify that the user is able to select the radius from the 'Radius' drop-down and correct parameters are passed on
   * clicking Find trials button
   *
   * parameters
   * @param path       url
   * @param radius     Radius value
   * @param radiusSize Size of Radius dropdown
   * @param zipIndex Index of ZipCode Radio button
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getRadius")
  public void verifyRadiusDropdown(String path, String radius, int radiusSize, int zipIndex,  String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(zipIndex).select();
      // assert that error message is displayed on the Radius field
      Assert.assertEquals(page.getLocationSection().getZipCodeSubSection().getRadiusOptions().size(), radiusSize,
        "Size of Radius dropdown is not correct");
      page.getLocationSection().getZipCodeSubSection().selectRadius(radius);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams)," query param does not match for Radius");

    });
  }

  /**
   * Verify that on selecting 'Country, State, City' radio button, the following are displayed:
   * Country
   * State
   * City
   *
   *  parameters
   *
   * @param path url
   * @param country Country value
   * @param countryIndex    Index of Country Radio button
   * @param queryParams     list of query parameters
   */
  @Test(dataProvider = "getCountry")
  public void verifyCountryStateCityRadioButton(String path, String country, int countryIndex, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      //verify the Country dropdown field is displayed when CountryStateCity radio button is selected
      Assert.assertTrue(page.getLocationSection().getCountryStateCitySubSection().isCountryDropdownVisible(),
        "Country dropdown field is not displayed");
      //verify the State field is displayed when CountryStateCity radio button is selected
      Assert.assertTrue(page.getLocationSection().getCountryStateCitySubSection().getState().isVisible(),
        "State field is not displayed");
      //verify the City field is displayed when CountryStateCity radio button is selected
      Assert.assertTrue(page.getLocationSection().getCountryStateCitySubSection().getCity().isVisible(),
        "State field is not displayed");
    });
  }

  /**
   * Verify that Country dropdown is correctly sorted and "United States" appears as default
   *
   * parameters
   *
   * @param path url
   * @param country Country value
   * @param countryIndex    Index of Country Radio button
   * @param queryParams     list of query parameters
   */
  @Test(dataProvider = "getCountry")
  public void verifyCountryDropdown(String path, String country, int countryIndex, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      //verify the Country dropdown field is displayed when CountryStateCity radio button is selected
      Assert.assertTrue(page.getLocationSection().getCountryStateCitySubSection().isCountrySorted(),
        "Country dropdown field is not correctly sorted");
      //verify the State field is displayed when CountryStateCity radio button is selected
      Assert.assertEquals(page.getLocationSection().getCountryStateCitySubSection().getSelectedCountry(),
        "United States", "Default value of Country dropdown is United States");
    });
  }

  /**
   * Verify that when user selects Country from the 'Country' drop-down and click Find Trials button, correct parameters
   * are passed.
   *
   * parameters
   *
   * @param path    url
   * @param country Country value
   * @param countryIndex index of Country radio button
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getCountry")
  public void verifyCountryParameter(String path, String country, int countryIndex, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      page.getLocationSection().getCountryStateCitySubSection().selectCountry(country);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams)," query param does not match for Country");
    });
  }

  /**
   * Verify that when user selects Country from the 'Country' drop-down, select State and click Find Trials button,
   * correct parameters are passed.
   * parameters
   *
   * @param path    url
   * @param country Country value
   * @param countryIndex index of Country radio button
   * @param states States value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getCountryState")
  public void verifyCountryStateParameter(String path, String country, int countryIndex, List<String> states, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      //select multiple states
      SearchNavigationResult search_result=inputStateValues(page, country,countryIndex, states);
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for Country and State");
          });
  }

  //this method is inputting values in all the fields
  public SearchNavigationResult inputStateValues(AdvancedSearchPage page, String country, int countryIndex, List<String> states){

      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      page.getLocationSection().getCountryStateCitySubSection().selectCountry(country);
      //using for loop to select multiple states
      for (String str : states)
      {page.getLocationSection().getCountryStateCitySubSection().getState().selectItem(str);}
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      return search_result;
      }
  /**
   * Verify that when user selects Country from the 'Country' drop-down, select State, enter City and click
   * Find Trials button, correct parameters are passed.
   * parameters
   *
   * @param path       url
   * @param country    Country value
   * @param countryIndex    Country index value
   * @param states     List of State value
   * @param city       City value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getCountryStateCity")
  public void verifyCountryStateCityParameter(String path, String country, int countryIndex, List<String> states, String city,
                                              String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      SearchNavigationResult search_result= inputStateCityValues(page, country, countryIndex, states, city);

      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for Country,State and City");
    });
  }

  //this method is inputting values in all the fields
  public SearchNavigationResult inputStateCityValues(AdvancedSearchPage page, String country, int countryIndex, List<String> states, String city){

    page.getLocationSection().scrollUntilToggleVisible();
    page.getLocationSection().getRadioButton(countryIndex).select();
    page.getLocationSection().getCountryStateCitySubSection().selectCountry(country);
    //using for loop to select multiple states
    for (String str : states)
    {page.getLocationSection().getCountryStateCitySubSection().getState().selectItem(str);}
    page.getLocationSection().getCountryStateCitySubSection().getCity().enterText(city);
    // create a navigation event redirect to search results page
    SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
    return search_result;
  }
  /**
   * Verify that when user selects Country from the 'Country' drop-down and enter City and click Find Trials button,
   * correct parameters are passed.
   *
   * parameters
   *
   * @param path    url
   * @param country Country value
   * @param countryIndex index of Country radio button
   * @param city    City value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getCountryCity")
  public void verifyCountryCityParameter(String path, String country,int countryIndex, String city, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      page.getLocationSection().getCountryStateCitySubSection().selectCountry(country);
      page.getLocationSection().getCountryStateCitySubSection().getCity().enterText(city);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for Country and City");
    });
  }

  /**
   * Verify that on clicking Hospitals/Institutions radio button, Hospitals/Institutions autosuggest field are displayed
   * parameters
   *
   * @param path     url
   * @param hospital Hospital value
   * @param hospitalIndex Hospital index value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getHospital")
  public void verifyHospitalRadioButton(String path, String hospital, int hospitalIndex, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(hospitalIndex).select();
      //verify the Hospitals/Institutions autosuggest field are displayed when Hospitals/Institutions
      // radio button is selected
      Assert.assertTrue(page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput().isVisible(),
        "Hospitals/Institutions autosuggest field is not displayed");
    });
  }

  /**
   * Verify that when user enters Hospital and click Find Trials button, correct parameters are passed.
   * parameters
   *
   * @param path     url
   * @param hospital Hospital value
   * @param hospitalIndex Hospital index value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getHospital")
  public void verifyHospitalParameter(String path, String hospital, int hospitalIndex, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(hospitalIndex).select();
      page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput().enterText(hospital);
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams)," query param does not match for Hospital");
    });
  }

  /**
   * Verify the Helper Text for Hospital field
   * <p>
   * parameters
   *
   * @param path                 url
   * @param hospitalIndex Hospital index value
   * @param helperText helper text for Hospital field
   */
  @Test(dataProvider = "getHospitalHelperText")
  public void verifyHospitalHelperText(String path, int hospitalIndex, String helperText) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(hospitalIndex).select();
      page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput().setFocus();
      //assert that message displayed is correct
      Assert.assertEquals(page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput()
        .getSectionFieldMessage(), helperText, "Correct message is not displayed for Enter characters");
    });
  }

  /**
   * Verify the No results found error message for Hospital field
   * <p>
   * parameters
   *
   * @param path                  url
   * @param hospitalIndex Hospital index value
   * @param hospital              hospital value
   * @param expectedHospitalError error message for No Results found for Hospital field
   */
  @Test(dataProvider = "getHospitalError")
  public void verifyHospitalError(String path, int hospitalIndex,String hospital, String expectedHospitalError) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(hospitalIndex).select();
      page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput().enterText(hospital);

      //assert that message displayed is correct
      Assert.assertEquals(page.getLocationSection().getHospitalsInstitutionSubSection().getHospitalInput()
        .getSectionFieldMessage(), expectedHospitalError, "Correct message is not displayed for Error message");
    });
  }

  /**
   * Verify that on clicking NIH radio button and click Find Trials button, correct parameters are passed.
   * parameters
   *
   * @param path url
   * @param nihIndex index of atNIH radio button
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getNIH")
  public void verifyNIHRadioButton(String path, int nihIndex, String queryParams) {
    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(nihIndex).select();
      // create a navigation event redirect to search results page
      SearchNavigationResult search_result = page.clickSubmit(page.getFormAction().getFindTrialsButton());
//create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams)," query param does not match for NIH");
    });
  }
  /************************
   * SUBMIT SEARCH WITH HITTING ENTER KEY
   ***********************/

  /**
   * Verify that when user selects Country from the 'Country' drop-down, selects the state and enter City value and
   * hits ENTER key, correct parameters are passed.
   * parameters
   *
   * @param path       url
   * @param country    Country value
   * @param countryIndex    Country index value
   * @param states     List of State value
   * @param city       City value
   * @param queryParams list of query parameters
   */
  @Test(dataProvider = "getCountryStateCity")
  public void verifyCountryStateCityParameterWithEnter(String path, String country, int countryIndex, List<String> states,
                                                       String city, String queryParams) {

    TestRunner.run(AdvancedSearchPage.class, path, (AdvancedSearchPage page) -> {
      page.getLocationSection().scrollUntilToggleVisible();
      page.getLocationSection().getRadioButton(countryIndex).select();
      SearchNavigationResult search_result= inputStateCityValuesForEnter(page, country, countryIndex, states, city);
      //create a comparator object from the redirected URL query part
      QueryParametersComparator querycomp = new QueryParametersComparator(search_result.getPageURL().getQuery());
      //Assert that url contains expected query
      Assert.assertTrue(querycomp.compareQueryParams(queryParams),
        " query param does not match for Country,State and City");
    });
  }
  //this method is inputting State values in the field
  public SearchNavigationResult inputStateCityValuesForEnter(AdvancedSearchPage page, String country, int countryIndex, List<String> states, String city){

    page.getLocationSection().scrollUntilToggleVisible();
    page.getLocationSection().getRadioButton(countryIndex).select();
    page.getLocationSection().getCountryStateCitySubSection().selectCountry(country);
    //using for loop to select multiple states
    for (String str : states)
    {page.getLocationSection().getCountryStateCitySubSection().getState().selectItem(str);}
    page.getLocationSection().getCountryStateCitySubSection().getCity().enterText(city);
    // create a navigation event redirect to search results page
    SearchNavigationResult search_result = page.hitEnterKey(page.getLocationSection().getCountryStateCitySubSection().getCity());
    return search_result;
  }

  /************************************************************
   * Data providers
   ************************************************************/
  /**
   * Data provider retrieves paths to Advanced Search page
   * params
   */

  @DataProvider(name = "getLocation")
  public Iterator<Object[]> getLocation() {
    String[] columns = {"path"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, parameter value when VA toggle is true
   * params
   */

  @DataProvider(name = "getVAToggle")
  public Iterator<Object[]> getVAToggle() {
    String[] columns = {"path","VAToggleTrue","VAToggleFalse"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String toggleTrue = (String) item[1];
      String toggleFalse = (String) item[2];
      int expectToggleTrue = Integer.parseInt(toggleTrue);
      int expectToggleFalse = Integer.parseInt(toggleFalse);
      converted.add(new Object[]{item[0], expectToggleTrue, expectToggleFalse});
    }
    return converted.iterator();
  }
  /**
   * Data provider retrieves paths to Advanced Search page, parameter value when VA toggle is true
   * params
   */

  @DataProvider(name = "getVAToggleTrue")
  public Iterator<Object[]> getVAToggleTrue() {
    String[] columns = {"path","ParamVAToggleTrue"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, parameter value when VA toggle is false
   * params
   */

  @DataProvider(name = "getVAToggleFalse")
  public Iterator<Object[]> getVAToggleFalse() {
    String[] columns = {"path","ParamVAToggleFalse"};
    return new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search", columns);
  }

  /**
   * Data provider retrieves paths to Advanced Search page, parameter value when VA toggle is false
   * params
   */

  @DataProvider(name = "getSearchAllButton")
  public Iterator<Object[]> getSearchAllButton() {
    String[] columns = {"path","SearchAllIndex","ParamVAToggleFalse"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String searchAll = (String) item[1];
      int searchAllIndex = Integer.parseInt(searchAll);
      converted.add(new Object[]{item[0], searchAllIndex, item[2]});
    }
    return converted.iterator();
  }
  /**
   * Data provider retrieves paths to Advanced Search page, ZipCode and Radius
   * params
   */

  @DataProvider(name = "getZip")
  public Iterator<Object[]> getZip() {
    String[] columns = {"path", "ZipCodeIndex"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[1];
      int radioIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0], radioIndex});
    }
    return converted.iterator();
  }
  /**
   * Data provider retrieves paths to Advanced Search page, ZipCode and Radius
   * params
   */

  @DataProvider(name = "getZipRadius")
  public Iterator<Object[]> getZipRadius() {
    String[] columns = {"path", "ZipCode", "Radius", "ZipCodeIndex", "ParamZipRadius"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[3];
      int zipIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0], item[1],item[2],zipIndex, item[4]});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Radius
   * params
   */

  @DataProvider(name = "getRadius")
  public Iterator<Object[]> getRadius() {
    String[] columns = {"path", "Radius", "RadiusSize", "ZipCodeIndex", "ParamRadius"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String radiusSize = (String) item[2];
      String index = (String) item[3];
      int zipIndex = Integer.parseInt(index);
      int expectedRadiusSize = Integer.parseInt(radiusSize);
      converted.add(new Object[]{item[0], item[1], expectedRadiusSize,zipIndex, item[4]});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, invalid values for Zip Code
   * error messages
   *
   * @return
   */

  @DataProvider(name = "getZipCodeInvalidEntries")
  public Iterator<Object[]> getZipCodeInvalidEntries() {
    String[] columns = {"path", "ZipCodeIndex","ZipCode", "Error"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_negative",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[1];
      int zipIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0],zipIndex, item[2], item[3] });
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Country value
   * params
   */

  @DataProvider(name = "getCountry")
  public Iterator<Object[]> getCountry() {
    String[] columns = {"path", "Country","Location", "ParamCountry"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_country",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[2];
      int countryIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0], item[1], countryIndex, item[3]});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Country value and State value
   * params
   */

  @DataProvider(name = "getCountryState")
  public Iterator<Object[]> getCountryState() {
    String[] columns = {"path","Country","Location", "State","ParamCountryState"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_country", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    List<String> states;
      while (data.hasNext()) {
      Object[] item = data.next();
        String index = (String) item[2];
        int countryIndex = Integer.parseInt(index);
      String rawStates = (String) item[3];
      states = Arrays.asList(rawStates.split(","));
      converted.add(new Object[]{item[0], item[1],countryIndex, states, item[4]});
    }
    return converted.iterator();

  }

  /**
   * Data provider retrieves paths to Advanced Search page, Country value, State value and City value
   * params
   */

  @DataProvider(name = "getCountryStateCity")
  public Iterator<Object[]> getCountryStateCity() {
    String[] columns = {"path","Country","Location", "State", "City", "ParamCountryStateCity"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_country", columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    List<String> states;

    while (data.hasNext()) {
      Object[] item = data.next();
      String index = (String) item[2];
      int countryIndex = Integer.parseInt(index);
      String rawStates = (String) item[3];
     states = Arrays.asList(rawStates.split(","));
      converted.add(new Object[]{item[0], item[1],countryIndex, states,item[4],item[5]});
    }
    return converted.iterator();

  }

  /**
   * Data provider retrieves paths to Advanced Search page, Country value and City value
   * params
   */

  @DataProvider(name = "getCountryCity")
  public Iterator<Object[]> getCountryCity() {
    String[] columns = {"path", "Country","Location", "City","ParamCountryCity"};
    Iterator<Object[]> data = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_cntrycity", columns);
    List<Object[]> converted = new ArrayList<Object[]>();

    while (data.hasNext()) {
      Object[] item = data.next();
      String index = (String) item[2];
      int countryIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0], item[1],countryIndex, item[3],item[4]});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, Hospital value
   * params
   */

  @DataProvider(name = "getHospital")
  public Iterator<Object[]> getHospital() {
    String[] columns = {"path", "Hospital","Location", "ParamHospital"};
      Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
        "advanced_search_hospital",
        columns);
      List<Object[]> converted = new ArrayList<Object[]>();
      while (values.hasNext()) {
        Object[] item = values.next();
        String index = (String) item[2];
        int hospitalIndex = Integer.parseInt(index);
        converted.add(new Object[]{item[0],item[1], hospitalIndex, item[3]});
      }
      return converted.iterator();


  }

  /**
   * Data provider retrieves paths to Advanced Search page, Hospital value and helper text
   * params
   */

  @DataProvider(name = "getHospitalHelperText")
  public Iterator<Object[]> getHospitalHelperText() {
    String[] columns = {"path","Location", "HospitalText"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_hospital",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[1];
      int hospitalIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0],hospitalIndex, item[2]});
    }
    return converted.iterator();
  }

  /**
   * Data provider retrieves paths to Advanced Search page, invalid values for Hospital and
   * error messages
   *
   * @return
   */

  @DataProvider(name = "getHospitalError")
  public Iterator<Object[]> getHospitalError() {
    String[] columns = {"path", "HospitalIndex","Hospital", "HospitalError"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_negative",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[1];
      int hospitalIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0],hospitalIndex, item[2], item[3] });
    }
    return converted.iterator();

  }

  /**
   * Data provider retrieves paths to Advanced Search page, Location value for NIH
   * params
   */

  @DataProvider(name = "getNIH")
  public Iterator<Object[]> getNIH() {
    String[] columns = {"path","Location","ParamNIH"};
    Iterator<Object[]> values = new ExcelDataReader(getDataFilePath("cts_advanced_search_location_data.xlsx"),
      "advanced_search_NIH",
      columns);
    List<Object[]> converted = new ArrayList<Object[]>();
    while (values.hasNext()) {
      Object[] item = values.next();
      String index = (String) item[1];
      int hospitalIndex = Integer.parseInt(index);
      converted.add(new Object[]{item[0],hospitalIndex, item[2] });
    }
    return converted.iterator();
       }
}

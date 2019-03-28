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
      Assert.assertTrue(page.getFooterHeader().getText().contains("National Cancer Institute"));

    });

  }

  /**
   * This method is checking if the FOLLOW US section exists on footer on the
   * pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterFollowUs(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterFollowUsVisible(), "FOLLOW US section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterFollowUs().getText().contains("FOLLOW US"));
      Assert.assertTrue(page.getFooterFB().isDisplayed());
      Assert
          .assertTrue(page.getFooterFB().getAttribute("href").contains("https://www.cancer.gov/social-media#facebook"));

      Assert.assertTrue(page.getFooterTwitter().isDisplayed());
      Assert.assertTrue(
          page.getFooterTwitter().getAttribute("href").contains("https://www.cancer.gov/social-media#twitter"));

      Assert.assertTrue(page.getFooterInstagram().isDisplayed());
      Assert.assertTrue(
          page.getFooterInstagram().getAttribute("href").contains("http://instagram.com/nationalcancerinstitute"));

      Assert.assertTrue(page.getFooterYoutube().isDisplayed());
      Assert.assertTrue(
          page.getFooterYoutube().getAttribute("href").contains("https://www.cancer.gov/social-media#youTube"));

      Assert.assertTrue(page.getFooterLinkedIn().isDisplayed());
      Assert.assertTrue(
          page.getFooterLinkedIn().getAttribute("href").contains("https://www.cancer.gov/social-media#linkedIn"));

      Assert.assertTrue(page.getFooterGovDelivery().isDisplayed());
      Assert.assertTrue(page.getFooterGovDelivery().getAttribute("href")
          .contains("https://public.govdelivery.com/accounts/USNIHNCI/subscriber/new"));

      Assert.assertTrue(page.getFooterRSS().isDisplayed());
      Assert.assertTrue(page.getFooterRSS().getAttribute("href").contains("https://www.cancer.gov/syndication/rss"));
    });

  }

  /**
   * This method is checking if the CONTACT INFORMATION section exists on footer
   * on the pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */

  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterContactInfo(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterContactInfoVisible(), "Contact Info section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterContactInfo().getText().contains("CONTACT INFORMATION"));
      Assert.assertTrue(page.getFooterContactUs().getText().contains("Contact Us"));
      Assert.assertTrue(page.getFooterContactUs().getAttribute("href").contains("https://www.cancer.gov/contact"));

      Assert.assertTrue(page.getFooterLiveHelp().getText().contains("LiveHelp Online Chat"));
      Assert.assertTrue(page.getFooterLiveHelp().getAttribute("href").contains("https://livehelp.cancer.gov"));
    });
  }

  /**
   * This method is checking if the MORE INFORMATION section exists on footer on
   * the pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterMoreInfo(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterMoreInfoVisible(), "More Info section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterMoreInfo().getText().contains("MORE INFORMATION"));
      Assert.assertTrue(page.getFooterAbout().getText().contains("About This Website"));
      Assert.assertTrue(page.getFooterAbout().getAttribute("href").contains("https://www.cancer.gov/about-website"));

      Assert.assertTrue(page.getFooterCgovEsp().getText().contains("Cancer.gov en español"));
      Assert.assertTrue(page.getFooterCgovEsp().getAttribute("href").contains("https://www.cancer.gov/espanol"));

      Assert.assertTrue(page.getFooterMultimedia().getText().contains("Multimedia"));
      Assert.assertTrue(page.getFooterMultimedia().getAttribute("href").contains("https://www.cancer.gov/multimedia"));

      Assert.assertTrue(page.getFooterPublication().getText().contains("Publications"));
      Assert
          .assertTrue(page.getFooterPublication().getAttribute("href").contains("https://www.cancer.gov/publications"));

      Assert.assertTrue(page.getFooterSitemap().getText().contains("Site Map"));
      Assert.assertTrue(
          page.getFooterSitemap().getAttribute("href").contains("https://www.cancer.gov/about-website/sitemap"));

      Assert.assertTrue(page.getFooterDigiStds().getText().contains("Digital Standards for NCI Websites"));
      Assert.assertTrue(
          page.getFooterDigiStds().getAttribute("href").contains("https://www.cancer.gov/digital-standards"));

    });
  }

  /**
   * This method is checking if the POLICIES section exists on footer on the
   * pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterPolicies(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterPoliciesVisible(), "Policies section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterPolicies().getText().contains("POLICIES"));
      Assert.assertTrue(page.getFooterAccessibility().getText().contains("Accessibility"));
      Assert.assertTrue(
          page.getFooterAccessibility().getAttribute("href").contains("https://www.cancer.gov/policies/accessibility"));

      Assert.assertTrue(page.getFooterComment().getText().contains("Comment Policy"));
      Assert.assertTrue(
          page.getFooterComment().getAttribute("href").contains("https://www.cancer.gov/policies/comments"));

      Assert.assertTrue(page.getFooterDisclaimer().getText().contains("Disclaimer"));
      Assert.assertTrue(
          page.getFooterDisclaimer().getAttribute("href").contains("https://www.cancer.gov/policies/disclaimer"));

      Assert.assertTrue(page.getFooterFOIA().getText().contains("FOIA"));
      Assert.assertTrue(page.getFooterFOIA().getAttribute("href").contains("https://www.cancer.gov/policies/foia"));

      Assert.assertTrue(page.getFooterPrivacy().getText().contains("Privacy & Security"));
      Assert.assertTrue(
          page.getFooterPrivacy().getAttribute("href").contains("https://www.cancer.gov/policies/privacy-security"));

      Assert.assertTrue(page.getFooterReuse().getText().contains("Reuse & Copyright"));
      Assert.assertTrue(
          page.getFooterReuse().getAttribute("href").contains("https://www.cancer.gov/policies/copyright-reuse"));

      Assert.assertTrue(page.getFooterSyndication().getText().contains("Syndication Services"));
      Assert
          .assertTrue(page.getFooterSyndication().getAttribute("href").contains("https://www.cancer.gov/syndication"));

      Assert.assertTrue(page.getFooterWebLinking().getText().contains("Website Linking"));
      Assert.assertTrue(
          page.getFooterWebLinking().getAttribute("href").contains("https://www.cancer.gov/policies/linking"));
    });
  }

  /**
   * This method is checking if correct links and labels are displayed for
   * agencies on the footer on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterAgencies(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.getFooterDHHS().getText().contains("U.S. Department of Health and Human Services"));
      Assert.assertTrue(page.getFooterDHHS().getAttribute("href").contains("http://www.hhs.gov/"));

      Assert.assertTrue(page.getFooterNIH().getText().contains("National Institutes of Health"));
      Assert.assertTrue(page.getFooterNIH().getAttribute("href").contains("http://www.nih.gov"));

      Assert.assertTrue(page.getFooterNCI().getText().contains("National Cancer Institute"));
      Assert.assertTrue(page.getFooterNCI().getAttribute("href").contains("https://www.cancer.gov/"));

      Assert.assertTrue(page.getFooterUSA().getText().contains("USA.gov"));
      Assert.assertTrue(page.getFooterUSA().getAttribute("href").contains("http://usa.gov"));
    });

  }

  /**
   * This method is checking if correct NCI tagline is displayed on the footer
   * on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPaths")
  public void verifyFooterTagline(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.getFooterTagline().getText().contains("NIH ... Turning Discovery Into Health"));

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

      Assert.assertTrue(page.getBackToTop().isDisplayed(), "Back to top link is visible.");
      Assert.assertTrue(page.getBackToTop().getText().contains("TOP"));
      Assert.assertTrue(page.getBackToTop().getAttribute("href").contains("#top"));
    });
  }

  /********* Espanol ***************************************************/

  /**
   * 
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
      Assert.assertTrue(page.getFooterHeader().getText().contains("Instituto Nacional del Cáncer"));

    });

  }

  /**
   * This method is checking if the FOLLOW US section exists on footer on the
   * pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterFollowUsEsp(String path) {

    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert.assertTrue(page.isFooterFollowUsVisible(), "FOLLOW US section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterFollowUs().getText().contains("SÍGANOS EN"));
      Assert.assertTrue(page.getFooterFB().isDisplayed());
      Assert.assertTrue(
          page.getFooterFB().getAttribute("href").contains("https://www.cancer.gov/espanol/redes-sociales"));

      Assert.assertTrue(page.getFooterTwitter().isDisplayed());
      Assert.assertTrue(
          page.getFooterTwitter().getAttribute("href").contains("https://www.cancer.gov/espanol/redes-sociales"));

      Assert.assertTrue(page.getFooterInstagram().isDisplayed());
      Assert.assertTrue(
          page.getFooterInstagram().getAttribute("href").contains("https://www.cancer.gov/social-media#instagram"));

      Assert.assertTrue(page.getFooterYoutube().isDisplayed());
      Assert.assertTrue(
          page.getFooterYoutube().getAttribute("href").contains("https://www.cancer.gov/espanol/redes-sociales"));

      Assert.assertTrue(page.getFooterLinkedIn().isDisplayed());
      Assert.assertTrue(
          page.getFooterLinkedIn().getAttribute("href").contains("https://www.cancer.gov/social-media#linkedIn"));

      Assert.assertTrue(page.getFooterGovDelivery().isDisplayed());
      Assert.assertTrue(page.getFooterGovDelivery().getAttribute("href")
          .contains("https://public.govdelivery.com/accounts/USNIHNCIESP/subscriber/new"));

      Assert.assertTrue(page.getFooterRSS().isDisplayed());
      Assert.assertTrue(
          page.getFooterRSS().getAttribute("href").contains("https://www.cancer.gov/espanol/sindicacion/rss"));
    });

  }

  /**
   * This method is checking if the CONTACT INFORMATION section exists on footer
   * on the pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterContactInfoEsp(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterContactInfoVisible(),
          "INFORMACIÓN DE CONTACTO section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterContactInfo().getText().contains("INFORMACIÓN DE CONTACTO"));

      Assert.assertTrue(page.getFooterContactUs().getText().contains("Comuníquese con nosotros"));
      Assert.assertTrue(
          page.getFooterContactUs().getAttribute("href").contains("https://www.cancer.gov/espanol/contactenos"));

      Assert.assertTrue(page.getFooterLiveHelp().getText().contains("LiveHelp chat en vivo"));
      Assert.assertTrue(page.getFooterLiveHelp().getAttribute("href").contains("https://livehelp-es.cancer.gov"));
    });
  }

  /**
   * This method is checking if the MORE INFORMATION section exists on footer on
   * the pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterMoreInfoEsp(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterMoreInfoVisible(), "MÁS INFORMACIÓN section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterMoreInfo().getText().contains("MÁS INFORMACIÓN"));
      Assert.assertTrue(page.getFooterAbout().getText().contains("Acerca de este sitio web"));
      Assert.assertTrue(
          page.getFooterAbout().getAttribute("href").contains("https://www.cancer.gov/espanol/acerca-sitio"));

      Assert.assertTrue(page.getFooterCgovEsp().getText().contains("Cancer.gov in English"));
      Assert.assertTrue(page.getFooterCgovEsp().getAttribute("href").contains("https://www.cancer.gov"));

      Assert.assertTrue(page.getFooterMultimedia().getText().contains("Multimedia"));
      Assert.assertTrue(
          page.getFooterMultimedia().getAttribute("href").contains("https://www.cancer.gov/espanol/multimedia"));

      Assert.assertTrue(page.getFooterPublication().getText().contains("Publicaciones"));
      Assert.assertTrue(page.getFooterPublication().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/publicaciones/educacion-para-pacientes"));

      Assert.assertTrue(page.getFooterSitemap().getText().contains("Mapa del sitio"));
      Assert.assertTrue(page.getFooterSitemap().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/acerca-sitio/mapa-sitio"));

    });
  }

  /**
   * This method is checking if the POLICIES section exists on footer on the
   * pages and correct links and labels are displayed
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterPoliciesEsp(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {
      Assert.assertTrue(page.isFooterPoliciesVisible(), "Policies section in the Footer is displayed.");
      Assert.assertTrue(page.getFooterPolicies().getText().contains("POLÍTICAS"));
      Assert.assertTrue(page.getFooterAccessibility().getText().contains("Accesibilidad"));
      Assert.assertTrue(page.getFooterAccessibility().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/politicas/accesibilidad"));

      Assert.assertTrue(page.getFooterComment().getText().contains("Política de comentarios"));
      Assert.assertTrue(page.getFooterComment().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/politicas/comentarios"));

      Assert.assertTrue(page.getFooterDisclaimer().getText().contains("Exoneración"));
      Assert.assertTrue(page.getFooterDisclaimer().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/politicas/exoneracion"));

      Assert.assertTrue(page.getFooterFOIA().getText().contains("Ley de libre acceso a la información (FOIA)"));
      Assert.assertTrue(
          page.getFooterFOIA().getAttribute("href").contains("https://www.cancer.gov/espanol/politicas/foia"));

      Assert.assertTrue(page.getFooterPrivacy().getText().contains("Política de confidencialidad y seguridad"));
      Assert.assertTrue(page.getFooterPrivacy().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/politicas/confidencialidad-seguridad"));

      Assert.assertTrue(page.getFooterReuse().getText().contains("Derechos de autor"));
      Assert.assertTrue(page.getFooterReuse().getAttribute("href")
          .contains("https://www.cancer.gov/espanol/politicas/derechos-de-autor-y-uso"));

      Assert.assertTrue(page.getFooterSyndication().getText().contains("Servicios de sindicación de contenidos"));
      Assert.assertTrue(
          page.getFooterSyndication().getAttribute("href").contains("https://www.cancer.gov/espanol/sindicacion"));

      Assert.assertTrue(page.getFooterWebLinking().getText().contains("Política de enlaces a sitios web"));
      Assert.assertTrue(
          page.getFooterWebLinking().getAttribute("href").contains("https://www.cancer.gov/espanol/politicas/enlaces"));
    });
  }

  /**
   * This method is checking if correct links and labels are displayed for
   * agencies on the footer on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterAgenciesEsp(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert
          .assertTrue(page.getFooterDHHS().getText().contains("Departamento de Salud y Servicios Humanos de EE. UU."));
      Assert.assertTrue(page.getFooterDHHS().getAttribute("href").contains("http://www.hhs.gov/"));

      Assert.assertTrue(page.getFooterNIH().getText().contains("Institutos Nacionales de la Salud"));
      Assert.assertTrue(page.getFooterNIH().getAttribute("href").contains("http://www.nih.gov"));

      Assert.assertTrue(page.getFooterNCI().getText().contains("Instituto Nacional del Cáncer"));
      Assert.assertTrue(page.getFooterNCI().getAttribute("href").contains("https://www.cancer.gov/espanol"));

      Assert.assertTrue(page.getFooterUSA().getText().contains("GobiernoUSA.gov"));
      Assert
          .assertTrue(page.getFooterUSA().getAttribute("href").contains("http://www.usa.gov/gobiernousa/index.shtml"));
    });

  }

  /**
   * This method is checking if correct NCI tagline is displayed on the footer
   * on all pages
   * 
   * @param path
   *          Path of the page to check.
   */
  @Test(dataProvider = "getPageFooterPathsEsp")
  public void verifyFooterTaglineEsp(String path) {
    TestRunner.run(FooterPage.class, path, (FooterPage page) -> {

      Assert
          .assertTrue(page.getFooterTagline().getText().contains("NIH ... Transformación de Descubrimientos en Salud"));

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

      Assert.assertTrue(page.getBackToTop().isDisplayed(), "Back to top link is visible.");
      Assert.assertTrue(page.getBackToTop().getText().contains("SUBIR"));
      Assert.assertTrue(page.getBackToTop().getAttribute("href").contains("#top"));

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

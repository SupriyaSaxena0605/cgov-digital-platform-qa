package gov.cancer.pageobject.crosscutting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import gov.cancer.pageobject.PageObjectBase;

/**
 * Pseudo page object representing any page in the system. The FooterPage class
 * is used solely for verifying attributes of a page's Footer section.
 */
public class FooterPage extends PageObjectBase {

  /*************** Page WebElements **********************/
  @FindBy(how = How.CSS, using = "#nvcgSlFooter")
  WebElement pageFooter;
  @FindBy(how = How.CSS, using = "div[class='site-footer__header']>h1")
  WebElement pageFooterHeader;

  /********* FOOTER FOLLOW US ICONS ***********************/
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>h2")
  WebElement pageFooterFollow;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(1)>a")
  WebElement pageFooterFB;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(2)>a")
  WebElement pageFooterTwitter;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(3)>a")
  WebElement pageFooterInstagram;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(4)>a")
  WebElement pageFooterYoutube;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(5)>a")
  WebElement pageFooterLinkedIn;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(6)>a")
  WebElement pageFooterGovDelivery;
  @FindBy(how = How.CSS, using = "div[class='site-footer__icons']>ul>li:nth-of-type(7)>a")
  WebElement pageFooterRSS;

  /********* FOOTER CONTACT INFO section ***********************/
  @FindBy(how = How.CSS, using = "div[class='site-footer__contact']>h2")
  WebElement pageFooterContactInfo;
  @FindBy(how = How.CSS, using = "div[class='site-footer__contact']>ul>li:nth-of-type(1)>a")
  WebElement pageFooterContactUs;
  @FindBy(how = How.CSS, using = "div[class='site-footer__contact']>ul>li:nth-of-type(2)>a")
  WebElement pageFooterLiveHelp;

  /********* FOOTER MORE INFO section ***********************/
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>h2")
  WebElement pageFooterMoreInfo;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(1)>a")
  WebElement pageFooterAboutWebsite;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(2)>a")
  WebElement pageFooterCgovEsp;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(3)>a")
  WebElement pageFooterMultimedia;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(4)>a")
  WebElement pageFooterPublications;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(5)>a")
  WebElement pageFooterSitemap;
  @FindBy(how = How.CSS, using = "div[class='site-footer__info']>ul>li:nth-of-type(6)>a")
  WebElement pageFooterDigiStds;

  /********* FOOTER POLICIES section ***********************/
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>h2")
  WebElement pageFooterPolicies;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(1)>a")
  WebElement pageFooterAccessibility;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(2)>a")
  WebElement pageFooterComment;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(3)>a")
  WebElement pageFooterDisclaimer;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(4)>a")
  WebElement pageFooterFOIA;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(5)>a")
  WebElement pageFooterPrivacy;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(6)>a")
  WebElement pageFooterReuse;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(7)>a")
  WebElement pageFooterSyndication;
  @FindBy(how = How.CSS, using = "div[class='site-footer__policies']>ul>li:nth-of-type(8)>a")
  WebElement pageFooterWebLinking;

  /********* FOOTER Agencies section ***********************/
  @FindBy(how = How.CSS, using = "div[class='site-footer__agencies']>ul>li:nth-of-type(1)>a")
  WebElement pageFooterDHHS;
  @FindBy(how = How.CSS, using = "div[class='site-footer__agencies']>ul>li:nth-of-type(2)>a")
  WebElement pageFooterNIH;
  @FindBy(how = How.CSS, using = "div[class='site-footer__agencies']>ul>li:nth-of-type(3)>a")
  WebElement pageFooterNCI;
  @FindBy(how = How.CSS, using = "div[class='site-footer__agencies']>ul>li:nth-of-type(4)>a")
  WebElement pageFooterUSAgov;

  @FindBy(how = How.CSS, using = "div[class='site-footer__tagline']>h4")
  WebElement pageFooterTagline;

  /********* FOOTER Back To Top icon ***********************/
  @FindBy(how = How.CSS, using = "a.back-to-top")
  WebElement backToTop;

  final public String Footer_header = "National Cancer Institute";

  /********* FOOTER Page Methods ***********************/

  /* Returns the Webelement Back to Top */
  public WebElement getBackToTop() {
    return backToTop;
  }

  /* Returns true if Back to Top is displayed */
  public boolean isbackToTopVisible() {
    return backToTop.isDisplayed();
  }

  /**
   * Constructor
   *
   * @param path
   *          server-relative path of the page to load.
   */
  public FooterPage(String path) {
    super(path);
  }

  /* Returns true if Footer is displayed on the page */
  public boolean isFooterVisible() {
    return pageFooter.isDisplayed();
  }

  /* Returns true if header of Footer is displayed */
  public boolean isFooterHeaderVisible() {
    WebElement footerheader = getBrowser().findElement(By.cssSelector("div[class='site-footer__header']>h1"));
    return footerheader.isDisplayed();
  }

  /* Returns the Webelement header of the Footer */
  public Boolean getFooterHeader() {
    Boolean header_match = false;
    WebElement footerheader = getBrowser().findElement(By.cssSelector("div[class='site-footer__header']>h1"));
    header_match = footerheader.getText().contains(Footer_header);
    return header_match;
  }

  /* Returns the Webelement header of the Footer */
  public void getFooterHeaderEsp() {
    /* implement this for Spanish header of Footer */
  }

  /* Returns true if FOLLOW US is displayed on the Footer */
  public boolean isFooterFollowUsVisible() {
    return pageFooterFollow.isDisplayed();
  }

  /* Returns Webelement Follow Us label */
  public WebElement getFooterFollowUs() {
    return pageFooterFollow;
  }

  /* Returns Webelement Facebook icon */
  public WebElement getFooterFB() {
    return pageFooterFB;
  }

  /* Returns Webelement Twitter icon */
  public WebElement getFooterTwitter() {
    return pageFooterTwitter;
  }

  /* Returns Webelement Instagram icon */
  public WebElement getFooterInstagram() {
    return pageFooterInstagram;
  }

  /* Returns Webelement Youtube icon */
  public WebElement getFooterYoutube() {
    return pageFooterYoutube;
  }

  /* Returns Webelement LinkedIn icon */
  public WebElement getFooterLinkedIn() {
    return pageFooterLinkedIn;
  }

  /* Returns Webelement GovDelivery icon */
  public WebElement getFooterGovDelivery() {
    return pageFooterGovDelivery;
  }

  /* Returns Webelement RSS icon */
  public WebElement getFooterRSS() {
    return pageFooterRSS;
  }

  /* Returns true if Contact Info section is displayed on the Footer */
  public boolean isFooterContactInfoVisible() {
    return pageFooterContactInfo.isDisplayed();
  }

  /* Returns Webelement Contact Info label */
  public WebElement getFooterContactInfo() {
    return pageFooterContactInfo;
  }

  /* Returns Webelement Contact US link */
  public WebElement getFooterContactUs() {
    return pageFooterContactUs;
  }

  /* Returns Webelement LiveHelp link */
  public WebElement getFooterLiveHelp() {
    return pageFooterLiveHelp;
  }

  /* Returns true if More Info section is displayed on the Footer */
  public boolean isFooterMoreInfoVisible() {
    return pageFooterMoreInfo.isDisplayed();
  }

  /* Returns Webelement More Info label */
  public WebElement getFooterMoreInfo() {
    return pageFooterMoreInfo;
  }

  /* Returns Webelement About Website link */
  public WebElement getFooterAbout() {
    return pageFooterAboutWebsite;
  }

  /* Returns Webelement Cancer.gov Espanol link */
  public WebElement getFooterCgovEsp() {
    return pageFooterCgovEsp;
  }

  /* Returns Webelement Multimedia link */
  public WebElement getFooterMultimedia() {
    return pageFooterMultimedia;
  }

  /* Returns Webelement Publications link */
  public WebElement getFooterPublication() {
    return pageFooterPublications;
  }

  /* Returns Webelement Sitemap link */
  public WebElement getFooterSitemap() {
    return pageFooterSitemap;
  }

  /* Returns Webelement Digital Standards for NCI Websites link */
  public WebElement getFooterDigiStds() {
    return pageFooterDigiStds;
  }

  /* Returns true if Policies section is displayed on the Footer */
  public boolean isFooterPoliciesVisible() {
    return pageFooterPolicies.isDisplayed();
  }

  /* Returns Webelement Policies label */
  public WebElement getFooterPolicies() {
    return pageFooterPolicies;
  }

  /* Returns Webelement Accessibility link */
  public WebElement getFooterAccessibility() {
    return pageFooterAccessibility;
  }

  /* Returns Webelement Comment Policy link */
  public WebElement getFooterComment() {
    return pageFooterComment;
  }

  /* Returns Webelement Disclaimer link */
  public WebElement getFooterDisclaimer() {
    return pageFooterDisclaimer;
  }

  /* Returns Webelement FOIA link */
  public WebElement getFooterFOIA() {
    return pageFooterFOIA;
  }

  /* Returns Webelement Privacy & Security link */
  public WebElement getFooterPrivacy() {
    return pageFooterPrivacy;
  }

  /* Returns Webelement Reuse & Copyright link */
  public WebElement getFooterReuse() {
    return pageFooterReuse;
  }

  /* Returns Webelement Syndication Services link */
  public WebElement getFooterSyndication() {
    return pageFooterSyndication;
  }

  /* Returns Webelement Website Linking link */
  public WebElement getFooterWebLinking() {
    return pageFooterWebLinking;
  }

  /* Returns Webelement DHHS link */
  public WebElement getFooterDHHS() {
    return pageFooterDHHS;
  }

  /* Returns Webelement NIH link */
  public WebElement getFooterNIH() {
    return pageFooterNIH;
  }

  /* Returns Webelement NCI link */
  public WebElement getFooterNCI() {
    return pageFooterNCI;
  }

  /** Returns Webelement USA.gov link **/
  public WebElement getFooterUSA() {
    return pageFooterUSAgov;
  }

  /* Returns Webelement Tagline */
  public WebElement getFooterTagline() {
    return pageFooterTagline;
  }
}

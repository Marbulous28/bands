import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void venueIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Venues"));
    fill("#name").with("Glastonburry");
    submit(".btn");
    assertThat(pageSource()).contains("Glastonburry");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Bands"));
    fill("#name").with("Arctic Monkeys");
    submit(".btn");
    assertThat(pageSource()).contains("Arctic Monkeys");
  }

  @Test
  public void venueShowPageDisplaysName() {
    Venue testVenue = new Venue("Glastonburry");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    assertThat(pageSource()).contains("Glastonburry");
  }

  @Test
  public void bandShowPageDisplaysName() {
    Band testBand = new Band("Arctic Monkeys");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("Arctic Monkeys");
  }

  @Test
  public void bandIsAddedToVenue() {
    Venue testVenue = new Venue("Glastonburry");
    testVenue.save();
    Band testBand = new Band("Arctic Monkeys");
    testBand.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    fillSelect("#band_id").withText("Arctic Monkeys");
    submit(".btn");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("Arctic Monkeys");
  }

  @Test
  public void venueIsAddedToBand() {
    Venue testVenue = new Venue("Glastonburry");
    testVenue.save();
    Band testBand = new Band("Arctic Monkeys");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fillSelect("#venue_id").withText("Glastonburry");
    submit(".btn");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("Glastonburry");
  }

}

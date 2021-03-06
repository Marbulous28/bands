import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiates_true() {
    Band newBand = new Band("Arctic Monkeys");
    assertEquals(true, newBand instanceof Band);
  }

  @Test public void getName_bandHasName_String() {
    Band newBand = new Band("Arctic Monkeys");
    assertEquals("Arctic Monkeys", newBand.getName());
  }

  @Test
  public void all_empty_0() {
    assertEquals(0, Band.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Band thisBand = new Band("Arctic Monkeys");
    Band thatBand = new Band("Arctic Monkeys");
    assertTrue(thisBand.equals(thatBand));
  }

  @Test
  public void save_savesBandIntoDatabase_true() {
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    assertTrue(Band.all().get(0).equals(newBand));
  }

  @Test
  public void find_findsBandInDatabase_true() {
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    Band savedBand = Band.find(newBand.getId());
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void update_updatesBandName_true() {
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    newBand.update("Radio Head");
    assertEquals("Radio Head", Band.find(newBand.getId()).getName());
  }

  @Test
  public void delete_deletesBand_true() {
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    int newBandId = newBand.getId();
    newBand.delete();
    assertEquals(null, Band.find(newBandId));
  }

  @Test
  public void addVenue_addsVenueToBand() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Band newBand = new Band("Arctic Monkey");
    newBand.save();
    newBand.addVenue(newVenue);
    Venue savedVenue = newBand.getVenues().get(0);
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    newBand.addVenue(newVenue);
    List savedVenues = newBand.getVenues();
    assertEquals(1, savedVenues.size());
  }

  @Test
  public void delete_deletesAllBandsAndVenuesJoins() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Band newBand = new Band("Arctic");
    newBand.save();
    newBand.addVenue(newVenue);
    newBand.delete();
    assertEquals(0, newVenue.getBands().size());
  }

}

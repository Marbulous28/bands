import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue newVenue = new Venue("Glastonburry");
    assertEquals(true, newVenue instanceof Venue);
  }

  @Test
  public void getName_categoryInstantiatesWithName_String() {
    Venue newVenue = new Venue("Glastonburry");
    assertEquals("Glastonburry", newVenue.getName());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Venue firstVenue = new Venue("Glastonburry");
    Venue secondVenue = new Venue("Glastonburry");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    assertTrue(Venue.all().get(0).equals(newVenue));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findVenueInDatabase_true() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Venue savedVenue = Venue.find(newVenue.getId());
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void addVenue_addsVenueToVenue_true() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    newVenue.addBand(newBand);
    Band savedBand = newVenue.getBands().get(0);
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Venue newVenue = new Venue("Glastonburry");
    newVenue.save();
    Band newBand = new Band("Arctic Monkeys");
    newBand.save();
    newVenue.addBand(newBand);
    List savedBands = newVenue.getBands();
    assertEquals(1, savedBands.size());
  }


}

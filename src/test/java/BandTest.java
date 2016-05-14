import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

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



}

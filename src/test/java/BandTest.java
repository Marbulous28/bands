import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BandTest {

  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiates_true() {
    Band newBand = new Band("Arctic Monkeys");
    assertEquals(true, newBand instanceof Band);
  }

  @Test public void getName_bandHasName_String() {
    Band newBand = new Band("Arctic Monkeys");
    assertEquals("Arctic Monkeys", newBand.getName());
  }

}

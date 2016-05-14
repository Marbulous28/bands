import java.util.List;
import org.sql2o.*;

public class Band {
  private int id;
  private String name;

  public Band(String name) {
   this.name = name;
  }

  public String getName() {
    return name;
  }


}

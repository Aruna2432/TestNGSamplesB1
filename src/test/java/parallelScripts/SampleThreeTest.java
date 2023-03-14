package parallelScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
  
  @Test
  public void testOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Test One from Sample Three"+id);
  }
  @Test
  public void testTwo() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Test Two from Sample Three"+id);
  }
  @Test
  public void testThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Test Three from Sample Three"+id);
  }
  @Test
  public void testFour() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Test Four from Sample Three"+id);
  }
}

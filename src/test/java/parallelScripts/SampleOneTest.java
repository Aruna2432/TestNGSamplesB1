package parallelScripts;

import org.testng.annotations.Test;

public class SampleOneTest {

  
	  @Test
	  public void testOne() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test One from Sample One"+ id);
	  }
	  @Test
	  public void testTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test Two from Sample One"+id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test Three from Sample One"+id);
	  }
	  @Test(threadPoolSize=4, invocationCount=8, timeOut=10000)
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Test Four from Sample One"+id);
	  }
	
  }


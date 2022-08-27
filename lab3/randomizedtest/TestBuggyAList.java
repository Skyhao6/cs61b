package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      /*int N = 500;
      for(int i = 0; i < N; i++) {
          int operateNumber = StdRandom.uniform(0,2);
          if(operateNumber == 0) {
              int randVal = StdRandom.uniform(2, 100);
              correct.addLast(randVal);
              broken.addLast(randVal);
          } else if (operateNumber == 1) {
              int csize = correct.size();
              int bsize = broken.size();
          }
      }*/
      correct.addLast(5);
      correct.addLast(10);
      correct.addLast(15);

      broken.addLast(5);
      broken.addLast(10);
      broken.addLast(15);

      assertEquals(correct.size(), broken.size());

      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
  }
  @Test
    public void randomizedTest(){
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();
      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              correct.addLast(randVal);
              broken.addLast(randVal);
              System.out.println("addLast(" + randVal + ")");
          } else if (operationNumber == 1) {
              // removeLast
              int csize = correct.size();
              int bsize = broken.size();
              if(csize == 0 || bsize == 0)
                  return;
              System.out.println("correct removeLast: " + correct.getLast());
              System.out.println("broken removeLast: " + broken.getLast());
              assertEquals(correct.removeLast(), broken.removeLast());
          } else if (operationNumber == 2) {

          }
      }
  }
}

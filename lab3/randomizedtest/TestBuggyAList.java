package randomizedtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
    /* adds the same value to both the correct and buggy AList implementations,
    * then checks that the results of three subsequent removeLast calls are the same
    * */
        // initiate correct AList
        AListNoResizing correctList = new AListNoResizing();
        // initiate buggy AList
        BuggyAList bugList = new BuggyAList();
        // addLast 4, 5, 6, respectively
        correctList.addLast(4); bugList.addLast(4);
        correctList.addLast(5); bugList.addLast(5);
        correctList.addLast(6); bugList.addLast(6);
        // removeLast 4, 5, 6, respectively
        // everytime using removeLast, verify the results whether equals
        assertEquals(correctList.size(), bugList.size());
        assertEquals(correctList.removeLast(), bugList.removeLast());
        assertEquals(correctList.removeLast(), bugList.removeLast());
        assertEquals(correctList.removeLast(), bugList.removeLast());
    }
}

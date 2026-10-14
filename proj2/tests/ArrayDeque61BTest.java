// import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

    // Test for size() method
    @Test
    public void sizeTest(){
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.size()).isEqualTo(0);

        adeque1.addFirst(4); adeque1.addFirst(3); adeque1.addFirst(2);
        adeque1.addLast(5); adeque1.addLast(6);
        assertThat(adeque1.size()).isEqualTo(5);
    }

    // Test for isEmpty() method.
    @Test
    public void isEmptyTest(){
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.isEmpty()).isTrue();

        adeque1.addFirst(4); adeque1.addFirst(3); adeque1.addFirst(2);
        adeque1.addLast(5); adeque1.addLast(6);
        assertThat(adeque1.isEmpty()).isFalse();
    }

    // Test for getFirst
    @Test
    public void getFirstTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.getFirst()).isNull(); // null

        adeque1.addLast(5);
        assertThat(adeque1.getFirst()).isEqualTo(5);
        adeque1.addFirst(4);
        assertThat(adeque1.getFirst()).isEqualTo(4);
    }
    // Test for getLast
    @Test
    public void getLastTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.getLast()).isNull(); // null

        adeque1.addLast(5);
        assertThat(adeque1.getLast()).isEqualTo(5);
        adeque1.addFirst(4);
        assertThat(adeque1.getLast()).isEqualTo(5);
    }

    // Test for size() method
    @Test
    public void getTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.get(1)).isNull();
        assertThat(adeque1.get(-100)).isNull();

        adeque1.addFirst(3); adeque1.addFirst(2); adeque1.addFirst(1);
        assertThat(adeque1.get(3)).isNull();
        assertThat(adeque1.get(1)).isEqualTo(2);
        assertThat(adeque1.get(-20)).isNull();
    }

    // Test for size() method
    // No need to implement getRecursive for ArrayDeque61B!
//    @Test
//    public void getRecursiveTest() {
//        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
//        assertThat(adeque1.getRecursive(1)).isNull();
//        assertThat(adeque1.getRecursive(-100)).isNull();
//
//        adeque1.addFirst(3); adeque1.addFirst(2); adeque1.addFirst(1);
//        assertThat(adeque1.getRecursive(3)).isNull();
//        assertThat(adeque1.getRecursive(1)).isEqualTo(2);
//        assertThat(adeque1.getRecursive(-20)).isNull();
//    }

    // Test for toList method
    @Test
    public void toListTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        List<Integer> adeque1toList = new ArrayList<>();
        assertThat(adeque1.toList()).isEqualTo(adeque1toList);

        adeque1.addFirst(3); adeque1.addFirst(2);
        adeque1toList.addFirst(3); adeque1toList.addFirst(2);
        assertThat(adeque1.toList()).isEqualTo(adeque1toList);

        adeque1.addLast(4); adeque1.addLast(5);
        adeque1toList.addLast(4); adeque1toList.addLast(5);
        assertThat(adeque1.toList()).isEqualTo(adeque1toList);
    }

    // Test for removeFirst method
    @Test
    public void removeFirstTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.removeFirst()).isNull();

        adeque1.addLast(4);
        assertThat(adeque1.removeFirst()).isEqualTo(4);
        assertThat(adeque1.removeFirst()).isNull();
    }

    // Test for removeLast method
    @Test
    public void removeLastTest() {
        Deque61B<Integer> adeque1 = new ArrayDeque61B<>();
        assertThat(adeque1.removeLast()).isNull();

        adeque1.addFirst(4);
        assertThat(adeque1.removeLast()).isEqualTo(4);
        assertThat(adeque1.removeLast()).isNull();
    }
}

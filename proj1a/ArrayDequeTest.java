import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void testEmpty() {
        ArrayDeque<Integer> example = new ArrayDeque<>();
        assertTrue(example.isEmpty());
        assertEquals(0, example.size());
        assertEquals(null, example.get(0));
        assertEquals(null, example.removeFirst());
        assertEquals(null, example.removeLast());
    }

    @Test
    public void testAdd() {
        ArrayDeque<Integer> example = new ArrayDeque<>();
        example.addFirst(0);
        example.addLast(1);
        assertFalse(example.isEmpty());
        assertEquals(2, example.size());

        int actual0th = example.get(0);
        int actual1st = example.get(1);
        assertEquals(0, actual0th);
        assertEquals(1, actual1st);
    }

    @Test
    public void testRemove(){
        ArrayDeque<Integer> example = new ArrayDeque<>();
        example.addFirst(1);
        example.addLast(2);
        assertEquals(2,example.size());

        example.removeFirst();
        assertEquals(1,example.size());
        assertEquals(2,example.get(front));
        assertEquals(2,example.removeLast());
        assertEquals(0,example.size());
    }


}
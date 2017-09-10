package examples;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;
import static org.hamcrest.Matchers.is;
import static org.junit.runners.Parameterized.Parameters;
import static org.junit.runners.Parameterized.Parameter;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LearningTest {
    //Parameterized
    @Parameters
    public static Iterable<Object[]> data() {
        return asList(new Object[][]{{-3, 3}, {-2, 2}, {-1, 1}, {0, 0}, {1, 1}, {2, 2}});
    }

    @Parameter(0)
    public int input;

    @Parameter(1)
    public int expectedResult;

    @Test
    public void test_abs() {
        assertThat(abs(input), is(expectedResult));
    }

    //examples
    private List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void test_array_has_only_two_X_written_by_hands() {
        list.addAll(asList("w", "t", "x", "x", "y"));

        int firstIndexX = list.indexOf("x");
        int secondIndexX = list.subList(firstIndexX + 1, list.size()).indexOf("x") + firstIndexX + 1;
        int thirdIndexX = list.subList(secondIndexX + 1, list.size()).indexOf("x");

        if ((firstIndexX >= 0) && (secondIndexX >= 0) && (thirdIndexX == -1)) {

        } else {
            throw new AssertionError("list hasn't only two x");
        }
    }

    @Test
    public void test_array_has_only_two_X_written_by_custom_Matcher() {
        list.addAll(asList("w", "t", "x", "o", "x"));
        assertThat(list, hasItemStrictCount("x", 2));
    }

    private static <T> Matcher<Collection<T>> hasItemStrictCount (T _elem, int _count) {
        return new BaseMatcher<Collection<T>>() {
            private int count = _count;
            private T elem = _elem;

            @Override
            public boolean matches(Object o) {
                Iterator<T> iter = ((Iterable<T>) o).iterator();
                if (count <= 0) {
                    throw new IllegalArgumentException();
                }
                while(iter.hasNext()) {
                    T toCompare = iter.next();
                    if (toCompare.equals(elem)) {
                        count--;
                    }
                }
                if (count == 0) return true;
                else return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("element '" + _elem.toString() + "' count " + _count + " times");
            }
        };
    }
}

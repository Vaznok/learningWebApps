package examples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.MockitoCore;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LearningMockito {
    List<String> list;

    @Before
    public void setUp() {
        this.list = mock(List.class);
    }

    @Test
    public void test_Mockito_listGet() {
        Mockito.when(list.get(Mockito.anyInt())).thenReturn("0");
        Mockito.when(list.get(Mockito.eq(5))).thenReturn("A");
        ArgumentMatcher<Integer> matcher = new ArgumentMatcher<Integer>() {
            @Override
            public boolean matches(Object arg) {
                return (arg instanceof Integer) && (Integer) arg % 3 == 0;
            }
        };
        Mockito.when(list.get(Mockito.intThat(matcher))).thenReturn("everyThird");
        Mockito.when(list.get(Mockito.eq(9))).thenThrow(new RuntimeException("Oh god!"));

        for (int i = 0; i < 10; i++) {
            System.out.println("list.get(" + i + ") return " + list.get(i));
        }
    }

    @Test
    public void test_Mockito_arrayList_addAll() {
        list.addAll(asList("A", "B", "C"));
        list.addAll(asList("A", "B", "C"));
        list.addAll(asList("A", "B", "C"));
        System.out.println(list);
    }

    @Test
    public void test_Mockito_mockTypes() {
        List listDefault = mock(ArrayList.class);
        System.out.println(listDefault.size());
        System.out.println(listDefault.isEmpty());
        System.out.println(listDefault.iterator());

        System.out.println();

        List listDeepStub = mock(ArrayList.class, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_MOCKS));

        System.out.println(listDeepStub.size());
        System.out.println(listDeepStub.isEmpty());
        System.out.println(listDeepStub.iterator());
        System.out.println(listDeepStub.iterator().hasNext());
        System.out.println(listDeepStub.iterator().next());
        //System.out.println(listDeepStub.subList(0, 10).get(0).toString());
    }

    @Test
    public void test_Mockito_verify() {
        list = mock(List.class);
        list.add("A");
        list.add("B");
        list.add("C");
        Mockito.verify(list).add("C");
    }

    @Test
    public void test_Mockito_verify_collection_addAll() {
        //create + programming
        ArrayList<String> list = new ArrayList<>();
        Collection<String> coll = mock(Collection.class);
        when(coll.toArray()).thenReturn(new String[]{"A", "B", "C"});
        when(coll.iterator()).thenReturn(asList("A", "B", "C").iterator());

        //usage
        list.addAll(coll);
        list.addAll(coll);
        list.addAll(coll);
        coll.iterator();
        coll.size();

        assertThat(list, equalTo(asList("A", "B", "C", "A", "B", "C", "A", "B", "C")));

        //ask?
        verify(coll, times(3)).toArray();
        verify(coll, times(1)).iterator();
        verify(coll, times(1)).size();
        verifyNoMoreInteractions(coll);
        verify(coll).iterator();


    }
}

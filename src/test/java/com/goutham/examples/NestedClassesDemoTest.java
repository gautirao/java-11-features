package com.goutham.examples;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NestedClassesDemoTest {
        // https://www.baeldung.com/java-nest-based-access-control#:~:text=One%20nest%20member%20(typically%20the,to%20identify%20its%20nest%20host.
        public void outerPublic() {
        }

    private void outerPrivate() {
    }

    class Inner {

        public void innerPublic() {
            outerPrivate();
        }
    }

    @Test
    public void testInnerClass(){
        boolean isNestMate = NestedClassesDemoTest.class.isNestmateOf(NestedClassesDemoTest.Inner.class);
        boolean nestHost = NestedClassesDemoTest.Inner.class.getNestHost() == NestedClassesDemoTest.class;

        assertTrue(isNestMate);
        assertTrue(nestHost);

        String nestedMembers = Arrays.stream(NestedClassesDemoTest.Inner.class.getNestMembers())
                .map(Class::getName)
                .collect(Collectors.joining(","));

        assertEquals("com.goutham.examples.NestedClassesDemoTest,com.goutham.examples.NestedClassesDemoTest$Inner",nestedMembers);
    }


}

package ru.myapp.test2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static ru.myapp.test2.Element.reverseIteratively;
import static ru.myapp.test2.Element.reverseRecursively;

public class ElementTest {

    private final Element A;
    private final Element B;
    private final Element C;
    private final Element D;

    public ElementTest() {
        A = new Element("A");
        B = new Element("B");
        C = new Element("C");
        D = new Element("D");

        A.setNext(B);
        B.setNext(C);
        C.setNext(D);
    }

    // --- Recursively ---

    @Test
    public void reverseRecursively_checkOrderFromStartPosition() {
        Element sut = reverseRecursively(A);

        assertEquals("DCBA", getStringRepresentation(sut));
    }

    @Test
    public void reverseRecursively_checkOrderFromMiddlePosition() {
        Element sut = reverseRecursively(C);

        assertEquals("DC", getStringRepresentation(sut));
    }

    @Test
    public void reverseRecursively_checkOrderFromLastPosition() {
        Element sut = reverseRecursively(D);

        assertEquals("D", getStringRepresentation(sut));
    }

    // --- Iteratively ---

    @Test
    public void reverseIteratively_checkOrderFromStartPosition() {
        Element sut = reverseIteratively(A);

        assertEquals("DCBA", getStringRepresentation(sut));
    }

    @Test
    public void reverseIteratively_checkOrderFromMiddlePosition() {
        Element sut = reverseIteratively(C);

        assertEquals("DC", getStringRepresentation(sut));
    }

    @Test
    public void reverseIteratively_checkOrderFromLastPosition() {
        Element sut = reverseIteratively(D);

        assertEquals("D", getStringRepresentation(sut));
    }

    private String getStringRepresentation(Element reversed) {
        StringBuilder strRepresentation = new StringBuilder(reversed.getValue());
        Element next = reversed.getNext();
        while (next != null) {
            strRepresentation.append(next.getValue());
            next = next.getNext();
        }
        return strRepresentation.toString();
    }
}
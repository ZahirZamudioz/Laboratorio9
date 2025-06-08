package complements;

public class ListLinked<E> {

    private Node<E> head;

    public ListLinked() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertLast(E valor) {
        Node<E> nuevo = new Node<>(valor);
        if (isEmpty()) {
            head = nuevo;
        } else {
            Node<E> actual = head;

            while (actual.getNext() != null) {
                actual = actual.getNext();
            }
            actual.setNext(nuevo);
        }
    }

    public void insertFirst(E valor) {
        Node<E> nuevo = new Node<>(valor);
        nuevo.setNext(head);
        head = nuevo;
    }

    public void removeNode(E valor) {
        if (isEmpty())
            return;
        if (head.getData().equals(valor)) {
            head = head.getNext();
            return;
        }
        Node<E> actual = head;
        while (actual.getNext() != null) {
            if (actual.getNext().getData().equals(valor)) {
                actual.setNext(actual.getNext().getNext());
                return;
            }
            actual = actual.getNext();
        }
    }

    public void search(E valor) {
        if (isEmpty())
            return;
        Node<E> actual = head;
        while (actual != null) {
            if (actual.getData().equals(valor)) {
                System.out.println(actual.getData());
            }
            actual = actual.getNext();
        }
    }

    public E getElement(E valor) {
        if (isEmpty())
            return null;
        Node<E> actual = head;
        while (actual != null) {
            if (actual.getData().equals(valor)) {
                return actual.getData();
            }
            actual = actual.getNext();
        }
        return null;
    }

    public boolean contains(E valor) {
        if (isEmpty())
            return false;

        Node<E> actual = head;
        while (actual != null) {
            if (actual.getData().equals(valor)) {
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }

    public ListLinked<E> getAllElements() {
        ListLinked<E> allElements = new ListLinked<E>();
        Node<E> actual = head;
        while (actual != null) {
            allElements.insertLast(actual.getData());
            actual = actual.getNext();
        }
        return allElements;
    }

    public void forEach(java.util.function.Consumer<E> action) {
        Node<E> actual = head;
        while (actual != null) {
            action.accept(actual.getData());
            actual = actual.getNext();
        }
    }

    public E getFirstElement() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }

    
}

package complements;

public class Queue<E> {
    private ListLinked<E> list;
    
    public Queue() {
        this.list = new ListLinked<E>();
    }
    
    //Agrega un elemento al final de la cola
    public void enqueue(E element) {
        list.insertLast(element);
    }
    
    //Remueve y retorna el primer elemento de la cola
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        // Obtener el primer elemento
        E first = getFirst();
        
        // Remover el primer elemento
        if (first != null) {
            list.removeNode(first);
        }
        
        return first;
    }
    
    //Retorna el primer elemento sin removerlo
    public E peek() {
        return getFirst();
    }
    
    //Verifica si la cola está vacía
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    //Método auxiliar para obtener el primer elemento
    private E getFirst() {
        // Esto requiere un método adicional en ListLinked
        // Por ahora implementaré una versión que funcione
        return list.getFirstElement();
    }
}

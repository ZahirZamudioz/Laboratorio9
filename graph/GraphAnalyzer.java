package graph;

import complements.ListLinked;
import java.util.ArrayList;

public class GraphAnalyzer<E> extends GraphLink<E> {

    // Calcula el grado de un nodo específico
    public int getNodeDegree(E data) {
        Vertex<E> vertex = getVertex(data);
        if (vertex == null) {
            System.out.println("El vértice " + data + " no existe en el grafo.");
            return -1;
        }
        // Contar aristas salientes
        int outDegree = countEdges(vertex.listAdj);
        
        // Contar aristas entrantes (para grafos no dirigidos, cada arista se cuenta una vez)
        int inDegree = countIncomingEdges(vertex);
        return outDegree + inDegree;
    }
    
    // Verifica si el grafo es de tipo camino (Px)
    public boolean isPath() {
        if (listVertex.isEmpty()) {
            return false;
        }
        
        int totalVertices = countVertices();
        if (totalVertices < 2) {
            return false;
        }
        
        // Un camino debe tener exactamente (n-1) aristas
        int totalEdges = countTotalEdges();
        if (totalEdges != totalVertices - 1) {
            return false;
        }
        
        // Debe tener exactamente 2 vértices de grado 1 y el resto de grado 2
        int[] degreeCounts = getDegreeCounts();
        return degreeCounts[1] == 2 && degreeCounts[2] == totalVertices - 2;
    }
    
    // Verifica si el grafo es de tipo ciclo (Cx)
    public boolean isCycle() {
        if (listVertex.isEmpty()) {
            return false;
        }
        
        int totalVertices = countVertices();
        if (totalVertices < 3) {
            return false;
        }
        
        // Un ciclo debe tener exactamente n aristas
        int totalEdges = countTotalEdges();
        if (totalEdges != totalVertices) {
            return false;
        }
        
        // Todos los vértices deben tener grado 2
        int[] degreeCounts = getDegreeCounts();
        return degreeCounts[2] == totalVertices && isConexo();
    }
    
    // Verifica si el grafo es de tipo rueda (Wx)
    public boolean isWheel() {
        if (listVertex.isEmpty()) {
            return false;
        }
        
        int totalVertices = countVertices();
        if (totalVertices < 4) {
            return false;
        }
        
        // Una rueda debe tener 2(n-1) aristas
        int totalEdges = countTotalEdges();
        if (totalEdges != 2 * (totalVertices - 1)) {
            return false;
        }
        
        // Debe tener exactamente 1 vértice de grado (n-1) y (n-1) vértices de grado 3
        int[] degreeCounts = getDegreeCounts();
        return degreeCounts[totalVertices - 1] == 1 && degreeCounts[3] == totalVertices - 1;
    }
    
    // Verifica si el grafo es completo (Kx)
    public boolean isComplete() {
        if (listVertex.isEmpty()) {
            return false;
        }
        
        int totalVertices = countVertices();
        if (totalVertices < 2) {
            return false;
        }
        
        // Un grafo completo debe tener n(n-1)/2 aristas
        int expectedEdges = totalVertices * (totalVertices - 1) / 2;
        int totalEdges = countTotalEdges();
        
        if (totalEdges != expectedEdges) {
            return false;
        }
        
        // Todos los vértices deben tener grado (n-1)
        int[] degreeCounts = getDegreeCounts();
        return degreeCounts[totalVertices - 1] == totalVertices;
    }
    
    // Identifica el tipo de grafo y lo imprime
    public void identifyGraphType() {
        if (listVertex.isEmpty()) {
            System.out.println("El grafo está vacío.");
            return;
        }
        
        int totalVertices = countVertices();
        System.out.println("\n=== Análisis del grafo ===");
        System.out.println("Número de vértices: " + totalVertices);
        System.out.println("Número de aristas: " + countTotalEdges());
        System.out.println("¿Es conexo?: " + (isConexo() ? "Sí" : "No"));
        
        // Mostrar grados de todos los nodos
        System.out.println("\nGrados de los nodos:");
        showAllNodeDegrees();
        
        // Identificar tipo de grafo
        System.out.println("\nTipo de grafo:");
        
        if (isComplete()) {
            System.out.println("Grafo Completo (K" + totalVertices + ")");
        } else if (isWheel()) {
            System.out.println("Grafo Rueda (W" + totalVertices + ")");
        } else if (isCycle()) {
            System.out.println("Grafo Ciclo (C" + totalVertices + ")");
        } else if (isPath()) {
            System.out.println("Grafo Camino (P" + totalVertices + ")");
        } else {
            System.out.println("Grafo de tipo no identificado o mixto");
        }
    }

    // Cuenta el número de aristas en una lista de adyacencia
    private int countEdges(ListLinked<Edge<E>> edgeList) {
        final int[] count = {0};
        edgeList.forEach(edge -> count[0]++);
        return count[0];
    }
    
    // Cuenta las aristas entrantes a un vértice
    private int countIncomingEdges(Vertex<E> targetVertex) {
        final int[] count = {0};
        
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            if (!vertex.equals(targetVertex)) {
                vertex.listAdj.getAllElements().forEach(edge -> {
                    if (edge.getDestination().equals(targetVertex)) {
                        count[0]++;
                    }
                });
            }
        });
        
        return count[0];
    }
    
    // Cuenta el total de vértices en el grafo
    private int countVertices() {
        final int[] count = {0};
        listVertex.forEach(vertex -> count[0]++);
        return count[0];
    }
    
    // Cuenta el total de aristas en el grafo (evitando duplicados)
    private int countTotalEdges() {
        final int[] count = {0};
        
        // Para grafo no dirigido, contamos cada arista una sola vez
        ArrayList<String> countedEdges = new ArrayList<>();
        
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            vertex.listAdj.getAllElements().forEach(edge -> {
                String edgeKey1 = vertex.getData().toString() + "-" + edge.getDestination().getData().toString();
                String edgeKey2 = edge.getDestination().getData().toString() + "-" + vertex.getData().toString();
                
                if (!countedEdges.contains(edgeKey1) && !countedEdges.contains(edgeKey2)) {
                    countedEdges.add(edgeKey1);
                    count[0]++;
                }
            });
        });
        
        return count[0];
    }
    
    // Obtiene un array con el conteo de vértices por grado
    private int[] getDegreeCounts() {
        int totalVertices = countVertices();
        int[] degreeCounts = new int[totalVertices]; // Máximo grado posible es n-1
        
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            int degree = getNodeDegreeInternal(vertex);
            if (degree < totalVertices) {
                degreeCounts[degree]++;
            }
        });
        
        return degreeCounts;
    }
    
    //Calcula el grado de un vértice internamente
    private int getNodeDegreeInternal(Vertex<E> vertex) {
        int outDegree = countEdges(vertex.listAdj);
        int inDegree = countIncomingEdges(vertex);
        return outDegree + inDegree;
    }
    
    //Muestra los grados de todos los nodos
    private void showAllNodeDegrees() {
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            int degree = getNodeDegreeInternal(vertex);
            System.out.println("  Nodo " + vertex.getData() + ": Grado " + degree);
        });
    }
    
    //Método sobrecargado de getVertex para uso interno
    private Vertex<E> getVertex(E data) {
        Vertex<E> tempVertex = new Vertex<E>(data);
        return listVertex.getElement(tempVertex);
    }
}
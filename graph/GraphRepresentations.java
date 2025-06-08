package graph;

import complements.ListLinked;
import java.util.ArrayList;
import java.util.Collections;

public class GraphRepresentations<E> extends GraphLink<E> {

    //Muestra la representación formal del grafo
    public void showFormalRepresentation() {        
        if (listVertex.isEmpty()) {
            System.out.println("G = (∅, ∅) - Grafo vacío");
            return;
        }
        
        // Obtener lista de vértices
        ArrayList<E> vertices = getVertexList();
        
        // Obtener lista de aristas (sin duplicados para grafo no dirigido)
        ArrayList<String> edges = getEdgeList();
        
        // Mostrar representación formal
        System.out.print("G = (V, A)\n");
        System.out.print("V = {");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i));
            if (i < vertices.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
        
        System.out.print("A = {");
        for (int i = 0; i < edges.size(); i++) {
            System.out.print(edges.get(i));
            if (i < edges.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
        
        System.out.println("Orden del grafo |V| = " + vertices.size());
        System.out.println("Tamaño del grafo |A| = " + edges.size());
    }
    
    // Muestra la lista de adyacencia para cada vértice
    public void showAdjacencyList() {
        System.out.println("\n=== LISTA DE ADYACENCIA ===");
        
        if (listVertex.isEmpty()) {
            System.out.println("No hay vértices en el grafo.");
            return;
        }
        
        // Obtener todos los vértices ordenados
        ArrayList<E> vertices = getVertexList();
        
        // Mostrar lista de adyacencia para cada vértice
        for (E vertexData : vertices) {
            Vertex<E> vertex = getVertex(vertexData);
            if (vertex != null) {
                System.out.print(vertexData + " → [");
                
                // Obtener vértices adyacentes
                ArrayList<E> adjacentVertices = getAdjacentVertices(vertex);
                
                for (int i = 0; i < adjacentVertices.size(); i++) {
                    System.out.print(adjacentVertices.get(i));
                    if (i < adjacentVertices.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }
    }
    
    // Muestra la matriz de adyacencia del grafo
    public void showAdjacencyMatrix() {
        System.out.println("\n=== Matriz de adyacencia ===");
        
        if (listVertex.isEmpty()) {
            System.out.println("No hay vértices en el grafo.");
            return;
        }
        
        ArrayList<E> vertices = getVertexList();
        int n = vertices.size();
        
        // Crear matriz de adyacencia
        int[][] matrix = new int[n][n];
        
        // Llenar la matriz
        for (int i = 0; i < n; i++) {
            E vertexData = vertices.get(i);
            Vertex<E> vertex = getVertex(vertexData);
            
            if (vertex != null) {
                // Obtener vértices adyacentes
                ArrayList<E> adjacentVertices = getAdjacentVertices(vertex);
                
                for (E adjacent : adjacentVertices) {
                    int j = vertices.indexOf(adjacent);
                    if (j != -1) {
                        matrix[i][j] = 1;
                    }
                }
            }
        }
        
        // Mostrar encabezados de columnas
        System.out.print("     ");
        for (E vertex : vertices) {
            System.out.printf("%4s", vertex.toString());
        }
        System.out.println();
        
        // Mostrar matriz con etiquetas de filas
        for (int i = 0; i < n; i++) {
            System.out.printf("%4s ", vertices.get(i).toString());
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        
        // Mostrar propiedades de la matriz
        System.out.println("\nPropiedades de la matriz:");
        System.out.println("- Dimensión: " + n + "x" + n);
        System.out.println("- Simétrica: " + (isSymmetric(matrix) ? "Sí" : "No") + 
                          " (característica de grafo no dirigido)");
        System.out.println("- Diagonal principal: " + (hasSelfLoops(matrix) ? "Tiene" : "No tiene") + 
                          " lazos (1s en la diagonal)");
    }
    
    // Muestra todas las representaciones del grafo
    public void showAllRepresentations() {
        System.out.println("Representaciones del grafo no dirigido");
        showFormalRepresentation();
        showAdjacencyList();
        showAdjacencyMatrix();
    }
    
    // Crea un grafo de ejemplo para demostración
    public void createSampleGraph() {
        // Limpiar grafo actual
        this.listVertex = new ListLinked<Vertex<E>>();
        
        // Nota: Este método debe ser llamado con tipo específico
        System.out.println("Para crear un grafo de ejemplo, use createSampleStringGraph() o createSampleIntegerGraph()");
    }
    
    // Obtiene una lista ordenada de todos los vértices
    private ArrayList<E> getVertexList() {
        ArrayList<E> vertices = new ArrayList<>();
        
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> vertices.add(vertex.getData()));
        
        // Ordenar para presentación consistente
        try {
            Collections.sort(vertices, (a, b) -> a.toString().compareTo(b.toString()));
        } catch (Exception e) {
            // Si no se puede ordenar, mantener orden original
        }
        
        return vertices;
    }
    
    // Obtiene una lista de todas las aristas (sin duplicados para grafo no dirigido)
    private ArrayList<String> getEdgeList() {
        ArrayList<String> edges = new ArrayList<>();
        ArrayList<String> processedEdges = new ArrayList<>();
        
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            E sourceData = vertex.getData();
            
            vertex.listAdj.getAllElements().forEach(edge -> {
                E destData = edge.getDestination().getData();
                
                // Para grafo no dirigido, evitar duplicados (A,B) y (B,A)
                String edge1 = "(" + sourceData + "," + destData + ")";
                String edge2 = "(" + destData + "," + sourceData + ")";
                
                if (!processedEdges.contains(edge1) && !processedEdges.contains(edge2)) {
                    // Ordenar vertices para consistencia en la representación
                    if (sourceData.toString().compareTo(destData.toString()) <= 0) {
                        edges.add(edge1);
                    } else {
                        edges.add(edge2);
                    }
                    processedEdges.add(edge1);
                    processedEdges.add(edge2);
                }
            });
        });
        
        // Ordenar aristas para presentación consistente
        Collections.sort(edges);
        return edges;
    }
    
    // Obtiene los vértices adyacentes a un vértice dado
    private ArrayList<E> getAdjacentVertices(Vertex<E> vertex) {
        ArrayList<E> adjacentVertices = new ArrayList<>();
        
        vertex.listAdj.getAllElements().forEach(edge -> {
            adjacentVertices.add(edge.getDestination().getData());
        });
        
        // Ordenar para presentación consistente
        try {
            Collections.sort(adjacentVertices, (a, b) -> a.toString().compareTo(b.toString()));
        } catch (Exception e) {
            // Si no se puede ordenar, mantener orden original
        }
        
        return adjacentVertices;
    }
    
    // Verifica si una matriz es simétrica
    private boolean isSymmetric(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Verifica si la matriz tiene lazos (1s en la diagonal principal)
    private boolean hasSelfLoops(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] == 1) {
                return true;
            }
        }
        return false;
    }
    
    // Método auxiliar para obtener un vértice por su dato
    private Vertex<E> getVertex(E data) {
        Vertex<E> tempVertex = new Vertex<E>(data);
        return listVertex.getElement(tempVertex);
    }
    
    // Método para obtener información estadística del grafo
    public void showGraphStatistics() {
        System.out.println("\n=== Estadísticas del grafo ===");
        
        ArrayList<E> vertices = getVertexList();
        ArrayList<String> edges = getEdgeList();
        
        System.out.println("Número de vértices (orden): " + vertices.size());
        System.out.println("Número de aristas (tamaño): " + edges.size());
        
        if (!vertices.isEmpty()) {
            // Calcular grado promedio
            int totalDegree = 0;
            int maxDegree = 0;
            int minDegree = Integer.MAX_VALUE;
            
            for (E vertexData : vertices) {
                Vertex<E> vertex = getVertex(vertexData);
                if (vertex != null) {
                    int degree = getAdjacentVertices(vertex).size();
                    totalDegree += degree;
                    maxDegree = Math.max(maxDegree, degree);
                    minDegree = Math.min(minDegree, degree);
                }
            }
            
            double avgDegree = (double) totalDegree / vertices.size();
            
            System.out.printf("Grado promedio: %.2f\n", avgDegree);
            System.out.println("Grado máximo: " + maxDegree);
            System.out.println("Grado mínimo: " + minDegree);
            System.out.println("Densidad: " + calculateDensity(vertices.size(), edges.size()));
        }
    }
    
    // Calcula la densidad del grafo
    private String calculateDensity(int vertices, int edges) {
        if (vertices <= 1) return "0%";
        
        int maxEdges = vertices * (vertices - 1) / 2;
        double density = (double) edges / maxEdges * 100;
        return String.format("%.2f%%", density);
    }
}
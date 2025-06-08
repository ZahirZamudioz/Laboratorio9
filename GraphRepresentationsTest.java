import graph.*;

public class GraphRepresentationsTest {
    public static void main(String[] args) {
        System.out.println("=== Representaciones de grafos no dirigidos ===\n");
        
        // Ejemplo 1: Grafo pequeño
        System.out.println("EJEMPLO 1: Grafo pequeño");
        testSmallGraph();
        
        // Ejemplo 2: Grafo ciclo
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EJEMPLO 2: Grafo Ciclo C4");
        testCycleGraph();
        
        // Ejemplo 3: Grafo completo
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EJEMPLO 3: Grafo Completo K4");
        testCompleteGraph();
        
        // Ejemplo 4: Grafo con números
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EJEMPLO 4: Grafo con vértices numéricos");
        testNumericGraph();
    }
    
    // Prueba con un grafo pequeño simple
    private static void testSmallGraph() {
        GraphRepresentations<String> graph = new GraphRepresentations<>();
        
        // Crear vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        
        // Crear aristas (bidireccionales para grafo no dirigido)
        graph.insertEdge("A", "B");
        graph.insertEdge("B", "A");
        graph.insertEdge("B", "C");
        graph.insertEdge("C", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("C", "A");
        
        // Mostrar todas las representaciones
        graph.showAllRepresentations();
        graph.showGraphStatistics();
    }
    
    // Prueba con un grafo ciclo C4
    private static void testCycleGraph() {
        GraphRepresentations<String> graph = new GraphRepresentations<>();
        
        // Crear vértices
        String[] vertices = {"A", "B", "C", "D"};
        for (String vertex : vertices) {
            graph.insertVertex(vertex);
        }
        
        // Crear ciclo (bidireccional)
        String[][] edges = {
            {"A", "B"}, {"B", "C"}, {"C", "D"}, {"D", "A"}
        };
        
        for (String[] edge : edges) {
            graph.insertEdge(edge[0], edge[1]);
            graph.insertEdge(edge[1], edge[0]);
        }
        
        // Mostrar representaciones
        graph.showAllRepresentations();
        graph.showGraphStatistics();
    }
    
    // Prueba con un grafo completo K4
    private static void testCompleteGraph() {
        GraphRepresentations<String> graph = new GraphRepresentations<>();
        
        // Crear vértices
        String[] vertices = {"A", "B", "C", "D"};
        for (String vertex : vertices) {
            graph.insertVertex(vertex);
        }
        
        // Conectar todos con todos (grafo completo)
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                graph.insertEdge(vertices[i], vertices[j]);
                graph.insertEdge(vertices[j], vertices[i]);
            }
        }
        
        // Mostrar representaciones
        graph.showAllRepresentations();
        graph.showGraphStatistics();
    }
    
    // Prueba con un grafo usando números como vértices
    private static void testNumericGraph() {
        GraphRepresentations<Integer> graph = new GraphRepresentations<>();
        
        // Crear vértices numéricos
        for (int i = 1; i <= 5; i++) {
            graph.insertVertex(i);
        }
        
        // Crear aristas específicas
        int[][] edges = {
            {1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}
        };
        
        for (int[] edge : edges) {
            graph.insertEdge(edge[0], edge[1]);
            graph.insertEdge(edge[1], edge[0]);
        }
        
        // Mostrar representaciones
        graph.showAllRepresentations();
        graph.showGraphStatistics();
    }
    
    // Método auxiliar para crear un grafo personalizado para pruebas
    public static GraphRepresentations<String> createCustomGraph() {
        GraphRepresentations<String> graph = new GraphRepresentations<>();
        
        System.out.println("Creando grafo personalizado...");
        
        // Vértices
        String[] vertices = {"X", "Y", "Z", "W"};
        for (String vertex : vertices) {
            graph.insertVertex(vertex);
        }
        
        // Aristas formando una estructura específica
        graph.insertEdge("X", "Y");
        graph.insertEdge("Y", "X");
        graph.insertEdge("Y", "Z");
        graph.insertEdge("Z", "Y");
        graph.insertEdge("Z", "W");
        graph.insertEdge("W", "Z");
        graph.insertEdge("X", "W");
        graph.insertEdge("W", "X");
        
        return graph;
    }
    
    // Demuestra cómo usar cada método individualmente
    public static void demonstrateIndividualMethods() {
        System.out.println("\n=== DEMOSTRACIÓN DE MÉTODOS INDIVIDUALES ===");
        
        GraphRepresentations<String> graph = createCustomGraph();
        
        System.out.println("\n1. Solo representación formal:");
        graph.showFormalRepresentation();
        
        System.out.println("\n2. Solo lista de adyacencia:");
        graph.showAdjacencyList();
        
        System.out.println("\n3. Solo matriz de adyacencia:");
        graph.showAdjacencyMatrix();
        
        System.out.println("\n4. Solo estadísticas:");
        graph.showGraphStatistics();
    }
    
    // Ejemplo de grafo vacío y casos especiales
    public static void testSpecialCases() {
        System.out.println("\n=== CASOS ESPECIALES ===");
        
        // Grafo vacío
        System.out.println("\n1. Grafo vacío:");
        GraphRepresentations<String> emptyGraph = new GraphRepresentations<>();
        emptyGraph.showAllRepresentations();
        
        // Grafo con un solo vértice
        System.out.println("\n2. Grafo con un solo vértice:");
        GraphRepresentations<String> singleVertex = new GraphRepresentations<>();
        singleVertex.insertVertex("Solo");
        singleVertex.showAllRepresentations();
        
        // Grafo con vértices aislados
        System.out.println("\n3. Grafo con vértices aislados:");
        GraphRepresentations<String> isolatedVertices = new GraphRepresentations<>();
        isolatedVertices.insertVertex("A");
        isolatedVertices.insertVertex("B");
        isolatedVertices.insertVertex("C");
        // No insertar aristas - vértices aislados
        isolatedVertices.showAllRepresentations();
    }
}
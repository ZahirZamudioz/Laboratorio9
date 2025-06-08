import graph.*;

public class GraphAnalyzerTest {
    public static void main(String[] args) {
        System.out.println("=== Demostración de identificación de tipos de grafos ===\n");
        
        // Crear diferentes tipos de grafos para demostrar
        demoCompleteGraph();
        demoPathGraph();
        demoCycleGraph();
        demoWheelGraph();
        demoMixedGraph();
    }
    
    // Demostración de Grafo Completo K4
    private static void demoCompleteGraph() {
        System.out.println(">>> Grafo completo K4 <<<");
        GraphAnalyzer<String> graph = new GraphAnalyzer<>();
        
        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        
        // Insertar todas las aristas posibles (grafo completo)
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("A", "D");
        graph.insertEdge("B", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        
        // Para grafo no dirigido, agregar aristas en ambas direcciones
        graph.insertEdge("B", "A");
        graph.insertEdge("C", "A");
        graph.insertEdge("D", "A");
        graph.insertEdge("C", "B");
        graph.insertEdge("D", "B");
        graph.insertEdge("D", "C");
        
        // Analizar el grafo
        graph.identifyGraphType();
        
        // Mostrar grado de un nodo específico
        System.out.println("Grado del nodo A: " + graph.getNodeDegree("A"));
        System.out.println("Grado del nodo B: " + graph.getNodeDegree("B"));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    // Demostración de Grafo Camino P5
    private static void demoPathGraph() {
        System.out.println(">>> Grafo camino P5 <<<");
        GraphAnalyzer<Integer> graph = new GraphAnalyzer<>();
        
        // Insertar vértices
        for (int i = 1; i <= 5; i++) {
            graph.insertVertex(i);
        }
        
        // Insertar aristas en secuencia (camino)
        for (int i = 1; i < 5; i++) {
            graph.insertEdge(i, i + 1);
            graph.insertEdge(i + 1, i); // Para grafo no dirigido
        }
        
        // Analizar el grafo
        graph.identifyGraphType();
        
        // Mostrar grados de nodos específicos
        System.out.println("Grado del nodo 1 (extremo): " + graph.getNodeDegree(1));
        System.out.println("Grado del nodo 3 (intermedio): " + graph.getNodeDegree(3));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    // Demostración de Grafo Ciclo C5
    private static void demoCycleGraph() {
        System.out.println(">>> Grafo ciclo C5 <<<");
        GraphAnalyzer<Character> graph = new GraphAnalyzer<>();
        
        // Insertar vértices
        char[] vertices = {'A', 'B', 'C', 'D', 'E'};
        for (char v : vertices) {
            graph.insertVertex(v);
        }
        
        // Insertar aristas formando un ciclo
        for (int i = 0; i < vertices.length; i++) {
            char current = vertices[i];
            char next = vertices[(i + 1) % vertices.length];
            graph.insertEdge(current, next);
            graph.insertEdge(next, current); // Para grafo no dirigido
        }
        
        // Analizar el grafo
        graph.identifyGraphType();
        
        // Mostrar grado de algunos nodos
        System.out.println("Grado del nodo A: " + graph.getNodeDegree('A'));
        System.out.println("Grado del nodo C: " + graph.getNodeDegree('C'));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    // Demostración de Grafo Rueda W5
    private static void demoWheelGraph() {
        System.out.println(">>> Grafo de rueda W5 <<<");
        GraphAnalyzer<String> graph = new GraphAnalyzer<>();
        
        // Insertar vértices (centro + perímetro)
        graph.insertVertex("Centro");
        graph.insertVertex("P1");
        graph.insertVertex("P2");
        graph.insertVertex("P3");
        graph.insertVertex("P4");
        
        // Insertar aristas del centro a todos los vértices del perímetro
        String[] perimeter = {"P1", "P2", "P3", "P4"};
        for (String p : perimeter) {
            graph.insertEdge("Centro", p);
            graph.insertEdge(p, "Centro");
        }
        
        // Insertar aristas del ciclo del perímetro
        for (int i = 0; i < perimeter.length; i++) {
            String current = perimeter[i];
            String next = perimeter[(i + 1) % perimeter.length];
            graph.insertEdge(current, next);
            graph.insertEdge(next, current);
        }
        
        // Analizar el grafo
        graph.identifyGraphType();
        
        // Mostrar grados
        System.out.println("Grado del nodo Centro: " + graph.getNodeDegree("Centro"));
        System.out.println("Grado del nodo P1: " + graph.getNodeDegree("P1"));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    // Demostración de Grafo Mixto (no identificado)
    private static void demoMixedGraph() {
        System.out.println(">>> Grafo mixto <<<");
        GraphAnalyzer<Integer> graph = new GraphAnalyzer<>();
        
        // Crear un grafo irregular
        for (int i = 1; i <= 6; i++) {
            graph.insertVertex(i);
        }
        
        // Aristas irregulares
        int[][] edges = {
            {1, 2}, {1, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}
        };
        
        for (int[] edge : edges) {
            graph.insertEdge(edge[0], edge[1]);
            graph.insertEdge(edge[1], edge[0]);
        }
        
        // Analizar el grafo
        graph.identifyGraphType();
        
        // Mostrar algunos grados
        System.out.println("Grado del nodo 1: " + graph.getNodeDegree(1));
        System.out.println("Grado del nodo 3: " + graph.getNodeDegree(3));
        System.out.println("Grado del nodo 4: " + graph.getNodeDegree(4));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
}
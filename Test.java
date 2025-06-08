
public class Test {
    public static void main(String[] args) {
        System.out.println("=== Pruebas simples de analisis de grafos ===\n");
        
        // Prueba 1: Grafo básico
        testBasicGraph();
        
        // Prueba 2: Grafo triangular
        testTriangleGraph();
        
        // Prueba 3: Grafo en cadena
        testChainGraph();
        
        // Prueba 4: Comparar grafos
        testCompareGraphs();
    }
    
    // Prueba básica con un grafo simple
    public static void testBasicGraph() {
        System.out.println("--- PRUEBA 1: Grafo Básico ---");
        
        try {
            GraphProperties<String> graph = new GraphProperties<>();
            
            // Crear grafo simple: A -> B -> C
            graph.insertVertex("A");
            graph.insertVertex("B");
            graph.insertVertex("C");
            
            graph.insertEdge("A", "B");
            graph.insertEdge("B", "C");
            
            System.out.println("Grafo creado: A -> B -> C");
            graph.printAnalysis();
            
            System.out.println("Información: " + graph.getGraphInfo());
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error en prueba básica: " + e.getMessage());
        }
    }
    
    // Prueba con un grafo triangular (ciclo)
    public static void testTriangleGraph() {
        System.out.println("--- PRUEBA 2: Grafo Triangular ---");
        
        try {
            GraphProperties<String> graph = new GraphProperties<>();
            
            // Crear triángulo: A -> B -> C -> A
            graph.insertVertex("A");
            graph.insertVertex("B");
            graph.insertVertex("C");
            
            graph.insertEdge("A", "B");
            graph.insertEdge("B", "C");
            graph.insertEdge("C", "A");
            
            System.out.println("Grafo creado: A -> B -> C -> A (triángulo)");
            graph.printAnalysis();
            
            // Probar grados individuales
            System.out.println("Grado de A: " + graph.getVertexDegree("A"));
            System.out.println("Grado de B: " + graph.getVertexDegree("B"));
            System.out.println("Grado de C: " + graph.getVertexDegree("C"));
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error en prueba triangular: " + e.getMessage());
        }
    }
    
    // Prueba con un grafo en cadena
    public static void testChainGraph() {
        System.out.println("--- PRUEBA 3: Grafo en Cadena ---");
        
        try {
            GraphProperties<Integer> graph = new GraphProperties<>();
            
            // Crear cadena: 1 -> 2 -> 3 -> 4 -> 5
            for (int i = 1; i <= 5; i++) {
                graph.insertVertex(i);
            }
            
            for (int i = 1; i < 5; i++) {
                graph.insertEdge(i, i + 1);
            }
            
            System.out.println("Grafo creado: 1 -> 2 -> 3 -> 4 -> 5 (cadena)");
            graph.printAnalysis();
            
            // Probar recorridos
            System.out.println("Recorrido DFS desde 1:");
            graph.dfs(1);
            
            System.out.println("Recorrido BFS desde 1:");
            graph.bfs(1);
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error en prueba de cadena: " + e.getMessage());
        }
    }
    
    // Prueba comparando dos grafos
    public static void testCompareGraphs() {
        System.out.println("--- PRUEBA 4: Comparar Grafos ---");
        
        try {
            // Primer grafo: triángulo con números
            GraphProperties<Integer> graph1 = new GraphProperties<>();
            graph1.insertVertex(1);
            graph1.insertVertex(2);
            graph1.insertVertex(3);
            graph1.insertEdge(1, 2);
            graph1.insertEdge(2, 3);
            graph1.insertEdge(3, 1);
            
            // Segundo grafo: triángulo con letras
            GraphProperties<String> graph2 = new GraphProperties<>();
            graph2.insertVertex("X");
            graph2.insertVertex("Y");
            graph2.insertVertex("Z");
            graph2.insertEdge("X", "Y");
            graph2.insertEdge("Y", "Z");
            graph2.insertEdge("Z", "X");
            
            System.out.println("Grafo 1 (números):");
            graph1.printAnalysis();
            
            System.out.println("Grafo 2 (letras):");
            graph2.printAnalysis();
            
            // Crear otro grafo del mismo tipo para comparar isomorfismo
            GraphProperties<Integer> graph3 = new GraphProperties<>();
            graph3.insertVertex(10);
            graph3.insertVertex(20);
            graph3.insertVertex(30);
            graph3.insertEdge(10, 20);
            graph3.insertEdge(20, 30);
            graph3.insertEdge(30, 10);
            
            System.out.println("Grafo 3 (números diferentes):");
            graph3.printAnalysis();
            
            System.out.println("¿Grafo 1 y Grafo 3 son isomorfos? " + graph1.isIsomorphic(graph3));
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error en comparación: " + e.getMessage());
        }
    }
    
    // Prueba adicional rápida
    public static void quickTest() {
        System.out.println("--- PRUEBA RÁPIDA ---");
        
        try {
            GraphProperties<String> g = new GraphProperties<>();
            
            // Grafo súper simple
            g.insertVertex("A");
            g.insertVertex("B");
            g.insertEdge("A", "B");
            
            System.out.println("Grafo simple A->B:");
            System.out.println("Vértices: " + g.countVertices());
            System.out.println("Aristas: " + g.countEdges());
            System.out.println("Conexo: " + g.isConexo());
            System.out.println("Plano: " + g.isPlanar());
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error en prueba rápida: " + e.getMessage());
        }
    }
}
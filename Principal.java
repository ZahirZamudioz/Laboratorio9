import graph.*;

public class Principal {
    public static void main(String[] args) {
        
        /*GraphLink<String> grafo1 = new GraphLink<>();

        // Inserción de vertices
        grafo1.insertVertex("A");
        grafo1.insertVertex("B");
        grafo1.insertVertex("C");
        grafo1.insertVertex("D");
        grafo1.insertVertex("E");

        // Insercion de aristas
        grafo1.insertEdge("A", "B");
        grafo1.insertEdge("B", "C");
        grafo1.insertEdge("A", "C");
        grafo1.insertEdge("B", "D");
        grafo1.insertEdge("C", "E");
        grafo1.insertEdge("D", "E");

        /* Verificando si existe un vertice y una arista
        System.out.println("\nExiste el vertice A: " + grafo.searchVertex("A"));
        System.out.println("\nExiste un camino de A a B: " + grafo.searchEdge("A", "B"));

        // Elimando un vertice y una arista
        grafo.removeVertex("E");
        grafo.removeEdge("B", "C");

        // Recorrido DFS del grafo
        grafo.dfs("A");

        // Recorrido BFS del grafo
        grafo.bfs("A");

        // Mostrando el camino de un vertice a otro
        System.out.println("\nCamino de A a D: " + grafo.bfsPath("A", "D"));
        
        // Insertando una arista con peso
        grafo1.insertEdgeWeight("B", "C", 4);

        // Mostrando el camino mas corto de A a D
        System.out.println("\nCamino mas corto de A a D: " + grafo1.shortPath("A", "D"));

        // Verificando si el grafo es conexo
        System.out.println("\nEl grafo es conexo: " + grafo1.isConexo());

        // Mostrar la pila con la ruta mas corta
        System.out.println("\nAlgoritmo Dijkstra: " + grafo1.Dijkstra("A", "E"));
        
        System.out.println("=== PRUEBAS DE GraphListEdge ===\n");*/
        
        // Crear instancia del grafo
        GraphListEdge<String, Integer> grafo2 = new GraphListEdge<>();
        
        // Insertando vértices
        grafo2.insertVertex("A");
        grafo2.insertVertex("B");
        grafo2.insertVertex("C");
        grafo2.insertVertex("D");
        grafo2.insertVertex("E");
        
        // Buscando un vértice
        System.out.println("\n¿Existe vértice 'A'? " + grafo2.searchVertex("A"));
        
        // Insertar aristas
        grafo2.insertEdge("A", "B");
        grafo2.insertEdge("B", "C");
        grafo2.insertEdge("C", "D");
        grafo2.insertEdge("A", "C");
        
        // Insertando aristas con información adicional
        grafo2.insertEdge("D", "E", 10);
        grafo2.insertEdge("A", "D", 15);
        
        // Buscar aristas
        System.out.println("\n¿Existe arista A-B? " + grafo2.searchEdge("A", "B"));
        System.out.println("\n¿Existe arista B-A? " + grafo2.searchEdge("B", "A")); 
        
        // Recorrido BFS
        grafo2.bfs("A");
    }
}
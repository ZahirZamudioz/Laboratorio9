import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class JGraphExample {
    public static void main(String[] args) {
        System.out.println("\n=== Ejemplo de grafo con JGraphT ===");
        System.out.println("Representando una red de ciudades con distancias\n");
        
        // Crear un grafo ponderado simple
        Graph<String, DefaultWeightedEdge> ciudades = 
            new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        
        // Agregar vértices (ciudades)
        ciudades.addVertex("Lima");
        ciudades.addVertex("Arequipa");
        ciudades.addVertex("Cusco");
        ciudades.addVertex("Trujillo");
        ciudades.addVertex("Piura");
        
        // Agregar aristas con pesos (distancias en km)
        DefaultWeightedEdge e1 = ciudades.addEdge("Lima", "Arequipa");
        ciudades.setEdgeWeight(e1, 1015.0);
        
        DefaultWeightedEdge e2 = ciudades.addEdge("Arequipa", "Cusco");
        ciudades.setEdgeWeight(e2, 320.0);
        
        DefaultWeightedEdge e3 = ciudades.addEdge("Lima", "Trujillo");
        ciudades.setEdgeWeight(e3, 558.0);
        
        DefaultWeightedEdge e4 = ciudades.addEdge("Lima", "Cusco");
        ciudades.setEdgeWeight(e4, 1100.0);
        
        DefaultWeightedEdge e5 = ciudades.addEdge("Trujillo", "Piura");
        ciudades.setEdgeWeight(e5, 345.0);
        
        // Mostrar información básica del grafo
        System.out.println("Información del grafo:");
        System.out.println("Ciudades: " + ciudades.vertexSet());
        System.out.println("Número de ciudades: " + ciudades.vertexSet().size());
        System.out.println("Número de conexiones: " + ciudades.edgeSet().size());
        
        // Mostrar conexiones y distancias
        System.out.println("\nConexiones y distancia:");
        for (DefaultWeightedEdge arista : ciudades.edgeSet()) {
            String origen = ciudades.getEdgeSource(arista);
            String destino = ciudades.getEdgeTarget(arista);
            double distancia = ciudades.getEdgeWeight(arista);
            System.out.printf("%s ↔ %s: %.0f km%n", origen, destino, distancia);
        }
        
        // Verificar si el grafo está conectado
        ConnectivityInspector<String, DefaultWeightedEdge> inspector = 
            new ConnectivityInspector<>(ciudades);
        System.out.println("\n¿El grafo está conectado? " + inspector.isConnected());
        
        // Mostrar grado de cada vértice
        System.out.println("\nGrado de conexion de cada ciudad:");
        for (String ciudad : ciudades.vertexSet()) {
            System.out.println(ciudad + ": " + ciudades.degreeOf(ciudad) + " conexiones");
        }
        
        // Encontrar camino más corto usando Dijkstra
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = 
            new DijkstraShortestPath<>(ciudades);
        
        var camino = dijkstra.getPath("Lima", "Cusco");
        
        if (camino != null) {
            System.out.println("\nCamino mas corto de Lima a Cusco:");
            System.out.println("Ruta: " + camino.getVertexList());
            System.out.printf("Distancia total: %.0f km%n", camino.getWeight());
        }
        
        // Verificar si existe una arista específica
        boolean existeConexion = ciudades.containsEdge("Lima", "Arequipa");
        System.out.println("\n¿Existe conexión directa Lima-Arequipa? " + existeConexion);
        
        // Obtener vecinos de una ciudad
        System.out.println("\nCiudades conectadas directamente con Lima:");
        for (DefaultWeightedEdge arista : ciudades.edgesOf("Lima")) {
            String vecino = ciudades.getEdgeSource(arista).equals("Lima") 
                ? ciudades.getEdgeTarget(arista) 
                : ciudades.getEdgeSource(arista);
            System.out.println("- " + vecino);
        }
    }
}
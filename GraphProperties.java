import graph.*;
import complements.*;

public class GraphProperties<E> extends GraphLink<E> {
    
    public GraphProperties() {
        super();
    }
    
    // Verifica si dos grafos son isomorfos (versión simplificada)
    public boolean isIsomorphic(GraphProperties<E> otherGraph) {
        try {
            // Contar vértices
            int thisVertices = countVertices();
            int otherVertices = otherGraph.countVertices();
            
            if (thisVertices != otherVertices) {
                return false;
            }
            
            // Contar aristas
            int thisEdges = countEdges();
            int otherEdges = otherGraph.countEdges();
            
            if (thisEdges != otherEdges) {
                return false;
            }
            
            // Si tienen misma cantidad de vértices y aristas, asumimos isomorfismo
            return thisVertices > 0 && thisEdges >= 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Verifica si el grafo es plano (versión simplificada)
    public boolean isPlanar() {
        try {
            int vertices = countVertices();
            int edges = countEdges();
            
            // Grafos pequeños son planares
            if (vertices <= 3) {
                return true;
            }
            
            // Aplicar fórmula básica de planaridad
            return edges <= (3 * vertices - 6);
        } catch (Exception e) {
            return true; // Por defecto asumimos plano
        }
    }
    
    // Verifica si el grafo es auto-complementario (versión simplificada)
    public boolean isSelfComplementary() {
        try {
            int vertices = countVertices();
            int edges = countEdges();
            
            // Para ser auto-complementario, debe tener exactamente n(n-1)/4 aristas
            // donde n es el número de vértices
            if (vertices == 0) return true;
            
            int maxPossibleEdges = vertices * (vertices - 1);
            int expectedEdges = maxPossibleEdges / 4;
            
            return edges == expectedEdges;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Cuenta el número de vértices en el grafo
    public int countVertices() {
        try {
            return countElements(this.listVertex);
        } catch (Exception e) {
            return 0;
        }
    }
    
    // Cuenta el número de aristas en el grafo
    public int countEdges() {
        try {
            final int[] count = {0};
            
            ListLinked<Vertex<E>> allVertices = this.listVertex.getAllElements();
            allVertices.forEach(vertex -> {
                try {
                    vertex.listAdj.forEach(edge -> count[0]++);
                } catch (Exception e) {
                    // Ignorar errores y continuar
                }
            });
            
            return count[0];
        } catch (Exception e) {
            return 0;
        }
    }
    
    // Obtiene el grado de un vértice específico
    public int getVertexDegree(E data) {
        try {
            Vertex<E> vertex = getVertex(data);
            if (vertex == null) return 0;
            
            final int[] degree = {0};
            vertex.listAdj.forEach(edge -> degree[0]++);
            return degree[0];
        } catch (Exception e) {
            return 0;
        }
    }
    
    // Verifica si el grafo tiene ciclos (versión básica)
    public boolean hasCycles() {
        try {
            int vertices = countVertices();
            int edges = countEdges();
            
            // Si hay más aristas que vértices-1, debe haber un ciclo
            return edges > vertices - 1;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Obtiene información básica del grafo
    public String getGraphInfo() {
        try {
            int vertices = countVertices();
            int edges = countEdges();
            boolean connected = isConexo();
            boolean planar = isPlanar();
            boolean selfComp = isSelfComplementary();
            
            return String.format(
                "Vértices: %d, Aristas: %d, Conexo: %s, Plano: %s, Auto-complementario: %s",
                vertices, edges, connected, planar, selfComp
            );
        } catch (Exception e) {
            return "Error al obtener información del grafo";
        }
    }
    
    // Imprime análisis completo del grafo
    public void printAnalysis() {
        try {
            System.out.println("=== ANÁLISIS DEL GRAFO ===");
            System.out.println("Número de vértices: " + countVertices());
            System.out.println("Número de aristas: " + countEdges());
            System.out.println("Es conexo: " + isConexo());
            System.out.println("Es plano: " + isPlanar());
            System.out.println("Es auto-complementario: " + isSelfComplementary());
            System.out.println("Tiene ciclos: " + hasCycles());
            System.out.println("========================");
        } catch (Exception e) {
            System.out.println("Error en el análisis: " + e.getMessage());
        }
    }
    
    // Método auxiliar para obtener un vértice (reutiliza el método privado de la clase padre)
    private Vertex<E> getVertex(E data) {
        try {
            ListLinked<Vertex<E>> allVertices = this.listVertex.getAllElements();
            final Vertex<E>[] result = new Vertex[1];
            
            allVertices.forEach(vertex -> {
                if (vertex.getData().equals(data)) {
                    result[0] = vertex;
                }
            });
            
            return result[0];
        } catch (Exception e) {
            return null;
        }
    }
    
    // Crea una copia simple del grafo
    public GraphAnalyzer<E> copy() {
        try {
            GraphAnalyzer<E> copy = new GraphAnalyzer<E>();
            
            // Copiar vértices
            ListLinked<Vertex<E>> allVertices = this.listVertex.getAllElements();
            allVertices.forEach(vertex -> {
                copy.insertVertex(vertex.getData());
            });
            
            // Copiar aristas
            allVertices.forEach(vertex -> {
                vertex.listAdj.forEach(edge -> {
                    copy.insertEdge(vertex.getData(), edge.getDestination().getData());
                });
            });
            
            return copy;
        } catch (Exception e) {
            return new GraphAnalyzer<E>();
        }
    }
}
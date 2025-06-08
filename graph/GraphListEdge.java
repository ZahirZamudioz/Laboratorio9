package graph;

import java.util.ArrayList;
import complements.Queue;
import complements.ListLinked;

public class GraphListEdge<V, E> {
    private ArrayList<VertexObj<V, E>> secVertex;
    private ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    // Inserta el vértice 'v' en caso no exista
    public void insertVertex(V info) {
        if (!searchVertex(info)) {
            int position = secVertex.size();
            VertexObj<V, E> newVertex = new VertexObj<>(info, position);
            secVertex.add(newVertex);
        }
    }

    // Inserta la arista entre los vértices 'v' y 'z' en caso aún no haya sido insertada
    public void insertEdge(V info1, V info2) {
        VertexObj<V, E> vertex1 = getVertex(info1);
        VertexObj<V, E> vertex2 = getVertex(info2);

        if (vertex1 != null && vertex2 != null && !searchEdge(info1, info2)) {
            int position = secEdge.size();
            EdgeObj<V, E> newEdge = new EdgeObj<>(vertex1, vertex2, null, position);
            secEdge.add(newEdge);
        }
    }

    // Sobrecarga para insertar arista con información adicional
    public void insertEdge(V info1, V info2, E edgeInfo) {
        VertexObj<V, E> vertex1 = getVertex(info1);
        VertexObj<V, E> vertex2 = getVertex(info2);

        if (vertex1 != null && vertex2 != null && !searchEdge(info1, info2)) {
            int position = secEdge.size();
            EdgeObj<V, E> newEdge = new EdgeObj<>(vertex1, vertex2, edgeInfo, position);
            secEdge.add(newEdge);
        }
    }

    // Busca el vértice 'v' y retorna un verdadero si existe el vértice o un falso en caso contrario
    public boolean searchVertex(V info) {
        return getVertex(info) != null;
    }

    // Busca la arista entre los vértices 'v' y 'z'. Retorna verdadero si existe y en caso contrario un falso
    public boolean searchEdge(V info1, V info2) {
        VertexObj<V, E> vertex1 = getVertex(info1);
        VertexObj<V, E> vertex2 = getVertex(info2);

        if (vertex1 == null || vertex2 == null) {
            return false;
        }

        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.connectsVertices(vertex1, vertex2)) {
                return true;
            }
        }
        return false;
    }

    // Realiza el recorrido en profundidad a partir de 'v' del grafo y muestre los vértices que se vayan visitando
    public void bfs(V startInfo) {
        VertexObj<V, E> startVertex = getVertex(startInfo);
        
        if (startVertex == null) {
            System.out.println("El vértice " + startInfo + " no existe en el grafo.");
            return;
        }

        System.out.println("\nRecorrido BFS comenzando desde: " + startInfo);
        
        Queue<VertexObj<V, E>> queue = new Queue<>();
        ListLinked<VertexObj<V, E>> visited = new ListLinked<>();
        
        queue.enqueue(startVertex);
        visited.insertLast(startVertex);
        
        while (!queue.isEmpty()) {
            VertexObj<V, E> currentVertex = queue.dequeue();
            System.out.print(currentVertex.getInfo() + " ");
            
            // Obtener vértices adyacentes
            ListLinked<VertexObj<V, E>> adjacentVertices = getAdjacentVertices(currentVertex);
            
            adjacentVertices.forEach(adjVertex -> {
                if (!visited.contains(adjVertex)) {
                    visited.insertLast(adjVertex);
                    queue.enqueue(adjVertex);
                }
            });
        }
        System.out.println();
    }

    // Método auxiliar para obtener un vértice por su información
    private VertexObj<V, E> getVertex(V info) {
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(info)) {
                return vertex;
            }
        }
        return null;
    }

    // Método auxiliar para obtener vértices adyacentes a un vértice dado
    private ListLinked<VertexObj<V, E>> getAdjacentVertices(VertexObj<V, E> vertex) {
        ListLinked<VertexObj<V, E>> adjacentVertices = new ListLinked<>();
        
        for (EdgeObj<V, E> edge : secEdge) {
            VertexObj<V, E> otherVertex = edge.getOtherVertex(vertex);
            if (otherVertex != null) {
                adjacentVertices.insertLast(otherVertex);
            }
        }
        
        return adjacentVertices;
    }

    // Método para obtener todos los vértices
    public ArrayList<VertexObj<V, E>> getVertices() {
        return new ArrayList<>(secVertex);
    }

    // Método para obtener todas las aristas
    public ArrayList<EdgeObj<V, E>> getEdges() {
        return new ArrayList<>(secEdge);
    }

    // Método para mostrar el grafo
    public void printGraph() {
        System.out.println("Vértices: " + secVertex.size());
        for (VertexObj<V, E> vertex : secVertex) {
            System.out.println("  " + vertex);
        }
        
        System.out.println("Aristas: " + secEdge.size());
        for (EdgeObj<V, E> edge : secEdge) {
            System.out.println("  " + edge);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraphListEdge:\n");
        sb.append("Vertices: ").append(secVertex.size()).append("\n");
        sb.append("Edges: ").append(secEdge.size()).append("\n");
        return sb.toString();
    }
}

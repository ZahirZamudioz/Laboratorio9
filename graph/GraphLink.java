package graph;

import complements.*;

public class GraphLink<E> {

    public ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    // Inserta un vertice si no existe en el grafo
    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            Vertex<E> newVertex = new Vertex<E>(data);
            listVertex.insertLast(newVertex);
        }
    }

    // Inserta una arista entre dos vertices si ambos existen y la arista no existe
    public void insertEdge(E verOri, E verDes) {
        // Buscar los vértices origen y destino
        Vertex<E> vertexOrigen = getVertex(verOri);
        Vertex<E> vertexDestino = getVertex(verDes);

        // Solo insertar la arista si ambos vértices existen
        if (vertexOrigen != null && vertexDestino != null) {
            Edge<E> newEdge = new Edge<E>(vertexDestino);

            // Verificar si la arista ya existe para evitar duplicados
            if (!vertexOrigen.listAdj.contains(newEdge)) {
                vertexOrigen.listAdj.insertLast(newEdge);
            }
        }
    }

    // Verifica si un vertice existe en el grafo
    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    // Verifica si existe una arista entre dos vertices
    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vertexOrigen = getVertex(verOri);
        Vertex<E> vertexDestino = getVertex(verDes);

        if (vertexOrigen != null && vertexDestino != null) {
            Edge<E> targetEdge = new Edge<E>(vertexDestino);
            return vertexOrigen.listAdj.contains(targetEdge);
        }
        return false;
    }

    // Elimina un vertice y todas las aristas relacionadas
    public void removeVertex(E data) {
        Vertex<E> vertexToRemove = getVertex(data);

        if (vertexToRemove != null) {
            // Eliminar todas las aristas que apuntan a este vértice
            removeIncomingEdges(vertexToRemove);

            // Eliminar el vértice de la lista principal
            listVertex.removeNode(vertexToRemove);
        }
    }

    // Elimina una arista entre dos vertices si existe
    public void removeEdge(E verOri, E verDes) {
        Vertex<E> vertexOrigen = getVertex(verOri);
        Vertex<E> vertexDestino = getVertex(verDes);

        if (vertexOrigen != null && vertexDestino != null) {
            Edge<E> edgeToRemove = new Edge<E>(vertexDestino);
            vertexOrigen.listAdj.removeNode(edgeToRemove);
        }
    }

    // Realiza un recorrido DFS desde un vertice dado
    public void dfs(E data) {
        Vertex<E> startVertex = getVertex(data);

        if (startVertex != null) {
            System.out.println("\nRecorrido DFS comenzando desde: " + data);
            ListLinked<Vertex<E>> visited = new ListLinked<Vertex<E>>();
            dfsRec(startVertex, visited);
            System.out.println(); // Nueva línea al final
        } else {
            System.out.println("El vértice " + data + " no existe en el grafo.");
        }
    }

    // Método recursivo auxiliar para DFS
    private void dfsRec(Vertex<E> vertex, ListLinked<Vertex<E>> visited) {
        // Marcar el vértice actual como visitado
        visited.insertLast(vertex);
        System.out.print(vertex.getData() + " ");

        // Recorrer todos los vértices adyacentes
        visitAdjacentVertices(vertex, visited);
    }

    // Método auxiliar para recorrer los vertices adyacentes en DFS
    private void visitAdjacentVertices(Vertex<E> vertex, ListLinked<Vertex<E>> visited) {
        // Obtener todas las aristas del vértice actual
        ListLinked<Edge<E>> allEdges = vertex.listAdj.getAllElements();

        // Procesar cada arista
        processEdgesForDFS(allEdges, visited);
    }

    // Método auxiliar que procesa aristas en DFS y continúa si no están visitados
    private void processEdgesForDFS(ListLinked<Edge<E>> edges, ListLinked<Vertex<E>> visited) {
        // Usar forEach para recorrer todas las aristas
        edges.forEach(edge -> {
            // Obtener el vértice destino de la arista
            Vertex<E> destVertex = getDestinationVertex(edge);

            // Si el vértice no ha sido visitado, hacer DFS recursivo
            if (destVertex != null && !visited.contains(destVertex)) {
                dfsRec(destVertex, visited);
            }
        });
    }

    // Retorna el vértice destino de una arista
    private Vertex<E> getDestinationVertex(Edge<E> edge) {
        return edge.getDestination();
    }

    // Elimina aristas que apuntan a un vertice dado
    private void removeIncomingEdges(Vertex<E> targetVertex) {
        // Obtener todos los vértices del grafo
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();

        // Para cada vértice, eliminar aristas que apunten al vértice objetivo
        allVertices.forEach(vertex -> {
            if (!vertex.equals(targetVertex)) {
                removeEdgeToTarget(vertex, targetVertex);
            }
        });
    }

    // Elimina una arista desde un vertice origen hacia un vertice destino
    private void removeEdgeToTarget(Vertex<E> sourceVertex, Vertex<E> targetVertex) {
        Edge<E> edgeToRemove = new Edge<E>(targetVertex);
        sourceVertex.listAdj.removeNode(edgeToRemove);
    }

    // Realiza un recorrido BFS desde un vertice dado
    public void bfs(E data) {
        Vertex<E> startVertex = getVertex(data);

        if (startVertex != null) {
            System.out.println("\nRecorrido BFS comenzando desde: " + data);

            // Estructuras para BFS
            Queue<Vertex<E>> queue = new Queue<Vertex<E>>();
            ListLinked<Vertex<E>> visited = new ListLinked<Vertex<E>>();

            // Agregar el vértice inicial a la cola y marcarlo como visitado
            queue.enqueue(startVertex);
            visited.insertLast(startVertex);

            // Procesar la cola hasta que esté vacía
            while (!queue.isEmpty()) {
                Vertex<E> currentVertex = queue.dequeue();

                // Mostrar el vértice actual
                System.out.print(currentVertex.getData() + " ");

                // Agregar vértices adyacentes no visitados a la cola
                addAdjacentToQueue(currentVertex, queue, visited);
            }
            System.out.println();
        } else {
            System.out.println("El vértice " + data + " no existe en el grafo.");
        }
    }

    // Encuentra el camino mas corto entre dos vertices usando BFS
    public java.util.ArrayList<E> bfsPath(E start, E end) {
        Vertex<E> startVertex = getVertex(start);
        Vertex<E> endVertex = getVertex(end);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return null;
        }

        if (startVertex.equals(endVertex)) {
            java.util.ArrayList<E> path = new java.util.ArrayList<E>();
            path.add(start);
            return path;
        }

        // Estructuras para BFS con seguimiento de padres
        Queue<Vertex<E>> queue = new Queue<Vertex<E>>();
        ListLinked<Vertex<E>> visited = new ListLinked<Vertex<E>>();
        java.util.HashMap<Vertex<E>, Vertex<E>> parent = new java.util.HashMap<Vertex<E>, Vertex<E>>();

        // Inicializar BFS
        queue.enqueue(startVertex);
        visited.insertLast(startVertex);
        parent.put(startVertex, null);

        boolean pathFound = false;

        // BFS para encontrar el camino
        while (!queue.isEmpty() && !pathFound) {
            Vertex<E> currentVertex = queue.dequeue();

            // Procesar vértices adyacentes
            pathFound = processAdjacentForPath(currentVertex, endVertex, queue, visited, parent);
        }

        // Reconstruir el camino si se encontró
        if (pathFound) {
            return reconstructPath(startVertex, endVertex, parent);
        } else {
            System.out.println("No existe camino entre " + start + " y " + end);
            return null;
        }
    }

    // Metodo auxiliar que agrega vertices adyacentes no visitados a la cola (BFS)
    private void addAdjacentToQueue(Vertex<E> vertex, Queue<Vertex<E>> queue, ListLinked<Vertex<E>> visited) {
        // Obtener todas las aristas del vértice actual
        ListLinked<Edge<E>> allEdges = vertex.listAdj.getAllElements();

        // Procesar cada arista
        allEdges.forEach(edge -> {
            Vertex<E> adjacentVertex = edge.getDestination();

            // Si el vértice adyacente no ha sido visitado
            if (adjacentVertex != null && !visited.contains(adjacentVertex)) {
                visited.insertLast(adjacentVertex);
                queue.enqueue(adjacentVertex);
            }
        });
    }

    // Metodo auxiliar que procesa los adyacentes en BFS y construye un mapa de
    // padres
    private boolean processAdjacentForPath(Vertex<E> currentVertex, Vertex<E> endVertex,
            Queue<Vertex<E>> queue, ListLinked<Vertex<E>> visited,
            java.util.HashMap<Vertex<E>, Vertex<E>> parent) {

        // Obtener todas las aristas del vértice actual
        ListLinked<Edge<E>> allEdges = currentVertex.listAdj.getAllElements();

        // Variable para controlar si se encontró el objetivo
        final boolean[] found = { false };

        // Procesar cada arista
        allEdges.forEach(edge -> {
            if (!found[0]) { // Solo continuar si no se ha encontrado
                Vertex<E> adjacentVertex = edge.getDestination();

                if (adjacentVertex != null && !visited.contains(adjacentVertex)) {
                    visited.insertLast(adjacentVertex);
                    parent.put(adjacentVertex, currentVertex);

                    // Verificar si llegamos al objetivo
                    if (adjacentVertex.equals(endVertex)) {
                        found[0] = true;
                    } else {
                        queue.enqueue(adjacentVertex);
                    }
                }
            }
        });

        return found[0];
    }

    // Método auxiliar que reconstruye el camino desde el vertice final hasta el
    // inicial
    private java.util.ArrayList<E> reconstructPath(Vertex<E> start, Vertex<E> end,
            java.util.HashMap<Vertex<E>, Vertex<E>> parent) {

        java.util.ArrayList<E> path = new java.util.ArrayList<E>();
        Vertex<E> current = end;

        // Reconstruir el camino desde el final hacia el inicio
        while (current != null) {
            path.add(0, current.getData()); // Insertar al inicio
            current = parent.get(current);
        }
        return path;
    }

    // Inserta una arista del vértice v a z con peso w
    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        Vertex<E> vertexOrigen = getVertex(verOri);
        Vertex<E> vertexDestino = getVertex(verDes);

        if (vertexOrigen != null && vertexDestino != null) {
            Edge<E> newEdge = new Edge<E>(vertexDestino, weight);

            // Verificar si la arista ya existe para evitar duplicados
            if (!vertexOrigen.listAdj.contains(newEdge)) {
                vertexOrigen.listAdj.insertLast(newEdge);
            }
        }
    }

    // Calcula la ruta más corta entre dos vértices usando BFS (sin pesos)
    public java.util.ArrayList<E> shortPath(E start, E end) {
        return bfsPath(start, end); // Reutiliza el método existente
    }

    // Verifica si el grafo es conexo
    public boolean isConexo() {
        if (listVertex.isEmpty()) {
            return true; // Grafo vacío se considera conexo
        }

        // Obtener el primer vértice
        Vertex<E> startVertex = listVertex.getFirstElement();
        if (startVertex == null) {
            return true;
        }

        // Realizar DFS desde el primer vértice
        ListLinked<Vertex<E>> visited = new ListLinked<Vertex<E>>();
        dfsForConnectivity(startVertex, visited);

        // Contar los vértices visitados y compararlos con el total
        int visitedCount = countElements(visited);
        int totalCount = countElements(listVertex);

        // El grafo es conexo si todos los vértices fueron visitados
        return visitedCount == totalCount;
    }

    // Método auxiliar para contar elementos en una ListLinked
    public int countElements(ListLinked<Vertex<E>> list) {
        final int[] count = { 0 };
        list.forEach(vertex -> count[0]++);
        return count[0];
    }

    // Método auxiliar para verificar conectividad
    private void dfsForConnectivity(Vertex<E> vertex, ListLinked<Vertex<E>> visited) {
        visited.insertLast(vertex);

        // Recorrer todos los vértices adyacentes
        ListLinked<Edge<E>> allEdges = vertex.listAdj.getAllElements();
        allEdges.forEach(edge -> {
            Vertex<E> destVertex = edge.getDestination();
            if (destVertex != null && !visited.contains(destVertex)) {
                dfsForConnectivity(destVertex, visited);
            }
        });
    }

    // Algoritmo de Dijkstra para encontrar la ruta más corta con pesos
    public java.util.Stack<E> Dijkstra(E start, E end) {
        Vertex<E> startVertex = getVertex(start);
        Vertex<E> endVertex = getVertex(end);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return null;
        }

        if (startVertex.equals(endVertex)) {
            java.util.Stack<E> path = new java.util.Stack<E>();
            path.push(start);
            return path;
        }

        // Crear lista de información de vértices para Dijkstra
        ListLinked<VertexInfo<E>> vertexInfoList = new ListLinked<>();
        ListLinked<Vertex<E>> allVertices = listVertex.getAllElements();
        allVertices.forEach(vertex -> {
            VertexInfo<E> info = new VertexInfo<>(vertex);
            if (vertex.equals(startVertex)) {
                info.distance = 0;
            }
            vertexInfoList.insertLast(info);
        });

        // Algoritmo principal de Dijkstra
        while (true) {
            VertexInfo<E> current = findMinUnvisitedVertex(vertexInfoList);
            if (current == null || current.distance == Integer.MAX_VALUE) {
                break;
            }

            current.visited = true;
            if (current.vertex.equals(endVertex)) {
                break;
            }
            updateNeighborDistances(current, vertexInfoList);
        }
        return reconstructPathFromVertexInfo(startVertex, endVertex, vertexInfoList);
    }

    // Clase auxiliar para almacenar información de vértices en Dijkstra
    private static class VertexInfo<E> {
        Vertex<E> vertex;
        int distance;
        boolean visited;
        Vertex<E> previous;

        public VertexInfo(Vertex<E> vertex) {
            this.vertex = vertex;
            this.distance = Integer.MAX_VALUE;
            this.visited = false;
            this.previous = null;
        }
    }

    // Encontrar vértice no visitado con menor distancia
    private VertexInfo<E> findMinUnvisitedVertex(ListLinked<VertexInfo<E>> vertexInfoList) {
        final VertexInfo<E>[] minVertex = new VertexInfo[1];
        final int[] minDistance = { Integer.MAX_VALUE };

        vertexInfoList.forEach(info -> {
            if (!info.visited && info.distance < minDistance[0]) {
                minDistance[0] = info.distance;
                minVertex[0] = info;
            }
        });

        return minVertex[0];
    }

    // Actualizar distancias de vértices vecinos
    private void updateNeighborDistances(VertexInfo<E> current, ListLinked<VertexInfo<E>> vertexInfoList) {
        ListLinked<Edge<E>> edges = current.vertex.listAdj.getAllElements();

        edges.forEach(edge -> {
            Vertex<E> neighbor = edge.getDestination();
            VertexInfo<E> neighborInfo = findVertexInfo(neighbor, vertexInfoList);

            if (neighborInfo != null && !neighborInfo.visited) {
                int edgeWeight = getEdgeWeight(edge);
                int newDistance = current.distance + edgeWeight;

                if (newDistance < neighborInfo.distance) {
                    neighborInfo.distance = newDistance;
                    neighborInfo.previous = current.vertex;
                }
            }
        });
    }

    // Encontrar información de un vértice específico
    private VertexInfo<E> findVertexInfo(Vertex<E> vertex, ListLinked<VertexInfo<E>> vertexInfoList) {
        final VertexInfo<E>[] result = new VertexInfo[1];

        vertexInfoList.forEach(info -> {
            if (info.vertex.equals(vertex)) {
                result[0] = info;
            }
        });

        return result[0];
    }

    // Método auxiliar para obtener el peso de una arista
    private int getEdgeWeight(Edge<E> edge) {
        return 1; // Valor por defecto
    }

    // Reconstruir camino desde la información de vértices
    private java.util.Stack<E> reconstructPathFromVertexInfo(Vertex<E> start, Vertex<E> end,
            ListLinked<VertexInfo<E>> vertexInfoList) {
        VertexInfo<E> endInfo = findVertexInfo(end, vertexInfoList);

        if (endInfo == null || endInfo.distance == Integer.MAX_VALUE) {
            System.out.println("No existe camino entre " + start.getData() + " y " + end.getData());
            return null;
        }

        java.util.Stack<E> path = new java.util.Stack<>();
        Vertex<E> current = end;

        while (current != null) {
            path.push(current.getData());
            VertexInfo<E> currentInfo = findVertexInfo(current, vertexInfoList);
            current = currentInfo != null ? currentInfo.previous : null;
        }
        return path;
    }

    // Devuelve una representacion en texto de todos los vertices
    public String toString() {
        return this.listVertex.toString();
    }

    private Vertex<E> getVertex(E data) {
        Vertex<E> tempVertex = new Vertex<E>(data);
        return listVertex.getElement(tempVertex);
    }
}

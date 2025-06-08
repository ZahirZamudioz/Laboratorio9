package graph;

public class EdgeObj<V, E> {
    protected E info;
    protected VertexObj<V, E> endVertex1;
    protected VertexObj<V, E> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    // Getters
    public E getInfo() {
        return info;
    }

    public VertexObj<V, E> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V, E> getEndVertex2() {
        return endVertex2;
    }

    public int getPosition() {
        return position;
    }

    // Setters
    public void setInfo(E info) {
        this.info = info;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // Método para verificar si la arista conecta dos vértices específicos
    public boolean connectsVertices(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        return (endVertex1.equals(v1) && endVertex2.equals(v2)) ||
               (endVertex1.equals(v2) && endVertex2.equals(v1));
    }

    // Método para obtener el otro vértice de la arista
    public VertexObj<V, E> getOtherVertex(VertexObj<V, E> vertex) {
        if (endVertex1.equals(vertex)) {
            return endVertex2;
        } else if (endVertex2.equals(vertex)) {
            return endVertex1;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        EdgeObj<?, ?> edgeObj = (EdgeObj<?, ?>) o;
        return connectsVertices((VertexObj<V, E>) edgeObj.endVertex1, (VertexObj<V, E>) edgeObj.endVertex2);
    }

    @Override
    public String toString() {
        return "(" + endVertex1.getInfo() + " -- " + endVertex2.getInfo() + ")";
    }
}

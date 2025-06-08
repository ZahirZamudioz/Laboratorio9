package graph;

public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    // Getters
    public V getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    // Setters
    public void setInfo(V info) {
        this.info = info;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        VertexObj<?, ?> vertexObj = (VertexObj<?, ?>) o;
        return info != null ? info.equals(vertexObj.info) : vertexObj.info == null;
    }

    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}

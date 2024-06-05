import java.util.*;

public class PrimAlgorithm {
    private static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    public static List<Edge> prim(int vertices, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        for (Edge edge : edges) {
            graph.get(edge.source).add(edge);
            graph.get(edge.destination).add(new Edge(edge.destination, edge.source, edge.weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        pq.addAll(graph.get(0));

        boolean[] inMST = new boolean[vertices];
        inMST[0] = true; 

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (inMST[current.destination]) {
                continue;
            }

            mst.add(current);
            inMST[current.destination] = true;

            for (Edge edge : graph.get(current.destination)) {
                if (!inMST[edge.destination]) {
                    pq.add(edge);
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(1, 4, 3));
        edges.add(new Edge(2, 5, 3));
        edges.add(new Edge(3, 4, 3));
        edges.add(new Edge(4, 5, 2));

        List<Edge> mst = prim(6, edges);
        for (Edge e : mst) {
            System.out.println("Edge: " + e.source + " - " + e.destination + " with weight " + e.weight);
        }
    }
}

package CWH;
public class GraphColoring {
    final int V;
    int color[];

    GraphColoring(int v) {
        V = v;
        color = new int[V];
    }

    boolean isSafe(int v, int graph[][], int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    boolean graphColorUtil(int graph[][], int m, int v) {
        if (v == V)
            return true;
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, c)) {
                color[v] = c;
                if (graphColorUtil(graph, m, v + 1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    void graphColoring(int graph[][], int m) {
        if (!graphColorUtil(graph, m, 0)) {
            System.out.println("Solution does not exist");
            return;
        }
        for (int i = 0; i < V; i++)
            System.out.println("Vertex " + (i + 1) + " --->  Color " + color[i]);
    }

    public static void main(String args[]) {
        int graph1[][] = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        GraphColoring g1 = new GraphColoring(4);
        int m = 3; // number of colors
        System.out.println("Graph Coloring Solution:");
        g1.graphColoring(graph1, m);
    }
}



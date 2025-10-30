package CWH;
public class HamiltonianCycle {
    int V;
    int path[];

    boolean isSafe(int v, int graph[][], int path[], int pos) {
        if (graph[path[pos - 1]][v] == 0) return false;
        for (int i = 0; i < pos; i++)
            if (path[i] == v) return false;
        return true;
    }

    boolean hamCycleUtil(int graph[][], int path[], int pos) {
        if (pos == V) return graph[path[pos - 1]][path[0]] == 1;
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamCycleUtil(graph, path, pos + 1)) return true;
                path[pos] = -1;
            }
        }
        return false;
    }

    boolean hamCycle(int graph[][], char[] names) {
        V = graph.length;
        path = new int[V];
        for (int i = 0; i < V; i++) path[i] = -1;
        path[0] = 0;
        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("No Hamiltonian Cycle exists");
            return false;
        }
        printSolution(path, names);
        return true;
    }

    void printSolution(int path[], char[] names) {
        for (int i = 0; i < V; i++) System.out.print(names[path[i]] + " ");
        System.out.println(names[path[0]]);
    }

    public static void main(String args[]) {
        int graph1[][] = {
            {0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };
        char names1[] = {'A', 'B', 'C', 'D', 'E'};
        System.out.println("Case 1:");
        new HamiltonianCycle().hamCycle(graph1, names1);

        int graph2[][] = {
            {0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0}
        };
        char names2[] = {'T', 'M', 'S', 'H', 'C'};
        System.out.println("\nCase 2:");
        new HamiltonianCycle().hamCycle(graph2, names2);
    }
}

 

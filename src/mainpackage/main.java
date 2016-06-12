package mainpackage;

import java.util.Optional;
import java.util.stream.IntStream;

import algo.graph.DFS;
import algo.graph.Graph;
import algo.graph.TraverseState;

public class main {
  public static void main(String[] args) {
    Graph g = new Graph(6, true);
    g.addEdge(0, 5);
    g.addEdge(0, 1);
    g.addEdge(0, 4);
    g.addEdge(1, 2);
    g.addEdge(1, 5);
    g.addEdge(2, 3);
    g.addEdge(2, 4);
    g.addEdge(3, 0);

    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    int curTime = 0;
    DFS.traverse(g, 0, state, parents, entry, exit, curTime, Optional.of(i -> {
      System.out.println("Processed Node:" + i);
    }), Optional.ofNullable(null), Optional.of((x, y) -> {
      String edgeType = "";
      if (state[y] == TraverseState.UNDISCOVERED) {
        edgeType = "Tree Edge";
      } else if (state[y] == TraverseState.DISCOVERED) {
        edgeType = "Back Edge";
      } else if (state[y] == TraverseState.PROCESSED && entry[x] < entry[y]) {
        edgeType = "Forward Edge";
      } else if (state[y] == TraverseState.PROCESSED && entry[x] > entry[y]) {
        edgeType = "Cross Edge";
      }

      System.out.println("Processed " + edgeType + ":" + x + "," + y);
      if (state[y] == TraverseState.DISCOVERED) {
        System.out.println("Circle");
      }
    }));
  }

  public static int coinChange(int value, int[] coins) {
    return -1;
  }
}
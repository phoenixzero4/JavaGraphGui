package application;

import java.util.ArrayList;
import java.util.List;

public class NineTails {

	public final static int NUMBEROFNODES = 512;

	protected AbstractGraph<Integer>.Tree tree;

	public NineTails() {
		List<AbstractGraph.Edge> edges = getEdges();

		UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, NUMBEROFNODES);
		tree = graph.bfs(511);

	}

	private List<AbstractGraph.Edge> getEdges() {
		List<AbstractGraph.Edge> edges = new ArrayList<>();

		for (int i = 0; i < NUMBEROFNODES; i++) {
			for (int j = 0; j < 9; j++) {
				char[] node = getNode(i);
				if (node[j] == 'H') {
					int v = getFlippedNode(node, j);

					edges.add(new AbstractGraph.Edge(v, i));
				}
			}
		}

		return edges;
	}

	public static int getFlippedNode(char[] node, int position) {
		int row = position / 3;
		int column = position % 3;

		flipcell(node, row, column);
		flipcell(node, row - 1, column);
		flipcell(node, row + 1, column);
		flipcell(node, row, column - 1);
		flipcell(node, row, column + 1);

		return getIndex(node);
	}

	public static void flipcell(char[] node, int row, int column) {
		if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
			if (node[row * 3 + column] == 'H') {
				node[row * 3 + column] = 'T';
			} else {
				node[row * 3 + column] = 'H';
			}
		}
	}

	public static int getIndex(char[] node) {
		int result = 0;
		for (int i = 0; i < 9; i++) {
			if (node[i] == 'T') {
				result = result * 2 + 1;
			} else {
				result = result * 2 + 0;
			}
		}
		return result;
	}

	public static char[] getNode(int index) {
		char[] result = new char[9];
		for (int i = 0; i < 9; i++) {
			int digit = index % 2;
			if (digit == 0) {
				result[8 - i] = 'H';
			} else {
				result[8 - i] = 'T';
			}
			index = index / 2;
		}
		return result;
	}

	public List<Integer> getShortestPath(int index) {
		return tree.getPath(index);
	}

	public static void printNode(char[] node) {

		for (int i = 0; i < 9; i++) {
			if (i % 3 != 2) {
				System.out.print(node[i]);
			} else {
				System.out.println(node[i]);
			}

		}
		System.out.println();
	}

}

package MyProject;

import java.util.*;

public class Dijkstras {

	private static final int NO_PARENT = -1;

	private int distances;

	private List<Integer> path = new ArrayList<Integer>();
	
	private String paths;

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}
	
	public void resetPaths() {
		this.paths = "";
	}

	private static final Dijkstras INSTANCE = new Dijkstras();

	private Dijkstras() {
        
    }

	public static Dijkstras getInstance() {
		return INSTANCE;
	}

	public void dijkstra(int[][] adjacencyMatrix, int startVertex, int endVertex) {
		int nVertices = adjacencyMatrix[0].length;

		int[] shortestDistances = new int[nVertices];

		boolean[] added = new boolean[nVertices];

		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}

		shortestDistances[startVertex] = 0;

		int[] parents = new int[nVertices];

		parents[startVertex] = NO_PARENT;

		for (int i = 1; i < nVertices; i++) {

			int nearestVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					nearestVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}

			added[nearestVertex] = true;

			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = nearestVertex;
					shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
				}
			}
		}

		solveSolution(startVertex, shortestDistances, parents, endVertex);
	}

	private void solveSolution(int startVertex, int[] distances, int[] parents, int endVertex) {
		int nVertices = distances.length;
		int vertexIndex = endVertex;
		if (vertexIndex != startVertex) {
			setDistance(distances[vertexIndex]);
			savePath(vertexIndex, parents);
		}
	}

	public void setPath(int path) {
		this.path.add(path);
	}

	public void setDistance(int distances) {
		this.distances = distances;
	}

	public int getDistance() {
		return this.distances;
	}

	public List<Integer> getPath() {
		return this.path;
	}

	private void savePath(int currentVertex, int[] parents) {
		if (currentVertex == NO_PARENT) {
			return;
		}
		savePath(parents[currentVertex], parents);
		setPath(currentVertex);

	}

	public void printPathStep() {
		String path = "";
		for (int i = 0; i < this.path.size(); i++) {
			if (i != this.path.size() - 1) {
				path = path + this.path.get(i) + " ===> ";
			} else {
				path = path + this.path.get(i);
			}
		}
		setPaths(path);
		this.path.removeAll(this.path);
	}

}

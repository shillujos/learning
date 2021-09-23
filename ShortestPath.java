package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Write a function, shortestPath, that takes in an array of edges for an undirected graph and two nodes (nodeA, nodeB). 
 * The function should return the length of the shortest path between A and B. 
 * Consider the length as the number of edges in the path, not the number of nodes. 
 * If there is no path between A and B, then return -1.
 */

/**
 * Java implementation of Alvin's graph traversal course - ShortestPath
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 *
 */
public class ShortestPath {

	public static void main(String[] args) {
		String[][] edges= new String[5][2];
		edges[0] = new String[] {"w","x"};
		edges[1] = new String[] {"x", "y"};
		edges[2] = new String[] {"z", "y"};
		edges[3] = new String[] {"z", "v"};
		edges[4] = new String[] {"w", "v"};

		HashMap<String, ArrayList<String>> graph = constructGraph(edges);


		System.out.println("Count Test00::"+bfs(graph, "w", "z", new HashSet<String>()));
		System.out.println("Count Test01::"+bfs(graph, "y", "x", new HashSet<String>()));

		String[][] test02 = new String[7][2];
		test02[0] = new String[] {"a","c"};
		test02[1] = new String[] {"a","b"};
		test02[2] = new String[] {"c","b"};
		test02[3] = new String[] {"c","d"};
		test02[4] = new String[] {"b","d"};
		test02[5] = new String[] {"e","d"};
		test02[6] = new String[] {"g","f"};
		graph = constructGraph(test02);
		System.out.println("Count Test02::"+bfs(graph, "a", "e", new HashSet<String>()));
		System.out.println("Count Test03::"+bfs(graph, "e", "c", new HashSet<String>()));
		System.out.println("Count Test04::"+bfs(graph, "b", "g", new HashSet<String>()));

		String[][] test05 = new String[5][2];
		test05[0] = new String[] {"c","n"};
		test05[1] = new String[] {"c","e"};
		test05[2] = new String[] {"c","s"};
		test05[3] = new String[] {"c","w"};
		test05[4] = new String[] {"w","e"};

		graph = constructGraph(test05);
		System.out.println("Count Test05::"+bfs(graph, "w", "e", new HashSet<String>()));
		System.out.println("Count Test06::"+bfs(graph, "n", "e", new HashSet<String>()));

		String[][] test07 = new String[7][2];
		test07[0] = new String[] {"m","n"};
		test07[1] = new String[] {"n","o"};
		test07[2] = new String[] {"o","p"};
		test07[3] = new String[] {"p","q"};
		test07[4] = new String[] {"t","o"};
		test07[5] = new String[] {"r","q"};
		test07[6] = new String[] {"r","s"};

		graph = constructGraph(test07);
		System.out.println("Count Test07::"+bfs(graph, "m", "s", new HashSet<String>()));
	}

	/**
	 * Construct an adjacency list from the edges
	 * @param edges
	 * @return
	 */
	private static HashMap<String, ArrayList<String>> constructGraph(String[][] edges) {
		HashMap<String, ArrayList<String>> graph = new HashMap<>();
		for(String[] edge : edges) {
			ArrayList<String> tempOne = graph.getOrDefault(edge[0], new ArrayList<String>());
			tempOne.add(edge[1]);
			graph.put(edge[0], tempOne);

			ArrayList<String> tempTwo = graph.getOrDefault(edge[1], new ArrayList<String>());
			tempTwo.add(edge[0]);
			graph.put(edge[1], tempTwo);
		}
		return graph;
	}

	/**
	 * Do a bfs to find the shortest path
	 * @param graph
	 * @param src
	 * @param dest
	 * @param visited
	 * @return
	 */
	private static int bfs(HashMap<String, ArrayList<String>> graph, String src, String dest, HashSet<String> visited) {
		int shortest = -1;

		ArrayDeque<QueueNode> queue = new ArrayDeque<>();
		queue.offer(new QueueNode(src, 0));
		visited.add(src);
		int newLevel = 0;
		while(queue.size() > 0) {
			QueueNode curr = queue.poll();
			if(curr.val.equals(dest)) {
				//since we are using breadth first, the first time we
				//hit the dest, it is guaranteed to be shortest
				shortest = curr.level; 
			}
			newLevel = curr.level + 1;
			for(String child : graph.get(curr.val)) {
		
				if(!visited.contains(child)) {
					QueueNode temp = new QueueNode(child, newLevel);
					queue.offer(temp);
					visited.add(child);

				}
			}

		}

		return shortest ;

	}

	//Inner class created to keep the both value and level in the queue
	static class QueueNode {
		int level;
		String val;

		QueueNode(String val, int level){
			this.val = val;
			this.level = level;
		}
	}

}

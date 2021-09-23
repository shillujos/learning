package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Write a function, undirectedPath, that takes in an array of edges
 *  for an undirected graph and two nodes (nodeA, nodeB). The function 
 *  should return a boolean indicating whether or not there exists a path between nodeA and nodeB.
 */
/**
 * Java implementation of Alvin's HasPath for an undirected graph
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 *
 */
public class HasPathUndirected {

	public static void main(String[] args) {

		ArrayList<ArrayList<String>> edges = new ArrayList<>();

		ArrayList<String> edgeOne = new ArrayList<>();
		edgeOne.add("i");
		edgeOne.add("j");

		ArrayList<String> edgeTwo = new ArrayList<>();
		edgeTwo.add("k");
		edgeTwo.add("i");

		ArrayList<String> edgeThree = new ArrayList<>();
		edgeThree.add("m");
		edgeThree.add("k");

		ArrayList<String> edgeFour = new ArrayList<>();
		edgeFour.add("k");
		edgeFour.add("l");

		ArrayList<String> edgeFive = new ArrayList<>();
		edgeFive.add("o");
		edgeFive.add("n");


		edges.add(edgeOne);
		edges.add(edgeTwo);
		edges.add(edgeThree);
		edges.add(edgeFour);
		edges.add(edgeFive);

		HashMap<String, ArrayList<String>> graph = createGraphFromEdges(edges);
		
		System.out.println("dfsRecursive(graph, \"j\", \"m\") "+dfsRecursive(graph, "j", "m", new HashSet<String>()));
		System.out.println("dfsRecursive(graph, \"m\", \"j\") " +dfsRecursive(graph, "m", "j", new HashSet<String>()));
		System.out.println("dfsRecursive(graph, \"l\", \"j\")" +dfsRecursive(graph, "l", "j", new HashSet<String>()));
		System.out.println("dfsRecursive(graph, \"k\", \"o\")" +dfsRecursive(graph, "k", "o", new HashSet<String>()));
		System.out.println("dfsRecursive(graph, \"i\", \"o\")" +dfsRecursive(graph, "i", "o", new HashSet<String>()));
		
		System.out.println("dfsIterative(graph, \"j\", \"m\") "+dfsIterative(graph, "j", "m", new HashSet<String>()));
		System.out.println("dfsIterative(graph, \"m\", \"j\") " +dfsIterative(graph, "m", "j", new HashSet<String>()));
		System.out.println("dfsIterative(graph, \"l\", \"j\")" +dfsIterative(graph, "l", "j", new HashSet<String>()));
		System.out.println("dfsIterative(graph, \"k\", \"o\")" +dfsIterative(graph, "k", "o", new HashSet<String>()));
		System.out.println("dfsIterative(graph, \"i\", \"o\")" +dfsIterative(graph, "i", "o", new HashSet<String>()));
		
		System.out.println("bfsIterative(graph, \"j\", \"m\") "+bfsIterative(graph, "j", "m", new HashSet<String>()));
		System.out.println("bfsIterative(graph, \"m\", \"j\") " +bfsIterative(graph, "m", "j", new HashSet<String>()));
		System.out.println("bfsIterative(graph, \"l\", \"j\")" +bfsIterative(graph, "l", "j", new HashSet<String>()));
		System.out.println("bfsIterative(graph, \"k\", \"o\")" +bfsIterative(graph, "k", "o", new HashSet<String>()));
		System.out.println("bfsIterative(graph, \"i\", \"o\")" +bfsIterative(graph, "i", "o", new HashSet<String>()));
	
	}

	/**
	 * Takes in edges and constructs an adjacency list out of it
	 * @param edges
	 * @return
	 */
	private static HashMap<String, ArrayList<String>> createGraphFromEdges(ArrayList<ArrayList<String>> edges) {
		//create adjacency list out of edges
		HashMap<String, ArrayList<String>> graph = new HashMap<>();
		for(ArrayList<String> edge : edges) {

			ArrayList<String> tmpOne = graph.getOrDefault(edge.get(0), new ArrayList<String>());
			tmpOne.add(edge.get(1));
			graph.put(edge.get(0), tmpOne);

			ArrayList<String> tmpTwo = graph.getOrDefault(edge.get(1), new ArrayList<String>());
			tmpTwo.add(edge.get(0));
			graph.put(edge.get(1), tmpTwo);

		}
		return graph;
	}

	/**
	 * BFS way to find existence of a path
	 * @param graph
	 * @param src
	 * @param dest
	 * @param visited
	 * @return
	 */
	private static boolean bfsIterative(HashMap<String, ArrayList<String>> graph, String src, String dest, HashSet<String> visited) {
		
		ArrayDeque<String> queue = new ArrayDeque<String>();
		queue.offer(src);
		visited.add(src);
		
		while(queue.size() > 0) {
			String curr = queue.poll();
			
			if(curr.equals(dest)) {
				return true;
			}
			
			for(String child : graph.get(curr)) {
				if(!visited.contains(child)) {
					queue.offer(child);
					visited.add(child);
				}
			}
		}
		
		return false;
	}

	/**
	 * DFS in iterative way to find existence of a path
	 * @param graph
	 * @param src
	 * @param dest
	 * @param visited
	 * @return
	 */
	private static boolean dfsIterative(HashMap<String, ArrayList<String>> graph, String src, String dest, HashSet<String> visited) {
		
		ArrayDeque<String> stack = new ArrayDeque<>();
		stack.push(src);
		visited.add(src);
		
		while(stack.size() > 0) {
			String curr = stack.pop();
			
			if(curr.equals(dest)) {
				return true;
			}
			
			for(String child : graph.get(curr)) {
				if(!visited.contains(child)) {
					stack.push(child);
					visited.add(child);
				}
			}
		}
		
		return false;
		
	}

	/**
	 * DFS in recursive way to find existence of a path
	 * @param graph
	 * @param src
	 * @param dest
	 * @param visited
	 * @return
	 */
	private static boolean dfsRecursive(HashMap<String, ArrayList<String>> graph, String src, String dest, HashSet<String> visited) {
		
		if(src.equals(dest)) {
			return true;
		}
		
		if(visited.contains(src)) {
			return false;
		}
		visited.add(src);
		for(String child : graph.get(src)) {
			if(dfsRecursive(graph, child, dest, visited)) {
				return true;
			}
		}
		
		return false;
	}

}

package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/*
 * Write a function, hasPath, that takes in an object
 * representing the adjacency list of a directed acyclic graph 
 * and two nodes (src, dst). The function should return 
 * a boolean indicating whether or not there 
 * exists a directed path between the source and destination nodes.
 */
/**
 * Java Implementation of Alvin's HasPath for a directed graph
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 *
 */
public class HasPathDirected {

	public static void main(String[] args) {
		
		HashMap<String,String[]> graph = new HashMap<>();
		
		graph.put("f", new String[] {"g","i"});
		graph.put("g", new String[] {"h"});
		graph.put("h", new String[] {});
		graph.put("i", new String[] {"g","k"});
		graph.put("j", new String[] {"i"});
		graph.put("k", new String[] {});
	
		System.out.println("dfsRecursive(graph, \"f\", \"k\") "+dfsRecursive(graph, "f", "k"));
		System.out.println("dfsRecursive(graph, \"f\", \"j\") " +dfsRecursive(graph, "f", "j"));
		System.out.println("dfsRecursive(graph, \"i\", \"h\")" +dfsRecursive(graph, "i", "h"));
		
		System.out.println("dfsIterative(graph, \"f\", \"k\") "+dfsIterative(graph, "f", "k"));
		System.out.println("dfsIterative(graph, \"f\", \"j\") " +dfsIterative(graph, "f", "j"));
		System.out.println("dfsIterative(graph, \"i\", \"h\")" +dfsIterative(graph, "i", "h"));
		
		System.out.println("bfsIterative(graph, \"f\", \"k\") "+bfsIterative(graph, "f", "k"));
		System.out.println("bfsIterative(graph, \"f\", \"j\") " +bfsIterative(graph, "f", "j"));
		System.out.println("bfsIterative(graph, \"i\", \"h\")" +bfsIterative(graph, "i", "h"));

		
		graph = new HashMap<>();
		
		graph.put("v", new String[] {"x","w"});
		graph.put("w", new String[] {});
		graph.put("x", new String[] {});
		graph.put("y", new String[] {"z"});
		graph.put("z", new String[] {});
		
		System.out.println(dfsRecursive(graph, "v", "w"));
		System.out.println(dfsRecursive(graph, "v", "z"));
		
		System.out.println(dfsIterative(graph, "v", "w"));
		System.out.println(dfsIterative(graph, "v", "z"));
		
		System.out.println(bfsIterative(graph, "v", "w"));
		System.out.println(bfsIterative(graph, "v", "z"));

	}

	/**
	 * BFS implementation to check for a path
	 * @param graph
	 * @param source
	 * @param dest
	 * @return
	 */
	private static boolean bfsIterative(HashMap<String, String[]> graph, String source, String dest) {
		Deque<String> queue = new ArrayDeque<>();
		queue.offer(source);
		
		while(queue.size() > 0) {
			String curr = queue.poll();
			if(curr == dest) {
				return true;
			}
			for(String child : graph.get(curr)) {
				queue.offer(child);
			}
		}
		
		return false;
	}

	/**
	 * Iterative implementation of DFS using a stack, to find existence of a path
	 * @param graph
	 * @param source
	 * @param dest
	 * @return
	 */
	private static boolean dfsIterative(HashMap<String, String[]> graph, String source, String dest) {
		
		Deque<String> stack = new ArrayDeque<>();
		stack.push(source);
		
		while(stack.size() > 0) {
			String curr = stack.pop();
			if(curr == dest) {
				return true;
			}
			for(String child : graph.get(curr)) {
				stack.push(child);
			}
		}
		
		return false;
	}

	/**
	 * Recursive implementation of DFS using a queue, to find existence of a path
	 * @param graph
	 * @param source
	 * @param dest
	 * @return
	 */
	private static boolean dfsRecursive(HashMap<String, String[]> graph, String source, String dest) {
		
		if(source == dest)
			return true;
		
		for(String child : graph.get(source)) {
			if(dfsRecursive(graph, child, dest)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	

}

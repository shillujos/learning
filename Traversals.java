package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Implementation of various algorithms to traverse a graph
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 * 
 */
public class Traversals {

	public static void main(String[] args) {
		HashMap<String, String[]> graph = new HashMap<>();
		
		graph.put("a", new String[] {"b","c"});
		graph.put("b", new String[] {"d"});
		graph.put("c", new String[] {"e"});
		graph.put("d", new String[] {"f"});
		graph.put("e", new String[] {});
		graph.put("f", new String[] {});
		
		dfsIterative(graph, "a");
		
		bfsIterative(graph, "a");
		dfsRecursive(graph, "a");
	}
	

	/**
	 * Recursive implementation of DFS
	 * @param graph
	 * @param source
	 */
	private static void dfsRecursive(HashMap<String, String[]> graph, String source) {
		System.out.print(source +"\s");
		
		for(String child : graph.get(source)) {
			dfsRecursive(graph, child);
		}
	}

	/**
	 * Iterative implementation of DFS using stack
	 * @param graph
	 * @param source
	 */
	private static void dfsIterative(HashMap<String, String[]> graph, String source) {
		Deque<String> stack = new ArrayDeque<>();
		stack.push(source);
		
		while(stack.size() > 0) {
			String current = stack.pop();
			System.out.print(current +"\s" );
			
			for(String child : graph.get(current)) {
				stack.push(child);
			}
		}
		System.out.println();
	}
	
	/**
	 * Iterative implementation of BFS using a queue
	 * @param graph
	 * @param source
	 */
	private static void bfsIterative(HashMap<String, String[]> graph, String source) {
		Deque<String> queue = new ArrayDeque<String>();
		queue.offer(source);
		
		while(queue.size() > 0) {
			String current = queue.poll();
			System.out.print(current + " ");
			for(String child : graph.get(current)) {
				queue.offer(child);
			}
		}
		System.out.println();
	}
	


}

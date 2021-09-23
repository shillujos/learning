package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Write a function, connectedComponentsCount, that takes in the adjacency list 
 * of an undirected graph. The function should return the number of connected components within the graph.
 */
/**
 * Java implementation of Alvin's ConnectedComponents
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 * 
 */
public class ConnectedComponents {

	public static void main(String[] args) {
		HashMap<String, String[]> test00 = new HashMap<>();


		test00.put("0", new String[] {"8","1","5"});
		test00.put("1", new String[] {"0"});
		test00.put("5", new String[] {"0","8"});
		test00.put("8", new String[] {"0","5"});
		test00.put("2", new String[] {"3","4"});
		test00.put("3", new String[] {"2","4"});
		test00.put("4", new String[] {"3","2"});
		
		System.out.println("Test00::"+countConnectedComponents(test00));
		
		//case 01
		HashMap<String, String[]> test01 = new HashMap<>();
		test01.put("1", new String[] {"2"});
		test01.put("2", new String[] {"1","8"});
		test01.put("6", new String[] {"7"});
		test01.put("9", new String[] {"8"});
		test01.put("7", new String[] {"6","8"});
		test01.put("8", new String[] {"9","7","2"});
		System.out.println("Test01::"+countConnectedComponents(test01));
		
		//Case 02
		HashMap<String, String[]> test02 = new HashMap<>();
		test02.put("3", new String[] {});
		test02.put("4", new String[] {"6"});
		test02.put("6", new String[] {"4","5","7","8"});
		test02.put("8", new String[] {"6"});
		test02.put("7", new String[] {"6"});
		test02.put("5", new String[] {"6"});
		test02.put("1", new String[] {"2"});
		test02.put("2", new String[] {"1"});
		
		System.out.println("Test02::"+countConnectedComponents(test02));
		
		//case 03
		HashMap<String, String[]> test03 = new HashMap<>();
	
		System.out.println("Test03::"+countConnectedComponents(test03));

		//case 04
		HashMap<String, String[]> test04 = new HashMap<>();
		test04.put("0", new String[] {"4","7"});
		test04.put("1", new String[] {});
		test04.put("2", new String[] {});
		test04.put("3", new String[] {"6"});
		test04.put("4", new String[] {"0"});
		test04.put("6", new String[] {"3"});
		test04.put("7", new String[] {"0"});
		test04.put("8", new String[] {});
		
		System.out.println("Test04::"+countConnectedComponents(test04));
	}

	/**
	 * Iterate through each node and do a DFS/BFS
	 * @param graph
	 * @return
	 */
	private static int countConnectedComponents(HashMap<String, String[]> graph) {
		int count = 0; //connected components
		//check if the key is in visited set, if true, add 1 to count
		//for each key, add its children to visited
		HashSet<String> visited = new HashSet<>();
		for(String key : graph.keySet()) {
			//DFS mode of getting count
			/*if(dfs(graph, key, visited)) {
				count++;
			} */
			

			//BFS way of getting count
			if(!visited.contains(key)) {
				count++;
				bfs(graph, key, visited);
			}
			
		}
		return count;
	}

	/**
	 * DFS way to find count of connected components
	 * @param graph
	 * @param key
	 * @param visited
	 * @return
	 */
	private static boolean dfs(HashMap<String, String[]> graph, String key, HashSet<String> visited) {
		if(visited.contains(key)) {
			return false;
		}
		visited.add(key);
		for(String child : graph.get(key)) {
			
			dfs(graph, child, visited);
		}
		return true;
	}
	
	/**
	 * BFS way to find count of connected components
	 * @param graph
	 * @param key
	 * @param visited
	 */
	private static void bfs(HashMap<String, String[]> graph, String key, HashSet<String> visited) {
		ArrayDeque<String> queue = new ArrayDeque<String>();
		
		queue.offer(key);
		visited.add(key);
		
		while(queue.size() > 0) {
			String curr = queue.poll();
			for(String child : graph.get(curr)) {
				if(!visited.contains(child)) {
					queue.offer(child);
					visited.add(child);
				}
			}
		}
	}

}

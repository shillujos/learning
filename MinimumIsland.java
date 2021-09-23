package com.personal.algo.graphtraversal;

import java.util.HashSet;

/*
 * Write a function, minimumIsland, that takes in a grid containing Ws and Ls. 
 * W represents water and L represents land. The function should return the 
 * size of the smallest island. An island is a vertically or horizontally connected region of land.
 * You may assume that the grid contains at least one island.
 */
/**
 * Java implementation of Alvin's graph traversal course - MinimumIsland
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 *
 */
public class MinimumIsland {

	public static void main(String[] args) {

		//Test 00
		String[][] test00 = {
				{"W", "L", "W", "W", "W"},
				{"W", "L", "W", "W", "W"},
				{"W", "W", "W", "L", "W"},
				{"W", "W", "L", "L", "W"},
				{"L", "W", "W", "L", "L"},
				{"L", "L", "W", "W", "W"}
		};
		System.out.println("Test 00 DFS::"+minimumIsland(test00));

		//Test 01
		String[][] test01 = {
				{"L", "W", "W", "L", "W"},
				{"L", "W", "W", "L", "L"},
				{"W", "L", "W", "L", "W"},
				{"W", "W", "W", "W", "W"},
				{"W", "W", "L", "L", "L"}
		};
		System.out.println("Test 01 DFS::"+minimumIsland(test01));

		//Test 02
		String[][] test02 = {
				{"L", "L", "L"},
				{"L", "L", "L"},
				{"L", "L", "L"}
		};
		System.out.println("Test 02 DFS::"+minimumIsland(test02));

		//Test 03
		String[][] test03 = {
				{"W", "W"},
				{"L", "L"},
				{"W", "W"},
				{"W", "L"}
		};

		System.out.println("Test 03 DFS::"+minimumIsland(test03));
	}

	/**
	 * Count the smallest island
	 * @param grid
	 * @return
	 */
	private static int minimumIsland(String[][] grid) {
		HashSet<String> visited = new HashSet<>();
		int minCount = Integer.MAX_VALUE;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				int count = exploreGridDFS(row, col, grid, visited);
				if(count > 0) {
					minCount = Math.min(minCount, count);
				}
			}
		}
		return minCount;
	}

	/**
	 * DFS way to explore the graph
	 * @param row
	 * @param col
	 * @param grid
	 * @param visited
	 * @return
	 */
	private static int exploreGridDFS(int row, int col, String[][] grid, HashSet<String> visited) {
		int count = 0; 
		//base case of valid boundary
		boolean rowInBounds = row >= 0 && row < grid.length;
		boolean colInBounds = col >= 0 && col < grid[0].length;
		
		if(!rowInBounds || !colInBounds) {
			return count;
		}
		
		//skip if water
		if(grid[row][col].equals("W")) {
			return count;
		}
		
		//skip if already visited
		String pos = row + ","+col;
		if(visited.contains(pos)) {
			return count;
		}
		
		visited.add(pos);
		count++;
		
		count += exploreGridDFS(row+1, col, grid, visited);
		count += exploreGridDFS(row-1, col, grid, visited);
		count += exploreGridDFS(row, col+1, grid, visited);
		count += exploreGridDFS(row, col-1, grid, visited);
		
		return count;
	}

}

package com.jm.assignment;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MaxTokenFriends {
	public static void main(String[] args) throws IOException {
		Scanner src = new Scanner(System.in);
		MaxTokenFriends object = new MaxTokenFriends();
		System.out.println("Enter number on nodes and edges");
		int nodes = src.nextInt();
		int edges = src.nextInt();
		object.validateNodesEdges(nodes, edges);
		System.out.println("Enter from node, to node and node weight");
		int[] friends_from = new int[edges];
		int[] friends_to = new int[edges];
		int[] friends_weight = new int[edges];
		for (int i = 0; i < edges; i++) {
			friends_from[i] = src.nextInt();
			friends_to[i] = src.nextInt();
			friends_weight[i] = src.nextInt();
			object.validateWeight(friends_from[i], friends_to[i], friends_weight[i], nodes, edges);
		}
		src.close();
		object.maxTokens(nodes, friends_from, friends_to, friends_weight);
	}

	/**
	 * This method takes input edges and respective weights in input.<br>
	 * First it forms map of token ownership that every node contains. Then it
	 * makes a map of shared token representing token is shared by which
	 * nodes.<br>
	 * Then it constructs a map of all the pairs and the count of tokens that
	 * they share, which is used further to compute the maximum product of the
	 * same.
	 * 
	 * @param friends_nodes
	 * @param friends_from
	 * @param friends_to
	 * @param friends_weight
	 */
	private void maxTokens(int friends_nodes, int[] friends_from, int[] friends_to, int[] friends_weight) {
		Map<Entry<Integer, Integer>, Integer> map = new HashMap<Entry<Integer, Integer>, Integer>();
		Map<Integer, HashSet<Integer>> tokenOwnership = new HashMap<Integer, HashSet<Integer>>();
		Map<Integer, HashSet<Integer>> sharedTokens = new HashMap<Integer, HashSet<Integer>>();

		for (int i = 0; i < friends_from.length; i++) {
			int from = friends_from[i];
			int to = friends_to[i];
			int weight = friends_weight[i];
			if (tokenOwnership.get(from) == null) {
				tokenOwnership.put(from, new HashSet<Integer>());
			}
			tokenOwnership.get(from).add(weight);

			if (tokenOwnership.get(to) == null) {
				tokenOwnership.put(to, new HashSet<Integer>());
			}
			tokenOwnership.get(to).add(weight);

			if (sharedTokens.get(weight) == null) {
				sharedTokens.put(weight, new HashSet<Integer>());
			}
			sharedTokens.get(weight).add(from);
			sharedTokens.get(weight).add(to);

		}
		System.out.println("Token ownership: " + tokenOwnership);
		System.out.println("Shared tokens: " + sharedTokens);

		for (int i = 1; i <= friends_nodes; i++) {
			for (int j = 1; j <= friends_nodes; j++) {
				if (i != j) {
					int count = 0;
					if (tokenOwnership.containsKey(i)) {
						HashSet<Integer> allTokens = tokenOwnership.get(i);
						for (Integer token : allTokens) {
							if (sharedTokens.get(token).contains(j)) {
								count++;
							}
						}
						map.put(new AbstractMap.SimpleEntry<Integer, Integer>(i, j), count);
					}
				}
			}
		}
		int maxProduct = 0;
		int maxTokens = 0;
		for (Map.Entry<Map.Entry<Integer, Integer>, Integer> entry : map.entrySet()) {
			if (entry.getValue() >= maxTokens) {
				maxTokens = entry.getValue();
				Map.Entry<Integer, Integer> pair = entry.getKey();
				int product = pair.getKey() * pair.getValue();
				if (product >= maxProduct) {
					maxProduct = product;
				}
			}
		}
		System.out.println("Maximum product is: " + maxProduct);
	}

	/**
	 * Method to validate number of nodes and edges
	 * 
	 * @param nodes
	 * @param egdes
	 * @throws IOException
	 */
	public void validateNodesEdges(int nodes, int egdes) throws IOException {
		if (nodes < 2 || nodes > 100) {
			throw new IOException("Invalid input nodes");
		}

		if (egdes < 1 || egdes > Math.min(200, (nodes * (nodes - 1)) / 2)) {
			throw new IOException("Invalid number of edgese");
		}
	}

	/**
	 * Method to validate input of weight
	 * 
	 * @param fromNode
	 * @param toNode
	 * @param weight
	 * @param nodes
	 * @param edges
	 * @throws IOException
	 */
	public void validateWeight(int fromNode, int toNode, int weight, int nodes, int edges) throws IOException {
		if (fromNode == toNode) {
			throw new IOException("Invalid input: from node and to node cannot be same");
		}
		if (fromNode < 1 || fromNode > nodes || toNode < 1 || toNode > nodes) {
			throw new IOException("Invalid input of from node and to node");
		}
		if (weight < 1 || weight > 100 || weight > edges) {
			throw new IOException("Invalid input of weight");
		}
	}

}

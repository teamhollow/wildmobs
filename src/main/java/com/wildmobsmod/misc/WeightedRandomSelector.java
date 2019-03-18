package com.wildmobsmod.misc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class WeightedRandomSelector<T> {
	private final T[] values;
	private final LoadedDice dice;
	
	/**
	 * O(n) construction time, O(1) fetch time. Uses {@link LoadedDice}.
	 */
	public WeightedRandomSelector(T[] values, double[] probabilities) {
		if(values == null || probabilities == null) {
			throw new NullPointerException();
		}
		if(values.length != probabilities.length) {
			throw new IllegalArgumentException("Value and probability arrays must have the same length!");
		}
		this.values = values;
		dice = new LoadedDice(probabilities);
	}
	
	public T next() {
		return values[dice.next()];
	}
	
	/**
	 * Based on an alias method implementation by Keith Schwarz using Vose's algorithm.<br>
	 * See {@link http://www.keithschwarz.com/darts-dice-coins/}<br>
	 * O(n) construction time, O(1) fetch time.
	 */
	public static class LoadedDice {
	    private final Random random;
	    private final int[] alias;
	    private final double[] chance;

	    public LoadedDice(double[] probabilities) {
	        this(probabilities, new Random());
	    }
	    
	    public LoadedDice(double[] probabilities, Random random) {
	    	// sanity checks
	        if(probabilities == null || random == null) {
	        	throw new NullPointerException();
	        }
	        final int size = probabilities.length;
	        if(size == 0) {
	        	throw new IllegalArgumentException("Cannot create a 0-sided dice! (probability array may not be empty)");
	        }
	        // variable/field preparation
	        chance = new double[size];
	        alias = new int[size];
	        this.random = random;
	        final double average = 1.0D / size; // assumes uniform probability distribution
	        probabilities = probabilities.clone(); // protect the source array from changes; cloning is safe on primitive arrays
	        final Deque<Integer> small = new ArrayDeque<Integer>();
	        final Deque<Integer> large = new ArrayDeque<Integer>();
	        // partition probabilities
	        for(int i = 0; i < size; ++i) {
	        	(probabilities[i] >= average ? large : small).add(i);
	        }
	        // build chance and alias arrays
	        while(!small.isEmpty() && !large.isEmpty()) {
	            int less = small.removeLast();
	            int more = large.removeLast();
	            double low = probabilities[less];
	            chance[less] = low * size;
	            alias[less] = more;
	            double high = (probabilities[more] + low) - average;
	            probabilities[more] = high;
	            (high >= average ? large : small).add(more);
	        }
	        // clean up; handles chance columns with no alias
	        while(!small.isEmpty()) {
	        	chance[small.removeLast()] = 1.0D;
	        }
	        while(!large.isEmpty()) {
	        	chance[large.removeLast()] = 1.0D;
	        }
	    }

	    public int next() {
	        int column = random.nextInt(chance.length);
	        boolean coin = random.nextDouble() < chance[column];
	        return coin ? column : alias[column];
	    }
	}
}

package com.FibonacciGenerator;

import android.graphics.Point;
import android.view.Display;

import java.lang.IllegalArgumentException;import java.lang.Integer;import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darien on 2/9/2015.
 */
public class FibonacciGenerator {
    private int overflowIndex = Integer.MAX_VALUE;
    private List<Long> cache = new ArrayList<Long>(){{
        add((long)0);
        add((long)1);
    }};

    public FibonacciGenerator(int screenHeight) {
        getFibonacci(screenHeight/100);
    }

    public long getFibonacci(int i){
        long result;
        if(i<0) throw new IllegalArgumentException("You cannot search for a negative Fibonacci index.");
        else if(i<cache.size()) result = cache.get(i);
        else{
            result = getFibonacci(i-1)+getFibonacci(i-2);
            if(overflowIndex==Integer.MAX_VALUE && result<0) overflowIndex = i;
            cache.add(result);
        }
        return result;
    }

    public int getOverflowIndex() {
        return overflowIndex;
    }

    public List<Long> getCache(){
        return cache;
    }
}

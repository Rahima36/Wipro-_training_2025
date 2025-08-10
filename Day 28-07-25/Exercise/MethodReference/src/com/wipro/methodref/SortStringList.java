package com.wipro.methodref;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringList {
	public static void main(String[] args) {
		
        List<String> li = Arrays.asList("Shubham", "Anand", "Sham", "Tejas", "Prashant");

        
        List<String> sortedList = li.stream()
                                       .sorted(String::compareTo)
                                       .collect(Collectors.toList());

       System.out.println(sortedList);
		
	}
	
}

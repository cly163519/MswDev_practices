import java.io.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class FunWithWords {
	public static void main(String[] args) throws Exception {

		//Use arrayList to remove duplicates
		long t1 = System.currentTimeMillis();
		List<String> list = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("100k-AnneOfGreenGables.txt"));
			while(scanner.hasNext()) {
				String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
				if(!list.contains(word)) {
					list.add(word);
				}
			}
			scanner.close();
		}catch(IOException e) {
			
		}
		long t2 = System.currentTimeMillis();
        System.out.println("ArrayList unique words: " + list.size() + ", time = " + (t2 - t1) + " ms");
        
        //Use set to remove duplicates
        long t3 = System.currentTimeMillis();
        Set<String> set = new HashSet<>();
        try {
        	Scanner scanner = new Scanner(new File("100k-AnneOfGreenGables.txt"));
        	while(scanner.hasNext()) {
        		String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
        		set.add(word);
        	}
        	scanner.close();
        }catch(IOException e) {
        	
        }
        long t4 = System.currentTimeMillis();
        System.out.println("HashSet unique words: " + set.size() + ", time = " + (t4 - t3) + " ms");
        
        //Use hashMap to count how many time the word appears
        long t5 = System.currentTimeMillis();
        Map<String, Integer> map = new HashMap<>();
        try {
        	Scanner scanner = new Scanner(new File("100k-AnneOfGreenGables.txt"));
        	while(scanner.hasNext()) {
        		String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
        		map.put(word, map.getOrDefault(word, 0) + 1);
        	}
        	scanner.close();
        }catch(IOException e) {
        	
        }
        long t6 = System.currentTimeMillis();
        System.out.println("HashMap unique words: " + map.size() + ", time = " + (t6 - t5) + " ms");
        
        System.out.println("First 10 words: ");

        //不知道下面这段什么用法
//        map.entrySet().stream()
//           .sorted((a, b) -> b.getValue() - a.getValue())
//           .limit(10)
//           .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
        
        //这是什么用法?
        System.out.println("Using a comparator to sort the outputs alphabetically: ");
        map.entrySet().stream()
           .sorted(Comparator.comparing(Map.Entry::getKey))  // 按 key (单词) 排序
           .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
        //这是什么用法?
        System.out.println("Sort by number of occurrences: ");
        map.entrySet().stream()
           .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // 按 value 倒序
           .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
       // 这是什么用法?
        System.out.println("Bar chart of the top 10 most common words: ");
        map.entrySet().stream()
           .sorted((a, b) -> b.getValue() - a.getValue())
           .limit(10)
           .forEach(e -> {
               String word = e.getKey();
               int count = e.getValue();
               String bar = "*".repeat(Math.min(count, 50)); // 最多画50个星号
               System.out.printf("%-10s | %s (%d)%n", word, bar, count);
           });


	}
}
	
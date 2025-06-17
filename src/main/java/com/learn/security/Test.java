package com.learn.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test{
	
	public static void main(String[] args){
		
		Path.of("E:\\SPRING_PROJECT\\spring-security\\test\\");
		try {
			name();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	public static void name() throws IOException {
		Path filePath = Path.of("E:\\SPRING_PROJECT\\spring-security\\test\\");
		
		
		
		 Map<String, Long> collect = Files.walk(filePath)
		   .filter(path -> Files.isRegularFile(path))
		   .flatMap(t -> {
			try {
				return Files.lines(t);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Stream.empty();
		})
		   //.filter(Objects::nonNull)
		   .flatMap(txt -> Arrays.asList(txt.split(" ")).stream())
		   .collect(Collectors.groupingBy(txt-> txt,Collectors.counting()));
		 //  .forEach(System.out::println);
		 
		// collect.forEach((k,v)-> System.out.printf("Key : %s | value : %s", k,v));
		 
		 System.out.println(collect);
		 
		 Map<Long, List<String>> collect2 = collect.entrySet()
		 .stream()
		 .collect(
				  Collectors.groupingBy(Map.Entry::getValue,Collectors.mapping(Map.Entry::getKey, Collectors.toList()))
				  );
		 
		 System.out.println(collect2);
	}

}

package compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Compiler {

	public static void main(String[] args) throws IOException 
	{
		try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) 
		{
		    paths.filter(Files::isRegularFile).forEach(System.out::println);
		}
	}

}

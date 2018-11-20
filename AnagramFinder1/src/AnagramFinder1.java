
import java.io.FileNotFoundException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnagramFinder1 {

	public static void main(String[] args) 
	{		
		Scanner userInputScanner = new Scanner(System.in); 
		Path path;
		try
        {
            System.out.println("Welcome to the Anagram Finder");
            System.out.println("-----------------------------");
            
            Path currentDir = Paths.get(".");
            path = currentDir.resolve(args[0]);                       
            
            long startTime = System.nanoTime();

            List<String> logFile = Files.readAllLines(path);
            
            long endTime = System.nanoTime();
            long timeElapsed = (endTime - startTime) / 1000000;

            System.out.println("Dictionary loaded in " + timeElapsed + " ms");
            
            System.out.print("AnagramFinder>");            
            String userInput = userInputScanner.nextLine(); 
            

            while (!userInput.equals("exit"))
            {
                String orderUserInput = sortString(userInput);
                List<String> resultList = new ArrayList<String>();

                startTime = System.nanoTime();

                for(String i : logFile)
                {
                    String elementToAdd = sortString(i);
                    if (elementToAdd.equals(orderUserInput))
                        resultList.add(i);
                }
                endTime = System.nanoTime();
                timeElapsed = (endTime - startTime) / 1000000;

                if (resultList != null && resultList.size() > 0)
                {
                    System.out.println(resultList.size() + " Anagrams found for " + userInput + " in " + timeElapsed + "ms");
                    System.out.println(String.join(",", resultList));
                }
                else
                {
                    System.out.println("No anagrams found for " + userInput + " " + timeElapsed + "ms");
                }
                System.out.print("AnagramFinder>");
                userInput = userInputScanner.nextLine();
            }
        }
        catch (FileNotFoundException fne)
        {
            System.out.println("ERROR: Could not find the dictionary file!");
            //System.console().readLine();
        }
        catch (Exception e)
        {
            System.out.println("ERROR: Unexpected Error!");
            //System.console().readLine();
        }
		finally {
		    if(userInputScanner!=null)
		    	userInputScanner.close();
		}
	}

	public static String sortString(String strToSort) 
    { 
        char tempCharArray[] = strToSort.toCharArray(); 
        Arrays.sort(tempCharArray); 
        return new String(tempCharArray); 
    } 
}

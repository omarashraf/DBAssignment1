package indexing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import Exceptions.DBAppException;

public class indexCreation {
	
	static String metadata = "metadata";
	
	public static void createIndex(String strTableName, String strColName) throws DBAppException
	{
		final String COMMA_DELIMITER = ",";
		BufferedReader fileReader = null;
		FileWriter fileWriter = null;
		try
		{
			fileReader = new BufferedReader(new FileReader(metadata));
		}
		catch(Exception e)
		{
			System.out.print("There is an error reading from the metadata file");
		}
		
		
		try
		{
			List<String> tmp = new ArrayList();
			String line = "";
			while ((line = fileReader.readLine()) != null)
			{
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0)
				{
					if(tokens[0].equals(strTableName) && tokens[1].equals(strColName))
					{
						//line.replace(tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+tokens[4]+","+tokens[5],
							//	tokens[0]+","+tokens[1]+","+tokens[2]+","+tokens[3]+","+"true"+","+tokens[5]);
						//line.concat("omar");
						//System.out.print(line);
						tmp.add(tokens[0]);
						tmp.add(tokens[1]);
						tmp.add(tokens[2]);
						tmp.add(tokens[3]);
						tmp.add("TRUE");
						tmp.add(tokens[5]);
					}
					else
					{
						tmp.add(tokens[0]);
						tmp.add(tokens[1]);
						tmp.add(tokens[2]);
						tmp.add(tokens[3]);
						tmp.add(tokens[4]);
						tmp.add(tokens[5]);
					}
				}
			}
			/*for(Object element : tmp)
			{
				System.out.println(element);
			}*/
			
			
			final String NEW_LINE_SEPARATOR = "\n";
			fileWriter = new FileWriter(metadata);
			try
			{
				
				while(!tmp.isEmpty())
				{
					
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tmp.remove(0));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
					continue;
				}
				System.out.print("mafish moshkela!");
			}
			catch(Exception e)
			{
				System.out.print("fy moshkela");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}
		finally
	    {
	    	try
	    	{
	    		fileWriter.flush();
	    		fileWriter.close();
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println("Error while flushing/closing fileWriter !!!");
	    		e.printStackTrace();
	    	}
	    }
	}
	
}

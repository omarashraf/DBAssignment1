package tableManipulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import Exceptions.DBAppException;

public class insertion {
	
	static String serFile = "records.ser";
	static String metadata = "metadata";
	
	public static boolean tableExists(String strTableName) {
		BufferedReader fileReader = null;
		String COMMA_DELIMITER = ",";
		
		try
		{
			fileReader = new BufferedReader(new FileReader(metadata));
		}
		catch(Exception e)
		{
			
			return false;
			
		}
		
		try
		{
			String line = "";
			while ((line = fileReader.readLine()) != null)
			{
				
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0)
				{
					if(tokens[0].equals(strTableName))
					{
						return true;
					}
				}
			}
		} 
		catch(Exception e)
		{
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean colExists(String strTableName , String Colname) {
		BufferedReader fileReader = null;
		String COMMA_DELIMITER = ",";
		
		try
		{
			fileReader = new BufferedReader(new FileReader(metadata));
		}
		catch(Exception e)
		{
			
			return false;
			
		}
		
		try
		{
			String line = "";
			while ((line = fileReader.readLine()) != null)
			{
				
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0)
				{
					if(tokens[0].equals(strTableName))
					{
						if (tokens[1].equals(Colname))
						return true;
					}
				}
			}
		} 
		catch(Exception e)
		{
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}
		return false;
	}
	
	public static void insertIntoTable(String strTableName, Hashtable<String, String> htblColNameValue) 
	throws DBAppException, IOException
	// still need to check if the value inserted is of the same type as the column, 
	// and set the max number of records in a file
	// search for a way to keep track of each record inserted to be able to retrieve it 
	{
		if (!tableExists(strTableName)) 
		{
			System.out.println("Table doesn't exist");
		}
		else 
		{
	    Enumeration e1 = htblColNameValue.keys();
		List<String> Colname = new ArrayList();
		List<String> Value = new ArrayList();
		while (e1.hasMoreElements()) 
			{
				String key = (String) e1.nextElement();
				  if (!colExists(strTableName, key))
				  {
					  System.out.println("Invalid column name" + key);
					  return;  
				  }
			      Colname.add(key);
			      Value.add(htblColNameValue.get(key));
		    }
		try 
		{
		FileOutputStream fileOut = new FileOutputStream(serFile,true);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 for (int i = 0; i < Colname.size(); i++)
    	{
			out.writeObject(Colname.get(i));
			out.writeObject(Value.get(i));
    	}
		 out.close();
		 fileOut.close();
		}
		catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
	}
	public static void retrieve() throws IOException, ClassNotFoundException{
		
	         
	         String Colname = ""; 
	         ObjectInputStream in = null;
	         try {
	        	 FileInputStream fileIn = new FileInputStream(serFile);
		         in = new ObjectInputStream(fileIn);
	         while(true) 
	     	{
	        	 
	        		 Colname = (String)in.readObject();
	 	 			System.out.println(Colname);
	        	 
	     	}
	         }
	         catch (Exception e) {
	        	 
	        		 System.out.println("File is Empty");
	         }
	         finally {
	         
	        	 if (in != null)
	                 in.close();
	         }
	 }
	
}

package tableManipulation;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
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
import java.util.NoSuchElementException;

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
	// still need to prevent duplicating keys, 
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
		
		ObjectOutputStream out = null;
		
		try 
			{
			File file = new File(serFile);
			if (!file.exists()) {
			FileOutputStream fileOut = new FileOutputStream(serFile,true);
			out = new ObjectOutputStream(fileOut);
			}
			else {
				FileOutputStream fileOut = new FileOutputStream(serFile,true);
				out = new AppendingObjectOutputStream(fileOut);
			}
		
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
			      Record x = new Record(key,htblColNameValue.get(key));
			      out.writeObject(x);
		    }
		
			} catch (IOException ioException) {
		        ioException.printStackTrace();
		      } catch (NoSuchElementException noSuchElementException) {
		         System.err.println("Invalid input.");
		      } finally {
		         try {
		            if (out != null)
		               out.close();
		         } catch (IOException ioException) {
		            System.err.println("Error closing file.");
		         }
		      }
		}
		
		
	
	}
	
	public static List<Record> retrieve() {
		List<Record> list= new ArrayList<Record>();
		ObjectInputStream inputStream = null;
		try {
	         inputStream = new ObjectInputStream(new FileInputStream(serFile));
	         while (true) {
	        	 
	             Record r = (Record) inputStream.readObject();
	             list.add(r);
	          }
	       } catch (EOFException eofException) {
	    	   return list;
	       } catch (ClassNotFoundException classNotFoundException) {
	          System.err.println("Object creation failed.");
	       } catch (IOException e) {
	          e.printStackTrace();
	       } finally {
	          try {
	             if (inputStream != null)
	                inputStream.close();
	          } catch (IOException ioException) {
	             System.err.println("Error closing file.");
	          }
	       }
	       return list;
	}
}

package mainClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import Exceptions.DBAppException;
import Exceptions.DBEngineException;

public class DBApp {
	
	static boolean validTableName = true;
	static boolean metadataIsCreated = false;
	
	public void init()
	{
		
	}
	
	public static void createTableHelper(String strTableName)
	{
		validTableName = true;
		BufferedReader fileReader = null;
		String COMMA_DELIMITER = ",";
		
		try
		{
			fileReader = new BufferedReader(new FileReader("metadata"));
		}
		catch(Exception e)
		{
			
			return;
			
		}
		
		try
		{
			String line = "";
			
			
			fileReader.readLine();
			
			while ((line = fileReader.readLine()) != null)
			{
				
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0)
				{
					if(tokens[0].equals(strTableName))
					{
						
						validTableName = false;
					}
				}
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
				fileReader.close();
			}
			catch(IOException e)
			{
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}
	}
	
	public static void createTable(String strTableName, Hashtable<String, String> htblColNameType, //remove static when not needed 
			Hashtable<String, String> htblColNameRefs, String strKeyColName) throws DBAppException
	{
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		final String FILE_HEADER = "TableName,ColumnName,ColumnType,Key,Indexed,References";
		
		createTableHelper(strTableName);
		//System.out.print(metadataIsCreated);
		Enumeration e1 = htblColNameType.keys();
		List<String> l1 = new ArrayList();
		List<String> l3 = new ArrayList();
	    while (e1.hasMoreElements()) {
	      String key = (String) e1.nextElement();
	      //System.out.println(key + " : " + htblColNameType.get(key));
	      l1.add(key);
	      l3.add(htblColNameType.get(key));
	    }
	    
	    
	    	    
	    Enumeration e2 = htblColNameRefs.keys();
	    List<String> l2 = new ArrayList();
	    List<String> l4 = new ArrayList();
	    while (e2.hasMoreElements()) {
	      String key = (String) e2.nextElement();
	      //System.out.println(key + " : " + htblColNameRefs.get(key));
	      l2.add(key);
	      l4.add(htblColNameRefs.get(key));
	    }
	    
	    FileWriter fileWriter = null;
	    
	    if(validTableName)
	    {
	    	try
		    {
		    	
		    	fileWriter = new FileWriter("metadata", true);
		    	metadataIsCreated = true;
		    	//System.out.print(metadataIsCreated);
		    	
		    	while(!htblColNameType.isEmpty())
		    	{
		    		fileWriter.append(strTableName);
			    	fileWriter.append(COMMA_DELIMITER);
			    	
			    	String col = l1.get(0);
			    	if(!l1.isEmpty())
			    		fileWriter.append(l1.remove(0));
			    	
			    	fileWriter.append(COMMA_DELIMITER);
			    	
			    	if(!l3.isEmpty())
			    		fileWriter.append(l3.remove(0));
			    	
			    	fileWriter.append(COMMA_DELIMITER);
			    	
			    	if(strKeyColName == col)
			    		fileWriter.append("TRUE");
			    	else
			    		fileWriter.append("FALSE");
			    	
			    	fileWriter.append(COMMA_DELIMITER);
			    	fileWriter.append("FALSE");
			    	fileWriter.append(COMMA_DELIMITER);
			    	boolean found = false;
			    	int listSize = l2.size();
			    	for(int i = 0; i < listSize; i++)
			    	{
			    		if(l2.get(0).equals(col))
			    		{
			    			l2.remove(0);
			    			found = true;
			    			fileWriter.append(l4.remove(0));
			    			break;
			    		}
			    		else
			    		{
			    			String tmp = l2.remove(0);
			    			l2.add(tmp);
			    		}
			    	}
			    	if(!found)
			    	{
			    		fileWriter.append("null");
			    	}
			    	/*for(String x : l2)
			    	{
			    		if(x.equals(col))
			    		{
			    			
			    			l2.remove(x);
			    		}
			    	}*/
			    		
			    	htblColNameType.remove(col);
			    	fileWriter.append(NEW_LINE_SEPARATOR);
			    	
		    	}
		    	
		    	
		    	
		    }
		    catch(Exception e)
		    {
		    	System.out.print("There is an error");
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
	    else
	    {
	    	System.out.println("Table name already exists");
	    }
	    
	    
	}
	
	public void createIndex(String strTableName, String strColName) throws DBAppException
	{
		
	}
	
	public void insertIntoTable(String strTableName, Hashtable<String, String> htblColNameValue) throws DBAppException
	{
		
	}
	
	public void deleteFromTable(String strTableName, Hashtable<String, String> htblColNameValue, 
			String strOperator) throws DBEngineException
	{
		
	}
	
	/*public Iterator selectValueFromTable(String strTable, Hashtable<String, String> htblColNameValue, 
			String strOperator) throws DBEngineException
	{
		
	}*/
	
	/*public Iterator selectRangeFromTable(String strTable, Hashtable<String, String> htblColNameRange, 
			String strOperator) throws DBEngineException
	{
		
	}*/
	
	public void saveAll() throws DBEngineException
	{
		
	}
	
	
	//Just to test what has been implemented so far.
	public static void main(String[] args) throws DBAppException
	{
		Hashtable h1 = new Hashtable();
		h1.put("ID", "java.lang.Integer");
		h1.put("Name", "java.lang.String");
		h1.put("Dept", "java.util.String");
		h1.put("Start_Date", "java.util.Date");
		h1.put("Country", "java.util.String");
		
		Hashtable h2 = new Hashtable();
		h2.put("Dept", "Department.ID");
		h2.put("Country", "Country.ID");
		//createTable("Employee", h1, h2, "ID");
		
		Hashtable h3 = new Hashtable();
		h3.put("id", "java.lang.Integer");
		h3.put("Vorname", "java.lang.String");
		//h3.put("Dept", "java.util.String");
		h3.put("Datum", "java.util.Date");
		//h3.put("Stadt", "java.util.String");
		//createTable("Kellner", h3, h2, "id");
	}
	
}

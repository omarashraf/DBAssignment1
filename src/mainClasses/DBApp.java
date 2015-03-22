package mainClasses;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import tableManipulation.insertion;

import com.sun.corba.se.impl.io.OptionalDataException;

import Exceptions.DBAppException;
import Exceptions.DBEngineException;

public class DBApp {
	
	
	
	static String metadata = "metadata";
	static String serFile = "records.ser";
	
	public void init()
	{
		
	}
	
	
	
	public static void createTable(String strTableName, Hashtable<String, String> htblColNameType, //remove static when not needed 
			Hashtable<String, String> htblColNameRefs, String strKeyColName) throws DBAppException
	{
		tableManipulation.createTable.createTable(strTableName, htblColNameType, htblColNameRefs, strKeyColName);
	}
	
	public void createIndex(String strTableName, String strColName) throws DBAppException
	{
		
	}
	
		
	public static void insertIntoTable(String strTableName, Hashtable<String, String> htblColNameValue) throws 
		DBAppException, IOException
	{
		insertion.insertIntoTable(strTableName, htblColNameValue);
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
	public static void main(String[] args) throws DBAppException, IOException , ClassNotFoundException
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
		//System.out.println(tableExists("Employe"));
		//System.out.println(colExists("Employee","I"));
		
		
		Hashtable h3 = new Hashtable();
		h3.put("id", "java.lang.Integer");
		h3.put("Vorname", "java.lang.String");
		//h3.put("Dept", "java.util.String");
		h3.put("Datum", "java.util.Date");
		//h3.put("Stadt", "java.util.String");
		//createTable("Kellner", h3, h2, "id");
		
		Hashtable h4 = new Hashtable();
		h4.put("ID", "1");
		h4.put("Name", "ahmed");
		h4.put("Dept", "cs");
		h4.put("Start_Date", "january");
		h4.put("Country", "egypt");
		
		
		//insertIntoTable("Employee", h4);
		//insertion.retrieve();
		
	}
	
}

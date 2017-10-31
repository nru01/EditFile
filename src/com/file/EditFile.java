package com.file;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class EditFile {

	private File newFile;
//	private FileWriter filewrite1;
//	private PrintWriter pwrite1;
	private FileReader fileread1;
//	private BufferedReader bread1;
	private String fileName;
	private String directory;
	
	public EditFile(String fileName) {
		this.directory = System.getProperty("user.dir");
		System.out.println("Current dir: " +directory);
		this.fileName = fileName;
//		this.newFile = new File(directory, fileName);
		try {
			fileread1 = new FileReader(directory+"/"+fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Creating new File: "+fileName);
			newFile = new File(directory, fileName);
			try {
				newFile.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
				System.err.println(directory+"/"+fileName+" could not be created");
			}
			try {
				fileread1 = new FileReader(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("File could not be created.");
			}
		}
//		bread1 = new BufferedReader(fileread1);
		
//		try {
//			filewrite1 = new FileWriter(fileName);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println("File was not created.");
//		}
//		pwrite1 = new PrintWriter(filewrite1);
	}
	public EditFile(String directory, String fileName) {
		this.directory = directory;
		this.fileName = fileName;
//		this.newFile = new File(directory, fileName);
		try {
			fileread1 = new FileReader(directory+"/"+fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Creating new File: "+fileName);
			newFile = new File(directory, fileName);
			try {
				newFile.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
				System.err.println(directory+"/"+fileName+" could not be created");
			}
			try {
				fileread1 = new FileReader(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("File could not be created.");
			}
		}
//		bread1 = new BufferedReader(fileread1);
		
//		try {
//			filewrite1 = new FileWriter(fileName);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println("File was not created.");
//		}
//		pwrite1 = new PrintWriter(filewrite1);
	}
	
	public static void main(String args[]) {
		EditFile ef = new EditFile("file1.txt");
		String[] str = ef.getText();
		for (int i=0; i< str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println("--------------------------current");
		
		ef.writeText("what is that song");
		
		str = ef.getText();
		for (int i=0; i< str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println("--------------------------add line");
		
		ef.writeText(ef.getText());
		
		str = ef.getText();
		for (int i=0; i< str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println("--------------------------double it");
		
		ef.replace(1, "123 can therefor replace abc");
		
		str = ef.getText();
		for (int i=0; i< str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println("--------------------------replace first line");
		
		ef.replace(3, 4, ef.getLines(1, 2));
		
		str = ef.getText();
		for (int i=0; i< str.length; i++) {
			System.out.println(str[i]);
		}
		System.out.println("--------------------------replace (3 to 4) with (1 to 2)");
	}
	
	
	/**
	 * @return Returns the directory of the file.
	 */
	public String getDir() {
		return directory;
	}
	
	/**
	 * Replaces the specified line with the given text.
	 * @param line int The line number.
	 * @param text String The new text.
	 */
	public void replace(int line, String text) {
		String[] current = this.getText();
		if (line>=1 && line<=current.length) {
			FileWriter filewrite1 = null;
			try {
				filewrite1 = new FileWriter(directory+"/"+fileName);
			} catch (IOException e) {
				System.err.println("ERROR: in writeText");
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(filewrite1);
			
			for (int i = 0; i<line-1; i++) {
				writer.println(current[i]);
			}
			writer.println(text);
			for (int i = line; i<current.length-1; i++) {
				writer.println(current[i]);
			}
			if (line<current.length) {
				writer.print(current[current.length-1]);
			}
			writer.close();
		} else {
			System.err.println("ERROR: line does not exist.");
		}
	}
	/**
	 * Replaces the given range of lines with the given texts.
	 * @param line int The beginning line number.
	 * @param endLine int The ending line number.
	 * @param text String[] The new list of texts.
	 */
	public void replace(int line, int endLine, String[] text) {
		String[] current = this.getText();
		if (line>=1 && endLine<=current.length && line<=endLine) {
			FileWriter filewrite1 = null;
			try {
				filewrite1 = new FileWriter(directory+"/"+fileName);
			} catch (IOException e) {
				System.err.println("ERROR: in writeText");
				e.printStackTrace();
			}
			PrintWriter writer = new PrintWriter(filewrite1);
			
			for (int i = 0; i<line-1; i++) {
				writer.println(current[i]);
			}
			for (int i = line-1; i<endLine; i++) {
				writer.println(text[i-(line-1)]);
			}
			for (int i = endLine; i<current.length-1; i++) {
				writer.println(current[i]);
			}
			if (endLine<current.length) {
				writer.print(current[current.length-1]);
			}
			writer.close();
		} else {
			System.err.println("ERROR: line does not exist.");
		}
	}
	/**
	 * writes the text after the existing text in the file.
	 * @param text String The text to be written to the file.
	 */
	public void writeText(String text) {
		String[] current = this.getText();
		
		FileWriter filewrite1 = null;
		try {
			filewrite1 = new FileWriter(directory+"/"+fileName);
		} catch (IOException e) {
			System.err.println("ERROR: in writeText");
			e.printStackTrace();
		}
		PrintWriter writer = new PrintWriter(filewrite1);
		
		for (int i = 0; i<current.length; i++) {
			writer.println(current[i]);
		}
		writer.print(text);
		writer.close();
	}
	/**
	 * writes the text after the existing text in the file.
	 * @param text String[] The text to be written to the file.
	 */
	public void writeText(String[] text) {
		String[] current = this.getText();
		
		FileWriter filewrite1 = null;
		try {
			filewrite1 = new FileWriter(directory+"/"+fileName);
		} catch (IOException e) {
			System.err.println("ERROR: in writeText");
			e.printStackTrace();
		}
		PrintWriter writer = new PrintWriter(filewrite1);
		
		for (int i = 0; i<current.length; i++) {
			writer.println(current[i]);
		}
		for (int i = 0; i<text.length-1; i++) {
			writer.println(text[i]);
		}
		writer.print(text[text.length-1]);
		writer.close();
	}
	/**
	 * Gets the text inside of the file.
	 * @return Returns the text of the file as an array of String.
	 */
	public String[] getText() {
		String[] listOut = null;
		List<String> fileOutInfo = new ArrayList<String>();
		
		try
		{
			fileread1 = new FileReader(directory+"/"+fileName);
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(fileread1);
			String str_line;
			
			while ((str_line = buffer.readLine()) != null)
			{
				str_line = str_line.trim();
				if ((str_line.length()!=0))
				{
					fileOutInfo.add(str_line);
				}
			}
			buffer.close();
//			System.out.println("test-1");
			listOut = (String[])fileOutInfo.toArray(new String[fileOutInfo.size()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listOut;
	}
	/**
	 * Get the specific line in the file.
	 * @param line int Line number (starting with 1).
	 * @return Returns the specified line in the file.
	 */
	public String getLine(int line) {
		String[] text = getText();
		if (line >= 1 && line<=text.length) {
			return text[line-1];
		} else {
			return null;
		}
	}
	/**
	 * Get the range of lines in the file (inclusive).
	 * @param begLine int Line number (starting with 1).
	 * @param endLine int Line number (greater than or equal to begLine).
	 * @return Returns the group of lines in the file.
	 */
	public String[] getLines(int begLine, int endLine) {
		String[] text = getText();
		String[] lines = new String[endLine-begLine+1];
		if (begLine >= 1 && endLine<=text.length && begLine<endLine) {
			for (int i = begLine-1; i<endLine; i++) {
				lines[i-(begLine-1)] = text[i];
			}
			return lines;
		} else if (begLine==endLine) {
			lines[1] = text[endLine-1];
			return lines;
		} else {
			return null;
		}
	}
	/**
	 * Gets the specified lines in the file.
	 * @param lines int[] An array of each line to get.
	 * @return Returns String[]: the specified lines.
	 */
	public String[] getLines(int[] lines) {
		String[] text = getText();
		String[] textLines = new String[lines.length];
		for (int i = 0; i<lines.length; i++) {
			if (lines[i]>=1 && lines[i]<=text.length) {
				textLines[i] = text[lines[i]];
			} else {
				textLines[i] = null;
			}
		}
		return textLines;
	}
	
}

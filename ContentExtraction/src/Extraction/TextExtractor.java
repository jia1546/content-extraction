package Extraction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextExtractor {

		/**
		 * check whether the input file is of type txt
		 * @param path
		 */
		public void checkFileType(String path)
		{
			String suffix = path.substring(path.indexOf('.') + 1);
			
			if(! suffix.equals("txt"))
			{
				System.err.println("Invalid file type. This is for txt files!");
				System.exit(0);
			}
		}
		
		public String getContent(String path) 
		{
			this.checkFileType(path);
			
			BufferedReader inputStream = null;
			String content = "";
			
			try {
				inputStream	= new BufferedReader(new FileReader(path));
				String line = inputStream.readLine();
				
				while(line != null)
				{
					content += line;
					line = inputStream.readLine();
				}
				
				inputStream.close();
				return content;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
			return content;
		}
		
		public static void main(String[] args) {
			if(args.length != 1)
			{
				System.err.println("Usage java /var/TextExtractor /var/sample.txt");
				System.exit(0);
			}
			
			String content = null;
			TextExtractor aExtractor = new TextExtractor();
			content = aExtractor.getContent(args[0]);
			
			System.out.println(content);
		}	
}

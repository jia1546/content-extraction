package Extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


/**
 * 
 * @author jia1546(jia1546@163.com)
 * extract content from MS doc and docx files
 */
public class DocExtractor {
	public String getDocContent(String PATH)
	{
		String content = null;
		String surfix = null;
		surfix = PATH.substring(PATH.indexOf('.') + 1);
		
		File file = new File(PATH);
			try {
				
				FileInputStream inputStream = new FileInputStream(file);
				
				if(surfix.equals("doc"))
				{
					WordExtractor aExtractor = new WordExtractor(inputStream);
					content = aExtractor.getText();
					inputStream.close();
				}
				else if(surfix.equals("docx"))
				{
					XWPFDocument  aXWPFDocument = new XWPFDocument(inputStream);
					XWPFWordExtractor aXWPFExtractor = new XWPFWordExtractor(aXWPFDocument);
					content = aXWPFExtractor.getText();
					inputStream.close();
				}
				else
				{
					System.err.println("Invalid file type. This is for doc and docx files");
					System.exit(0);
				}
				
				return content;
				} catch(FileNotFoundException e){
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			return content;			
		}
	
	public static void main(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("Usage: java /var/DocExtractor /var/sample.doc|sample.docx");
			System.exit(0);
		}
		
		DocExtractor aExtractor = new DocExtractor();
		String content = aExtractor.getDocContent(args[0]);

		System.out.println(content.trim());	
	}
		
}

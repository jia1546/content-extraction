package Extraction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdfparser.*;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * extract content from pdf file with aid of pdf-box library
 * @author jia1546(jia1546@163.com)
 */

public class PdfExtractor {
	
	/**
	 * check whether the input file is of type pdf
	 * @param path
	 */
	public void checkFileType(String path)
	{
		String suffix = path.substring(path.indexOf('.') + 1);
		
		if(! suffix.equals("pdf"))
		{
			System.err.println("Invalid file type. This is for txt files!");
			System.exit(0);
		}
	}
	
	
	public String getContent(String path) {
		
		this.checkFileType(path);
		
		FileInputStream inputStream = null;
		String content = null;
		try {
			inputStream = new FileInputStream(path);
			PDFParser parser = new PDFParser(inputStream);
			parser.parse();
			PDFTextStripper stripper = new PDFTextStripper();
			content = stripper.getText(parser.getPDDocument());

			inputStream.close();
			return content;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public static void main(String[] args){
		if(args.length != 1)
		{
			System.out.println("Usage: java /var/PdfExtractor /var/sample.pdf");
			System.exit(0);
		}
		
		PdfExtractor aExtractor = new PdfExtractor();
		String content = aExtractor.getContent(args[0]);
		System.out.println(content);
	}
}

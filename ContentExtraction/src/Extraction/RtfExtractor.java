package Extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;

public class RtfExtractor {
	
	/**
	 * ensure that input file is of type rtf
	 */
	public void checkFileType(String path)
	{
		String surfix = path.substring(path.indexOf('.') + 1);
		if(!surfix.equals("rtf"))
		{
			System.err.println("Invalid file type: this is for rtf files!");
			System.exit(0);
		}
	}
	
	/**
	 * Here we don't consider Chinese content extraction. If needed, substitute line 44 for
	 * result = new String(styledDoc.getText(0,styledDoc.getLength()).getBytes("ISO8859_1"));
	 * @param path
	 * @return
	 */
	public String getContent(String path)
	{
		File file = new File(path);
		
		this.checkFileType(path);
		
		String content = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			
			DefaultStyledDocument styledDoc = new DefaultStyledDocument();
			new RTFEditorKit().read(inputStream, styledDoc, 0);
			content = new String(styledDoc.getText(0,styledDoc.getLength()));
			
			inputStream.close();
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(BadLocationException e){
			e.printStackTrace();
		}
		return content;
	}
	
	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.out.println("Usage: java /var/RtfExtractor /var/sample.rtf");
			System.exit(0);
		}
		
		String path = args[0];
		String content = null;
		
		RtfExtractor aExtractor = new RtfExtractor();
		content = aExtractor.getContent(path);
		
		System.out.println(content);
	}
}

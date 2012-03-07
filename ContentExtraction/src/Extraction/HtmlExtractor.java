package Extraction;
import org.htmlparser.beans.StringBean;

/**
 * Extract content from html source file with provided html parser beans 
 * @author jia1546
 *
 */
public class HtmlExtractor {
	
	public String getContent(String url)
	{
		StringBean strbean = new StringBean();
		
		//do not need information about url links in the page
		strbean.setLinks(false);
		
		//substitute  incessant blanks with regular blank
		strbean.setReplaceNonBreakingSpaces(true);
		
		//substitute successive blanks with a single blank
		strbean.setCollapse(true);
		
		//give the url to be parsed
		strbean.setURL(url);
		
		return strbean.getStrings();
	}
	

	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.err.println("Usage java /var/HtmlExtractor url");
			System.exit(0);
		}
		
		HtmlExtractor aExtraction = new HtmlExtractor();
		String content = aExtraction.getContent(args[0]);
		
		System.out.println(content);

	}

}

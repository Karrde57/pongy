import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class OptionsReaderSetter
{

	static public HashMap<String, Integer> get()
	{	
		SAXBuilder sxb = new SAXBuilder();
	    Document document = null;
		try
	      {
	         document = sxb.build(new File(GameManager.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\config\\options.xml");
	      }
	      catch(Exception e)
	      {
	    	  return null;
	      }

	    Element racine = document.getRootElement();
		List options = racine.getChildren();
		Iterator i = options.iterator();
		HashMap res = new HashMap<String, Integer>();
		while(i.hasNext())
		{
			Element courant = (Element)i.next();
			res.put(courant.getName(), Integer.parseInt(courant.getText()));
		}
		return res;
		
	}
	static public void set()
	{
		//TODO fonction de set du xml
	}
	
}

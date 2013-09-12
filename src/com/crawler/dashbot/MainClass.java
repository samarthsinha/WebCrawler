package com.crawler.dashbot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass{
	public static void main(String[] args) {
		fetchWebSiteMetaData("http://localhost/");
		System.out.println("HELLO BACK");
		
	}
	public static void fetchWebSiteMetaData(String url)
	{
		Document doc;
		try{
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30").get();
			String title = doc.title();
			String meta_key=null;
			String meta_desc=null;
			Elements metadata = doc.select("meta");
			for(Element meta : metadata)
			{
				if(meta.attr("name").compareToIgnoreCase("keywords")==0)
				{
					meta_key = meta.attr("content");
				}
				if(meta.attr("name").compareToIgnoreCase("description")==0)
				{
					meta_desc = meta.attr("content");
				}
			}
			Elements anchors = doc.select("a[href]");
			HashSet<String> url_list = new HashSet<String>();
			for(Element anchor : anchors)
			{
				String rel = anchor.attr("rel").toLowerCase();
				if(rel.contains("nofollow"))
				{
					continue;
				}
				String _url = anchor.absUrl("href");
				System.out.println(_url);
				url_list.add(_url);
			}
			//System.out.println(meta_key);
			DataWrapperIndexClass data = new DataWrapperIndexClass(url, meta_key, meta_desc, title);
			System.out.println(data);
			DatabaseInsertClass db = new DatabaseInsertClass();
			try {
				db.readDataBase(data);
			} catch (Exception e) {
				System.err.println("ERROR IN QUERY: "+e.getMessage());
			}
			 Iterator iterator = url_list.iterator();
			 while(iterator.hasNext())
			 {
				 process(iterator.next().toString());
				 //fetchWebSiteMetaData(iterator.next().toString());
			 }
		}
		catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			
			if(e.getMessage().contains("Duplicate entry"))
			{
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
		
		
		
	}

	
	static void process(String str) {
	    class OneShotTask implements Runnable {
	        String str;
	        OneShotTask(String s) { str = s; }
	        public void run() {
	            fetchWebSiteMetaData(str);
	            
	        }
	    }
	    Thread t = new Thread(new OneShotTask(str));
	    t.start();
	}
	
}

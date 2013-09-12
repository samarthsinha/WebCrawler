package com.crawler.dashbot;

public class DataWrapperIndexClass {
	private String url;
	private String keywords;
	private String metadata_desc;
	private String title;
	public DataWrapperIndexClass(String url,String keywords,String metadata_desc,String title)
	{
		this.url = url;
		this.keywords=keywords;
		if(metadata_desc!=null)
			this.metadata_desc = metadata_desc;
		else 
			this.metadata_desc = "No Description Found";
		this.title = title;	
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeywords() {
		if(this.keywords == null)
		{
			return this.title + this.metadata_desc;
		}
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescr() {
		return metadata_desc;
	}

	public void setDescr(String metadata_desc) {
		this.metadata_desc = metadata_desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "**\n"+this.title+ "**\n" + this.url + "**\n" +this.metadata_desc+ "**\n"+ this.keywords;
		return result;
	}
	

}

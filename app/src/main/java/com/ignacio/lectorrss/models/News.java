package com.ignacio.lectorrss.models;

import android.util.Log;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created By Ignacio Galliano
 */
public class News {

    private String title;
    private String link;
    private String guid;
    private String pubDate;
    private String enclosure;


    public News(NodeList nodeList){
        for(int j = 0; j < nodeList.getLength(); j++ ){
            Node item = nodeList.item(j);
            if(item.getNodeName().equalsIgnoreCase("title")){
                this.title = item.getTextContent();
            }
            if(item.getNodeName().equalsIgnoreCase("link")){
                this.link = item.getTextContent();
            }
            if(item.getNodeName().equalsIgnoreCase("guid")){
                this.guid = item.getTextContent();
            }
            if(item.getNodeName().equalsIgnoreCase("pubDate")){

                DateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                try{
                    Date date = inputFormat.parse(item.getTextContent());
                    this.pubDate = outputFormat.format(date);
                }catch (Exception e){
                    this.pubDate = "";
                }

            }
            if(item.getNodeName().equalsIgnoreCase("enclosure")){
                //this.enclosure = ((Element) item).getAttribute("url");
                this.enclosure = item.getAttributes().item(0).getTextContent();
                Log.d("enclosure", enclosure);
            }
        }
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }
}

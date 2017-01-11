package com.almesh.enthusia;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class rssparser
{
	static int netflag=0;//to check net
	public static void parse (String  connect)
    {
            URL url;
            try
            {
                    // Set the url (you will need to change this to your RSS URL
            	 url = new URL(connect);
            	 
                    // Setup the connection
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    // Connect
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
                    {
                    		
                            // Retreive the XML from the URL
                            DocumentBuilderFactory dbf = DocumentBuilderFactory
                                            .newInstance();
                            DocumentBuilder db = dbf.newDocumentBuilder();
                            Document doc;
                            doc = db.parse(url.openStream());
                            doc.getDocumentElement().normalize();

                            // This is the root node of each section you want to parse
                            NodeList itemLst = doc.getElementsByTagName("item");
                            // This sets up some arrays to hold the data parsed
                            arrays.PodcastTitle = new String[itemLst.getLength()];
                            arrays.PodcastURL = new String[itemLst.getLength()];
                            arrays.PodcastContent = new String[itemLst.getLength()];
                            arrays.PodcastMedia = new String[itemLst.getLength()];
                           // Loop through the XML passing the data to the arrays
                            for (int i = 0; i < itemLst.getLength(); i++)
                            {		

                                    Node item = itemLst.item(i);
                                    if (item.getNodeType() == Node.ELEMENT_NODE)
                                    {	

                                            Element ielem = (Element) item;
                                         // This section gets the elements from the XML
                                            // that we want to use you will need to add
                                            // and remove elements that you want / don't want
                                            NodeList title = ielem.getElementsByTagName("title");
                                            NodeList link = ielem.getElementsByTagName("link");
                                            NodeList content = ielem.getElementsByTagName("description");
                                          
                                            try
                                            {
                                                    arrays.PodcastTitle[i] = title.item(0)
                                                                    .getChildNodes().item(0).getNodeValue();
                                                    
                                            } catch (NullPointerException e)
                                            {netflag=1;
                                                    e.printStackTrace();
                                            }
                                            
                                            try
                                            {
                                                arrays.PodcastURL[i] = link.item(0).getChildNodes()
                                                                .item(0).getNodeValue();

                                        } catch (NullPointerException e)
                                        {netflag=1;
                                                e.printStackTrace();
                                        }
                                        try
                                        {
                                                arrays.PodcastContent[i] = content.item(0)
                                                                .getChildNodes().item(0).getNodeValue();

                                        } catch (NullPointerException e)
                                        {netflag=1;
                                                e.printStackTrace();
                                        }

                                      /*  try
                                        {
                                                arrays.PodcastMedia[i] = mediaurl;
                                        } catch (NullPointerException e)
                                        {
                                                e.printStackTrace();
                                        }*/
                                }
                        }
                }

        } catch (MalformedURLException e)
        {netflag=1;
                e.printStackTrace();
        } catch (DOMException e)
        {netflag=1;
                e.printStackTrace();
        } catch (IOException e)
        {netflag=1;
                e.printStackTrace();
        } catch (ParserConfigurationException e)
        {netflag=1;
                e.printStackTrace();
        } catch (SAXException e)
        {netflag=1;
                e.printStackTrace();
        }
        catch (NullPointerException e)
        {	
        		netflag=1;
                e.printStackTrace();
        }

}

}







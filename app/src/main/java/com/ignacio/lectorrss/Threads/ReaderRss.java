package com.ignacio.lectorrss.Threads;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ignacio.lectorrss.models.News;
import com.ignacio.lectorrss.view.adapters.NewsListAdapter;
import com.ignacio.lectorrss.view.interfaces.AsyncTaskResponse;
import com.ignacio.lectorrss.view.interfaces.ListSelectedCallListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.security.AccessController.getContext;

/**
 * Created By Ignacio Galliano
 */
public class ReaderRss extends AsyncTask <Void, Void, Void> {

    private ArrayList<News> arrayNews = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private  Context context;
    private RecyclerView recyclerView;
    private URL url;

    private AsyncTaskResponse asyncTaskResponse;

    public ReaderRss(Context context,AsyncTaskResponse asyncTaskResponse ,RecyclerView recyclerView){
        this.context =  context;
        this.recyclerView = recyclerView;
        this.asyncTaskResponse = asyncTaskResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        asyncTaskResponse.responseArrayNews(arrayNews);

    }

    @Override
    protected Void doInBackground(Void... voids) {
        parseData(getData());
        return null;
    }

    private void parseData(Document data){
        if(data != null){
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList list = channel.getChildNodes();
            for(int i = 0; i < list.getLength(); i++ ){
                Node item = list.item(i);
                if(item.getNodeName().equalsIgnoreCase("item")){
                    News news = new News(item.getChildNodes());
                    arrayNews.add(news);
                }
            }
        }
    }

    public Document getData(){
        try {
            url = new URL("https://www.telegraph.co.uk/football/rss.xml");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc=builder.parse(inputStream);
            return xmlDoc;

        }catch (Exception e){

            return null;
        }
    }
}

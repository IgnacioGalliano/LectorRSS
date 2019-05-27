package com.ignacio.lectorrss.view.interfaces;

import com.ignacio.lectorrss.models.News;

import java.util.ArrayList;

/**
 * Created By Ignacio Galliano
 */
public interface AsyncTaskResponse {
    void responseArrayNews(ArrayList<News> arrayNews);
}

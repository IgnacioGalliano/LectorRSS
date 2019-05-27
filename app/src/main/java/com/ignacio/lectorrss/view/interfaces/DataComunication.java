package com.ignacio.lectorrss.view.interfaces;

import com.ignacio.lectorrss.models.News;

/**
 * Created By Ignacio Galliano
 */
public interface DataComunication {
    public News getNewsSelected();
    public void setNewsSelected(News news);
}

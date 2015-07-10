package com.codemobi.android.tvthailand.dao.show;

import com.codemobi.android.tvthailand.manager.bus.MainBus;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattapong on 12/20/14 AD.
 */
public class ShowCollectionDao {
    @SerializedName("programs") private List<ShowItemDao> shows;

    public ShowCollectionDao() {
        this.shows = new ArrayList<>();
    }

    public List<ShowItemDao> getShows() {
        return shows;
    }

    public void setShows(List<ShowItemDao> shows) {
        this.shows = shows;
    }

    public void addShows(List<ShowItemDao> shows) {
        for (ShowItemDao item: shows) {
            this.shows.add(item);
        }

        MainBus.getInstance().post(this);
    }

    public static List<ShowItemDao> MockShowList() {
        List<ShowItemDao> shows = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ShowItemDao showItem = new ShowItemDao();
            showItem.setTitle("Lakorn 3");
            showItem.setDescription("Channel 3");
            showItem.setThumbnailURL("http://thumbnail.instardara.com/tv/News3D.jpg");
            shows.add(showItem);
        }
        return shows;
    }
}

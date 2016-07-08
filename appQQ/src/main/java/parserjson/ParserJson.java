package parserjson;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import bean.News;
import bean.Video;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ParserJson {
    public static List<News> parserJsonToNews(String json) {
        List<News> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("T1348649776727");
            String newJson = jsonArray.toString();
            list = JSON.parseArray(newJson, News.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Video> parserJsonToVideo(String json) {
        List<Video> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("T1457069041911");
            String newJson = jsonArray.toString();
            list = JSON.parseArray(newJson, Video.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}


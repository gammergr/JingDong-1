package com.bwie.testten.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.testten.R;
import com.google.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 姜天赐 on 2017/12/1.
 */
public class FindFragment extends Fragment {

    private ListView lv;
    private List<VideoBean> lists;
    private MyAdapter adapter;

    List<String> list= new ArrayList<>();
    String uri="http://pic.ibaotu.com/00/34/35/51S888piCamj.mp4_10s.mp4";
    private final static int REQ_CODE = 1028;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    Unbinder unbinder;
    @BindView(R.id.erwei)
    ImageView erwei;
    @BindView(R.id.more)
    TextView more;
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_find, container, false);
        unbinder = ButterKnife.bind(this, v);
        tabLayout.addTab(tabLayout.newTab().setText("关注"));
        tabLayout.addTab(tabLayout.newTab().setText("精选"));
        tabLayout.addTab(tabLayout.newTab().setText("直播"));
        tabLayout.addTab(tabLayout.newTab().setText("视频"));
        tabLayout.addTab(tabLayout.newTab().setText("社区"));
        tabLayout.addTab(tabLayout.newTab().setText("生活"));
        tabLayout.addTab(tabLayout.newTab().setText("数码"));
        tabLayout.addTab(tabLayout.newTab().setText("亲子"));
        tabLayout.addTab(tabLayout.newTab().setText("风尚"));
        tabLayout.addTab(tabLayout.newTab().setText("美食"));
        erwei();
        for (int i=0;i<10;i++){
            list.add(uri+"  ");
        }
        lv = (ListView)v.findViewById(R.id.lv);
        initData();
        adapter = new MyAdapter(lists,getActivity());
        lv.setAdapter(adapter);
        return v;
    }
    private void erwei() {
        erwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void initData() {
        try {
            StringBuffer result = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("video1.json")));
            String str;
            //读取文件数据
            while((str = br.readLine())!=null){
                result.append(str);
            }
            br.close();
            //解析json数据
            parseJson(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJson(String s) {
        lists = new ArrayList<>();
        try {
            JSONArray dataArr = new JSONObject(s).getJSONObject("data").getJSONArray("data");
            for (int i = 0; i < dataArr.length(); i++) {
                try {
                    JSONObject group = dataArr.getJSONObject(i).getJSONObject("group");
                    String p360Video = group.getJSONObject("360p_video").getJSONArray("url_list").getJSONObject(0).getString("url");
                    String title = group.getString("title");
                    int video_height = group.getInt("video_height");
                    String coverUrl = group.getJSONObject("medium_cover").getJSONArray("url_list").getJSONObject(0).getString("url");
                    int video_width = group.getInt("video_width");
                    lists.add(new VideoBean(p360Video, coverUrl, video_width, video_height, title));
                } catch (JSONException e) {
//                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

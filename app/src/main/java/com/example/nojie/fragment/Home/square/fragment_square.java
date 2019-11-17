package com.example.nojie.fragment.Home.square;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nojie.Entity.TuchongEntity;
import com.example.nojie.MyIndexRecyclerViewAdapter;
import com.example.nojie.R;
import com.example.nojie.utility.ScreenUtils;
import com.google.gson.Gson;
import com.stx.xhb.androidx.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class fragment_square extends Fragment {
    private TextView date;
    private TextView month_year;
    private ImageView list_button;
    private ImageView search_button;
    private XBanner mXBanner;
    private TextView push_1_title;
    private TextView push_1_time;
    private TextView push_2_title;
    private TextView push_2_time;
    private TextView push_3_title;
    private TextView push_3_time;
    private List<Recommend_Model> models = new ArrayList<>();
    private RecyclerView recommend_list;
    private TextView push_title;
    private RecyclerView recyclerView;

    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("chakan"+"-->onCreatView");
        root = inflater.inflate(R.layout.fragment_square,container,false);


        initConentView(root);
        initBanner();
        initData();
        return root;
    }
    public void initConentView(View root) {
        date=root.findViewById(R.id.square_date);
        month_year=root.findViewById(R.id.square_year_month);
        list_button=root.findViewById(R.id.square_list_button);
        search_button=root.findViewById(R.id.square_search_button);
        push_1_time=root.findViewById(R.id.square_push_list_1_Time);
        push_1_title=root.findViewById(R.id.square_push_list_1_Title);
        push_2_time=root.findViewById(R.id.square_push_list_2_Time);
        push_2_title=root.findViewById(R.id.square_push_list_2_Title);
        push_3_time=root.findViewById(R.id.square_push_list_3_Time);
        push_3_title=root.findViewById(R.id.square_push_list_3_Title);
        recommend_list=root.findViewById(R.id.square_recommend_list);
        push_title=root.findViewById(R.id.square_push1_title);
        //获取控件
        mXBanner = (XBanner) root.findViewById(R.id.xbanner);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(getContext()) / 2);

        mXBanner.setLayoutParams(layoutParams);
    }
    /**
     * 初始化XBanner
     */
    private void initBanner() {        //设置广告图片点击事件
        mXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getActivity(), "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
            }
        });
//        //加载广告图片
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
                TuchongEntity.FeedListBean.EntryBean listBean = ((TuchongEntity.FeedListBean.EntryBean) model);
                String url = "https://photo.tuchong.com/" + listBean.getImages().get(0).getUser_id() + "/f/" + listBean.getImages().get(0).getImg_id() + ".jpg";
                Glide.with(getActivity()).load(url).placeholder(R.drawable.default_image).error(R.drawable.error).into((ImageView) view);
                System.out.println("活了活了！！！！！！！！！！！");

            }
        });
        List<TuchongEntity.FeedListBean.EntryBean> data = new ArrayList<>();
        mXBanner.setBannerData(data);
    }
    public void initData() {

        push_title.setText("你可能感兴趣的盲选新作");

        Calendar cal=Calendar.getInstance();
        Integer y=cal.get(Calendar.YEAR);
        Integer m=cal.get(Calendar.MONTH);
        Integer d=cal.get(Calendar.DATE);
        String month=null;
        switch (m){
            case 1:month="Jan.";
                break;
            case 2:month="Feb.";
                break;
            case 3:month="Mar.";
                break;
            case 4:month="Apr.";
                break;
            case 5:month="Mar.";
                break;
            case 6:month="Jun.";
                break;
            case 7:month="Jul.";
                break;
            case 8:month="Aug.";
                break;
            case 9:month="Sept.";
                break;
            case 10:month="Oct.";
                break;
            case 11:month="Nov.";
                break;
            case 12:month="Dec.";
                break;
        }
        date.setText(d.toString());
        month_year.setText(month+y.toString());
        list_button.setImageResource(R.drawable.list);
        search_button.setImageResource(R.drawable.search);
        push_1_time.setText("23小时前发布于上海");
        push_1_title.setText("全新编曲《歌颂者》");
        push_2_time.setText("23小时前发布于上海");
        push_2_title.setText("全新编曲《歌颂者》");
        push_3_time.setText("23小时前发布于上海");
        push_3_title.setText("全新编曲《歌颂者》");


        //加载网络图片资源
        String url = "https://api.tuchong.com/2/wall-paper/app";

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        try {
                            Toast.makeText(getActivity(), "加载广告数据失败", Toast.LENGTH_SHORT).show();
                        }catch (NullPointerException el){
                            System.out.println("出错了啊喂，square_181");
                        }

                    }
                    @Override
                    public void onResponse(String response, int id) {

                        TuchongEntity advertiseEntity = new Gson().fromJson(response, TuchongEntity.class);
                        List<TuchongEntity.FeedListBean> others = advertiseEntity.getFeedList();
                        List<TuchongEntity.FeedListBean.EntryBean> data = new ArrayList<>();
                        for (int i = 0; i <others.size(); i++) {
                            TuchongEntity.FeedListBean feedListBean = others.get(i);
                            if ("post".equals(feedListBean.getType())) {
                                data.add(feedListBean.getEntry());
                            }
                        }

                        //刷新数据之后，需要重新设置是否支持自动轮播
                        mXBanner.setAutoPlayAble(data.size() > 1);
                        mXBanner.setIsClipChildrenMode(true);
                        mXBanner.setBannerData(data);
                    }
                });

        Recommend_Model model1=new Recommend_Model();
        Recommend_Model model2=new Recommend_Model();
        Recommend_Model model3=new Recommend_Model();
        Recommend_Model model4=new Recommend_Model();
        model1.setLocation("无锡·蠡湖");
        model1.setOn_short_word("秋色千重野草枯，斜阳一抹染蠡湖");
        model1.setTitle("原创新曲欣赏");
        model2.setLocation("广西·海宁");
        model2.setOn_short_word("从鲜衣怒马到传奇归来\n傅海峰燃情岁月");
        model2.setTitle("原创新曲欣赏");
        model3.setLocation("上海·浦东");
        model3.setOn_short_word("力挺华为：\n全新操作系统“鸿蒙”！");
        model3.setTitle("原创新曲欣赏");
        model4.setLocation("河北·唐山");
        model4.setOn_short_word("经典老歌，全新翻唱K歌之王");
        model4.setTitle("原创新曲欣赏");
        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);

        recommend_list.setFocusableInTouchMode(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recommend_list.setLayoutManager(layoutManager);
        MyRecommendAdapter adapter = new MyRecommendAdapter(models);
        recommend_list.setAdapter(adapter);
        adapter.setOnRecyclerItemClickLitener(new MyIndexRecyclerViewAdapter.onRecyclerItemClickLitener() {
            @Override
            public void onRecyclerItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部点击"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRecyclerItemLongClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部长按"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        MyRecommendAdapter adapter= (MyRecommendAdapter) recommend_list.getAdapter();
        Integer start=0;
        Integer end=adapter.getItemCount();
        for(int i=end-1;i>=0;i-- ){
            adapter.removeData(i);
        }
        super.onDestroy();
    }
}

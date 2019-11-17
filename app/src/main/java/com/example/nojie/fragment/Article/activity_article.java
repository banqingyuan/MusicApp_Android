package com.example.nojie.fragment.Article;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nojie.R;
import com.example.nojie.fragment.Home.fragment_inspiration;
import com.example.nojie.utility.HtmlFormat;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import java.util.ArrayList;
import java.util.List;

public class activity_article extends Activity {

    private TitleBar titleBar;
    private Fragment currentFragment = new Fragment();
    private WebView webView;
    private RecyclerView recyclerView;
    private List<Article_Comment> myDataset = new ArrayList<>();


    private static final String testString1 =
            "<h3>Test1</h3><img src=\"http://h.hiphotos.baidu.com/image/h%3D200/sign=e72c850a09f3d7ca13f63876c21fbe3c/a2cc7cd98d1001e9460fd63bbd0e7bec54e797d7.jpg\" /><br>"+
            "<h3>Test2</h3><img src=\"http://c.hiphotos.baidu.com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg\" /><br>"+
            "<h3>Test3</h3><img src=\"http://c.hiphotos.baidu.com/image/pic/item/267f9e2f070828382dcc0b20bd99a9014d08f1c5.jpg\" /><br>"+
            "<h3>Test4</h3><img src=\"http://f.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d358824a0d950a304e251f5812.jpg\" /><br>"+
            "<h3>Test5</h3><img src=\"http://f.hiphotos.baidu.com/image/pic/item/c2cec3fdfc039245831fa7498294a4c27c1e25c9.jpg\" /><br>"+
            "<h3>Test6</h3><img src=\"http://e.hiphotos.baidu.com/image/pic/item/b999a9014c086e06613eab4b00087bf40ad1cb18.jpg\" /><br>"+
            "<h3>Test7</h3><img src=\"http://a.hiphotos.baidu.com/image/pic/item/503d269759ee3d6d251670cb41166d224e4adeda.jpg\" /><br>"+
            "<h3>Test8</h3><img src=\"http://f.hiphotos.baidu.com/image/pic/item/cb8065380cd791234275326baf345982b2b7801c.jpg\" /><br>"+
            "<h3>Test9</h3><img src=\"http://a.hiphotos.baidu.com/image/pic/item/bba1cd11728b4710910b55c9c1cec3fdfc03238a.jpg\" />";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initConentView();
        initData();
    }

    private void initConentView() {
        titleBar = findViewById(R.id.article_title);
        webView = findViewById(R.id.article_webview);
        recyclerView = findViewById(R.id.article_comment_recyclerview);
    }

    private void initData() {

        Article_Comment article_comment1 = new Article_Comment();
        article_comment1.setComment_content_text("This is content hahahahahahahahahahahahahahahahaha");
        article_comment1.setComment_type("无界音乐");
        article_comment1.setDate("2019-5-6");
        article_comment1.setUsername("单纯不做作的苏颖");
        Article_Comment article_comment2 = new Article_Comment();
        article_comment2.setComment_content_text("This is content hahahahahahahahahahahahahahahahaha");
        article_comment2.setComment_type("无界填词");
        article_comment2.setDate("2019-5-8");
        article_comment2.setUsername("单纯不做作的苗栩");
        Article_Comment article_comment3 = new Article_Comment();
        article_comment3.setComment_content_text("This is content hahahahahahahahahahahahahahahahaha");
        article_comment3.setComment_type("无界演唱");
        article_comment3.setDate("2019-11-11");
        article_comment3.setUsername("单纯不做作的我");
        myDataset.add(article_comment1);
        myDataset.add(article_comment2);
        myDataset.add(article_comment3);
        setTitleBar();
        setWebView();
        setRecyclerView(myDataset);
    }

    private void setTitleBar() {
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
        @Override
        public void onLeftClick(View v) {

        }

        @Override
        public void onTitleClick(View v) {

        }

        @Override
        public void onRightClick(View v) {

        }
    });
    }

    private void setRecyclerView(List<Article_Comment> myDataset) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Article_Comment_Recycler_Adapter adapter = new Article_Comment_Recycler_Adapter(myDataset);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerItemClickLitener(new Article_Comment_Recycler_Adapter.onRecyclerItemClickLitener() {
            @Override
            public void onRecyclerItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getApplicationContext(), "内部点击"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRecyclerItemLongClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getApplicationContext(), "内部长按"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true);//支持js
//        webSettings.setBuiltInZoomControls(true); // 显示放大缩小
//        webSettings.setSupportZoom(true); // 可以缩放
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
                return true;
            }
        });
        //取消滚动条
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        //不支持缩放功能
        webView.getSettings().setSupportZoom(false);
        webView.loadDataWithBaseURL(null, HtmlFormat.getNewContent(testString1), "text/html", "UTF-8", null);

    }

}

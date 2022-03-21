package com.example.laladui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.laladui.listview_news.WebInformationActivity;
import com.example.laladui.listview_news.rubbishAdapter;
import com.example.laladui.listview_news.News;
import com.example.laladui.listview_news.Rubbish;
import com.example.laladui.views.PagerItem;
import com.example.laladui.views.SobLooperPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentZixun extends Fragment implements AdapterView.OnItemClickListener {
    private View rootView;
    private SobLooperPager mLooperPager;
    private List<PagerItem> mData = new ArrayList<>();
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ArrayList<News> information;//新闻详情
    private Map<Integer, Integer> map;
    private ArrayList<String> html;
    private ArrayList<Rubbish> list;//首页新闻
    private rubbishAdapter adapter;//首页新闻适配器
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_zixun, container, false);
        iniToolbar();
        initData();
        initView(rootView);
        initList();
        initNews();
        listView.setOnItemClickListener(this);
        return rootView;
    }
    private void initData() {
        mData.add(new PagerItem("第一个图片",R.drawable.pic0));
        mData.add(new PagerItem("第二个图片",R.drawable.pic1));
        mData.add(new PagerItem("第三个图片",R.drawable.pic2));
        mData.add(new PagerItem("第四个图片",R.drawable.pic3));
    }

    private void initView(View view) {
        mLooperPager=rootView.findViewById(R.id.sob_looper_pager);
        mLooperPager.setData(new SobLooperPager.InnerAdapter() {
            @Override
            protected int getDataSize() {
                return mData.size();
            }

            @Override
            protected View getSubView(ViewGroup container, int position) {
                ImageView iv=new ImageView(container.getContext());
                iv.setImageResource(mData.get(position).getPicResId());
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                ViewPager.LayoutParams layoutParams=new ViewPager.LayoutParams();
                return iv;
            }
        }, new SobLooperPager.BindTitleListener() {
            @Override
            public String getTitle(int position) {
                return mData.get(position).getTitle();
            }
        });




    }
    public void iniToolbar(){
        setHasOptionsMenu(true);//自定义标题栏必须调用此方法，用于显示菜单
        toolbar = rootView.findViewById(R.id.toolbar1);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();//获得ActionBar的实例
        if(actionBar!=null){
            //actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.caidan);//设置导航按钮图标
            actionBar.setTitle("资讯" );
        }
    }
    //初始化新闻详情
    public void initNews(){
        information = new ArrayList<>();
        information.add(new News("https://baijiahao.baidu.com/s?id=1683870085941401953&wfr=spider&for=pc","垃圾分类真的来了！乱扔是要罚款的"));
        information.add(new News("http://sn.people.com.cn/GB/n2/2020/1108/c378288-34401278.html","垃圾分类撤桶下楼 助力绿色文明新生活"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1683696805738388382&wfr=spider&for=pc","垃圾分类科普小知识"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1683689132672547346&wfr=spider&for=pc","环保小超人，拯救大地球"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1682616735648543950&wfr=spider&for=pc","「文明宣传」垃圾分类处理科普小知识！"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1683888129390043074&wfr=spider&for=pc","小手拉大手，垃圾分类学起来!"));
        information.add(new News("https://news.dayoo.com/gzrbrmt/202011/18/158963_53657236.htm","垃圾分类从娃娃抓起，萌娃们一起“齐乐分”！"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1680862668338734542&wfr=spider&for=pc","超实用垃圾分类知识小漫画，一看就懂！"));
        information.add(new News("https://www.cqcb.com/hot/2020-09-25/3044500_pc.html","小萌娃这样学习垃圾分类"));
        information.add(new News("https://www.thepaper.cn/newsDetail_forward_9037325","涨知识 | 分享垃圾分类小知识"));
        information.add(new News("https://www.thepaper.cn/newsDetail_forward_8692851","垃圾分类」科普小知识来了，你get了吗"));
        information.add(new News("https://new.qq.com/omn/20200710/20200710A0LVYN00.html","健康生活 你我做起 生活垃圾分类小知识"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1669267747815792121&wfr=spider&for=pc","垃圾分类容易混？垃圾分类小妙招，轻松分辨垃圾"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1664210539504973693&wfr=spider&for=pc","关于垃圾分类，你还有哪些不知道的知识？"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1671644226668891336&wfr=spider&for=pc","从游戏中学知识提高垃圾分类意识"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1672992027231626695&wfr=spider&for=pc","你乘车的时候 也能顺便把垃圾分类知识掌握了"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1668467987642414123&wfr=spider&for=pc","一起做游戏！垃圾分类知识还能这样学！"));
        information.add(new News("https://www.thepaper.cn/newsDetail_forward_9048789","让垃圾分类更简单方便，这些知识点你需要了解！"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1666014407530544069&wfr=spider&for=pc","“花式”学习垃圾分类"));
        information.add(new News("http://szb.nanhaitoday.com/epaper/zjsb/html/2020-04/09/content_5705.htm","垃圾分类重方法"));
        information.add(new News("https://new.qq.com/omn/20200701/20200701A0SLFS00.html","生活垃圾分类应知会小常识"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1671713621142931546&wfr=spider&for=pc","这些垃圾分类小知识你记住了吗？"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1674711158346463014&wfr=spider&for=pc","看这，教你如何垃圾分类"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1669500397494121837&wfr=spider&for=pc","“硬核知识”你get到了吗？"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1678040804943542682&wfr=spider&for=pc","垃圾分类知识小漫画，一看就懂！"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1667552123974321154&wfr=spider&for=pc","做趣味游戏 学垃圾分类"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1672341930801525370&wfr=spider&for=pc","日常“拎不清”的生活垃圾分类小贴士，请查收！"));
        information.add(new News("https://www.thepaper.cn/newsDetail_forward_7544814","垃圾分类小知识，你get了吗？"));
        information.add(new News("https://www.thepaper.cn/newsDetail_forward_7792870","垃圾分类宣传小知识"));
        information.add(new News("https://baijiahao.baidu.com/s?id=1644824894529204686&wfr=spider&for=pc","有害垃圾有哪些？"));
    }

    //ListViewd的点击事件
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("gyp", "onItemClick:1 ");
        Intent intent=new Intent(getActivity(), WebInformationActivity.class);
        Log.d("gyp", "onItemClick:2 ");
        intent.putExtra("uri",information.get(i).getUri());
        Log.d("gyp", "onItemClick:3 ");
        startActivity(intent);//跳转到新闻详情界面
        Log.d("gyp", "onItemClick:4 ");


    }


    //初始化列表
    public void initList(){
        listView=rootView.findViewById(R.id.listview);
        list=new ArrayList<>();
        list.add(new Rubbish(R.drawable.p1, R.drawable.p2, R.drawable.p3, "垃圾分类真的来了！乱扔是要罚款的",
                "", "作者：文库        2020-07-06"));
        list.add(new Rubbish(R.drawable.p4, R.drawable.p5, R.drawable.p6, "垃圾分类撤桶下楼 助力绿色文明新生活",
                "", "作者：Tom        2020-02-26"));
        list.add(new Rubbish(R.drawable.p7, R.drawable.p8, R.drawable.p9, "垃圾分类科普小知识!",
                "", "作者：小花        2019-05-01"));
        list.add(new Rubbish(R.drawable.p10, R.drawable.p11, R.drawable.p12, "环保小超人，拯救大地球",
                "", "作者：阿里        2019-4-5"));
        list.add(new Rubbish(R.drawable.p13, R.drawable.p14, R.drawable.p15, "垃圾分类如何带动产业链变革?这些公司这样做!",
                "", "作者：好太太        2019-3-25"));
        list.add(new Rubbish(R.drawable.p16, R.drawable.p17, R.drawable.p18, "还没学会垃圾分类?来看看古人是如何分类的!",
                "", "作者：Lucy        2020-11-05"));
        list.add(new Rubbish(R.drawable.p19, R.drawable.p20, R.drawable.p21, "垃圾分类人人做! “站桶”服务我先行！",
                "", "作者：文库        2020-07-06"));
        list.add(new Rubbish(R.drawable.p22, R.drawable.p23, R.drawable.p24, "关于垃圾分类,这3张图你一定得看！",
                "", "作者：Tom        2020-02-26"));
        list.add(new Rubbish(R.drawable.p25, R.drawable.p26, R.drawable.p27, "垃圾分类|一起见“圾”行事",
                "", "作者：小花        2019-05-01"));
        list.add(new Rubbish(R.drawable.p28, R.drawable.p29, R.drawable.p30, "垃圾分类,你“时尚”了吗?",
                "", "作者：阿里        2019-4-5"));
        list.add(new Rubbish(R.drawable.p1, R.drawable.p5, R.drawable.p7, "践行垃圾分类、从“时尚”走向日常！",
                "", "作者：好太太        2019-3-25"));
        list.add(new Rubbish(R.drawable.p19, R.drawable.p15, R.drawable.p10, "司法局开展关于“垃圾分类 从我做起”主题宣传活动！",
                "", "作者：Lucy        2020-11-05"));
        list.add(new Rubbish(R.drawable.p1, R.drawable.p2, R.drawable.p3, "强制垃圾分类推行近一周 居民分类意识仍有待提高！",
                "", "作者：文库        2020-07-06"));
        list.add(new Rubbish(R.drawable.p4, R.drawable.p5, R.drawable.p6, "绿色生活•我家更美 拒绝浪费倡文明！",
                "", "作者：Tom        2020-02-26"));
        list.add(new Rubbish(R.drawable.p7, R.drawable.p8, R.drawable.p9, "快收藏!垃圾分类知识大全来了！",
                "", "作者：小花        2019-05-01"));
        list.add(new Rubbish(R.drawable.p10, R.drawable.p11, R.drawable.p12, "垃圾分类知识大全,你需要掌握!",
                "", "作者：阿里        2019-4-5"));
        list.add(new Rubbish(R.drawable.p13, R.drawable.p14, R.drawable.p15, "垃圾分类如何带动产业链变革?这些公司这样做!",
                "", "作者：好太太        2019-3-25"));
        list.add(new Rubbish(R.drawable.p16, R.drawable.p17, R.drawable.p18, "还没学会垃圾分类?来看看古人是如何分类的!",
                "", "作者：Lucy        2020-11-05"));
        list.add(new Rubbish(R.drawable.p19, R.drawable.p20, R.drawable.p21, "垃圾分类人人做! “站桶”服务我先行！",
                "", "作者：文库        2020-07-06"));
        list.add(new Rubbish(R.drawable.p22, R.drawable.p23, R.drawable.p24, "关于垃圾分类,这3张图你一定得看！",
                "", "作者：Tom        2020-02-26"));
        list.add(new Rubbish(R.drawable.p25, R.drawable.p26, R.drawable.p27, "垃圾分类|一起见“圾”行事",
                "", "作者：小花        2019-05-01"));
        list.add(new Rubbish(R.drawable.p28, R.drawable.p29, R.drawable.p30, "垃圾分类,你“时尚”了吗?",
                "", "作者：阿里        2019-4-5"));
        list.add(new Rubbish(R.drawable.p1, R.drawable.p5, R.drawable.p7, "践行垃圾分类、从“时尚”走向日常！",
                "", "作者：好太太        2019-3-25"));
        list.add(new Rubbish(R.drawable.p19, R.drawable.p15, R.drawable.p10, "司法局开展关于“垃圾分类 从我做起”主题宣传活动！",
                "", "作者：Lucy        2020-11-05"));

        adapter=new rubbishAdapter(getContext(),list);
        listView.setAdapter(adapter);

    }

}


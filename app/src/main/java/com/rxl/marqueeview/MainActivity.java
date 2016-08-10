package com.rxl.marqueeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MarqueeView mq,mq1;
    private List<String> notices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mq = (MarqueeView) findViewById(R.id.mq);
        mq1 = (MarqueeView) findViewById(R.id.mq1);

        notices = new ArrayList<>();
        notices.add("心中有阳光，脚底有力量！");
        notices.add("欢迎大家关注我哦！");
        notices.add("微信公众号");
        notices.add(" 新浪微博");

        mq.startWithText("心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！");
        mq1.startWithList(notices);


    }
}

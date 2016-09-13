package com.yanxing;

import android.os.Bundle;
import android.widget.TextView;

import com.yanxing.iview.MainView;
import com.yanxing.model.DouBan;
import com.yanxing.presenter.MainPresenter;

/**
 * mvp demo
 */
public class MainActivity extends BaseActivity<MainView,MainPresenter> implements MainView {

    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContent= (TextView) findViewById(R.id.content);
        mPresenter.loadData();
    }

    @Override
    public void setData(DouBan douBan) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < douBan.getSubjects().size(); i++) {
            stringBuilder.append(String.valueOf(i));
            stringBuilder.append(".");
            stringBuilder.append(douBan.getSubjects().get(i).getTitle());
            stringBuilder.append("\n");
        }
        mContent.setText(stringBuilder.toString());
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }
}

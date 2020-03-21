package com.reinhard.androidlabs.lab;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.reinhard.androidlabs.R;

/**
 * 示例页面。
 *
 * @author Reinhard（李剑波）
 * @date 2020/3/21
 */
public class DemoLabActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_demo_lab);
    }
}

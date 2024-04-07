package com.reinhard.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.reinhard.androidlabs.lab.DemoLabActivity;
import com.reinhard.androidlabs.lab.MoneyInputActivity;
import com.reinhard.androidlabs.lab.threeD.ThreeDMovementActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页（应用入口）。
 *
 * @author Reinhard（李剑波）
 * @date 2020/3/21
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 二级页面数据列表
     */
    private static final List<ActivityEntity> ACTIVITY_ENTITIES = new ArrayList<>();

    // 初始化二级页面列表
    static {
        // 示例页面
        ACTIVITY_ENTITIES.add(new ActivityEntity(R.string.title_demo_lab,
                DemoLabActivity.class));
        // 【金额输入】页
        ACTIVITY_ENTITIES.add(new ActivityEntity(R.string.title_money_input,
                MoneyInputActivity.class));
        // 【球形移动】页
        ACTIVITY_ENTITIES.add(new ActivityEntity(R.string.title_3d_move,
                ThreeDMovementActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化二级页面列表
        initRvSubActivity();
    }

    /**
     * 初始化二级页面列表
     */
    private void initRvSubActivity() {
        // 获取列表控件
        RecyclerView rvSubActivity = findViewById(R.id.rv_sub_activity);
        // 线性布局
        rvSubActivity.setLayoutManager(new LinearLayoutManager(this));
        // 设置适配器
        rvSubActivity.setAdapter(new LabListAdapter(ACTIVITY_ENTITIES));
    }

    /**
     * 二级页面列表适配器
     */
    private static class LabListAdapter extends BaseQuickAdapter<ActivityEntity, BaseViewHolder> {
        LabListAdapter(List<ActivityEntity> data) {
            super(R.layout.view_item_sub_activity, data);
            // 设置项点击监听
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                    ActivityEntity activityEntity = (ActivityEntity) adapter.getData()
                            .get(position);
                    // 项点击处理
                    handleItemClick(activityEntity);
                }
            });
        }

        /**
         * 项点击处理
         */
        private void handleItemClick(@NonNull ActivityEntity activityEntity) {
            // 跳转到对应的二级页面
            Context context = getContext();
            Intent intent = new Intent(context, activityEntity.getActivityClass());
            context.startActivity(intent);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, ActivityEntity lab) {
            // 设置页面标题
            baseViewHolder.setText(R.id.tv_activity_title, lab.getTitleResId());
        }
    }
}

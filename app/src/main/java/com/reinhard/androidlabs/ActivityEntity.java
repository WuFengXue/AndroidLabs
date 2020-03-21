package com.reinhard.androidlabs;

import android.app.Activity;

/**
 * 页面数据实体。
 *
 * @author Reinhard（李剑波）
 * @date 2020/3/21
 */
public class ActivityEntity {
    /**
     * 页面标题对应的字符串ID
     */
    private int titleResId;
    /**
     * 页面对应的类
     */
    private Class<? extends Activity> activityClass;

    ActivityEntity(int titleResId, Class<? extends Activity> activityClass) {
        this.titleResId = titleResId;
        this.activityClass = activityClass;
    }

    int getTitleResId() {
        return titleResId;
    }

    Class<? extends Activity> getActivityClass() {
        return activityClass;
    }

}

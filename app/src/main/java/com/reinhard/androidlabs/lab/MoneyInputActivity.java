package com.reinhard.androidlabs.lab;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.reinhard.androidlabs.R;

/**
 * 【金额输入】页，主要分析金额输入（右对齐）的几种实现思路，产品需求如下：
 * <p>
 * 1、无输入时，提示：请输入金额
 * <p>
 * 2、有输入时显示：符号 + 金额（如：¥123）
 * <p>
 * 3、要求右对齐
 * <p>
 * 4、要求结果只返回数字部分
 * <p>
 * 该页面包含3种实现：
 * <p>
 * 1、默认：{@link TextView}（显示符号） + {@link EditText}（显示金额）
 * <p>
 * 结论：不满足需求：当输入内容宽度小于提示的宽度时，符号和金额会分离，
 * 如：¥&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;123
 * <p>
 * 2、修改字符串：{@link EditText}（同时显示符号和金额）
 * <p>
 * 在 {@link TextWatcher#afterTextChanged(Editable)} 回调中对字符进行
 * 修改然后再回写
 * <p>
 * 结论：可以满足需求，但光标可以切换到符号之前（可以禁止在符号之前输入数字）
 * <p>
 * 3、修改提示：{@link TextView}（显示符号） + {@link EditText}（显示金额）
 * <p>
 * 在 {@link TextWatcher#afterTextChanged(Editable)} 回调中对提示进行
 * 修改
 * <p>
 * 结论：可以满足需求，且不存在光标切换问题
 *
 * @author Reinhard（李剑波）
 * @date 2020/3/23
 */
public class MoneyInputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_money_input);
        setContentView(R.layout.activity_money_input);
        initView();
    }

    private void initView() {
        // 初始化默认部分的UI
        initViewOfDefault();
        // 初始化修改字符串部分的UI
        initViewOfModifyString();
        // 初始化修改提示部分的UI
        initViewOfModifyHint();
    }

    /**
     * 初始化默认部分的UI
     */
    private void initViewOfDefault() {
        // 获取控件
        final TextView tvMoneySymbol = findViewById(R.id.tv_money_symbol_default);
        final EditText etMoney = findViewById(R.id.et_money_default);
        // 设置输入变化监听
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 只在无输入时显示符号
                String text = editable.toString();
                tvMoneySymbol.setVisibility(TextUtils.isEmpty(text)
                        ? View.GONE : View.VISIBLE);
            }
        });
        // 设置提交按钮点击监听
        findViewById(R.id.btn_submit_default)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 吐司展示当前输入金额
                        String moneyStr = etMoney.getText()
                                .toString();
                        toastMoney(TextUtils.isEmpty(moneyStr)
                                ? 0.0d
                                : Double.parseDouble(moneyStr));
                    }
                });
    }

    /**
     * 初始化修改字符串部分的UI
     */
    private void initViewOfModifyString() {
        final String MONEY_SYMBOL = getString(R.string.common_rmb_symbol);
        // 获取控件
        final EditText etMoney = findViewById(R.id.et_money_modify_string);
        // 设置输入变化监听
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                // 空字符
                if (TextUtils.isEmpty(text)) {
                    // do nothing
                }
                // 删除金额部分的最后一个字符
                else if (text.equals(MONEY_SYMBOL)) {
                    // 隐藏金额符号
                    etMoney.setText("");
                }
                // 输入金额部分的第一个字符
                else if (!text.contains(MONEY_SYMBOL)) {
                    // 前面追加金额符号
                    String newStr = MONEY_SYMBOL.concat(text);
                    etMoney.setText(newStr);
                    // 光标切到尾部
                    etMoney.setSelection(newStr.length());
                }
                // 光标切到金额符号前面，然后输入内容
                else if (!text.startsWith(MONEY_SYMBOL)) {
                    // 忽略新输入的内容
                    String newStr = text.substring(text.indexOf(MONEY_SYMBOL));
                    etMoney.setText(newStr);
                }
            }
        });
        // 设置提交按钮点击监听
        findViewById(R.id.btn_submit_modify_string)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 吐司展示当前输入金额
                        String moneyStr = etMoney.getText()
                                .toString();
                        toastMoney(TextUtils.isEmpty(moneyStr)
                                ? 0.0d
                                // 注意：此处需截取符号（首个字符）之后的内容
                                : Double.parseDouble(moneyStr.substring(1)));
                    }
                });
    }

    /**
     * 初始化修改提示部分的UI
     */
    private void initViewOfModifyHint() {
        // 获取控件
        final TextView tvMoneySymbol = findViewById(R.id.tv_money_symbol_modify_hint);
        final EditText etMoney = findViewById(R.id.et_money_modify_hint);
        // 设置输入变化监听
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                // 只在无输入时显示符号
                tvMoneySymbol.setVisibility(TextUtils.isEmpty(text)
                        ? View.GONE : View.VISIBLE);
                // 在有输入时将提示置空，以解决符号和金额分离的问题（思路核心点）
                etMoney.setHint(TextUtils.isEmpty(text)
                        ? getString(R.string.money_input_hint) : "");
            }
        });
        // 设置提交按钮点击监听
        findViewById(R.id.btn_submit_modify_hint)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 吐司展示当前输入金额
                        String moneyStr = etMoney.getText()
                                .toString();
                        toastMoney(TextUtils.isEmpty(moneyStr)
                                ? 0.0d
                                : Double.parseDouble(moneyStr));
                    }
                });
    }

    /**
     * 吐司展示当前输入金额
     *
     * @param money 当前输入金额
     */
    private void toastMoney(double money) {
        String formatStr = getString(R.string.money_input_toast_format);
        Toast.makeText(this, String.format(formatStr, money), Toast.LENGTH_SHORT)
                .show();
    }
}

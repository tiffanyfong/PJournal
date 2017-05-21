package com.tmf.pjournal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NumberPicker extends RelativeLayout {
    private static final int MIN_VALUE = 0;
    private ImageView ivDecrease;
    private ImageView ivIncrease;
    private TextView tvKey;
    private TextView tvValue;
    private int value;
    private LayoutInflater mInflater;

    public NumberPicker(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public NumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public NumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        View v = mInflater.inflate(R.layout.number_picker, this, true);
        value = 0;
        ivDecrease = (ImageView) v.findViewById(R.id.ivDecrease);
        ivIncrease = (ImageView) v.findViewById(R.id.ivIncrease);
        tvKey = (TextView) v.findViewById(R.id.tvKey);
        tvValue = (TextView) v.findViewById(R.id.tvValue);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        ivDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        ivIncrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
    }

    public void setKey(String s) {
        tvKey.setText(s);
    }

    public void setValue(int i) {
        tvValue.setText(String.valueOf(i));
    }

    public int getValue() {
        int value = 0;
        try {
            value = Integer.parseInt(tvValue.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private void increment() {
        value++;
        setValue(value);
    }

    private void decrement() {
        if (value > MIN_VALUE) {
            value--;
            setValue(value);
        }
    }


}

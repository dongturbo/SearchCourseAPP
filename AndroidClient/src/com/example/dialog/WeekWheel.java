package com.example.dialog;

import kankan.wheel.widget.adapters.ArrayWheelAdapter;

import com.example.schedule.R;
import com.example.wheelview.WheelView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeekWheel extends Dialog {

	private Context context;
	private TextView title;
	private Button OK;
	private Button cancel;
	private int wheelItemIndex;
	private String[][] terms = null;
	private WheelView wheelViewLeft;
	private WheelView wheelViewRight;

	public WeekWheel(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public WeekWheel(Context context, int theme, String[][] terms,
			int wheelItemIndex) {
		super(context, theme);
		this.context = context;
		this.terms = terms;
		this.wheelItemIndex = wheelItemIndex;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_week_number_wheel);

		title = (TextView) findViewById(R.id.wheel_view_txv_title);
		title.setGravity(Gravity.CENTER_HORIZONTAL);

		wheelViewLeft = (WheelView) findViewById(R.id.wheel_view_left);
		wheelViewRight = (WheelView) findViewById(R.id.wheel_view_right);

		title.setText("提示");
		OK = (Button) findViewById(R.id.wheel_view_btn_submit);
		OK.setText("确定");
		cancel = (Button) findViewById(R.id.wheel_view_btn_cancel);
		cancel.setText("取消");

		// 为左边的wheelView设置适配器
		ArrayWheelAdapter<String> adapter1 = new ArrayWheelAdapter<String>(
				context, terms[0]);
		adapter1.setTextSize(16);
		wheelViewLeft.setViewAdapter(adapter1);
		wheelViewLeft.setCurrentItem(wheelItemIndex);

		// 为右边的wheelView设置适配器
		ArrayWheelAdapter<String> adapter3 = new ArrayWheelAdapter<String>(
				context, terms[1]);
		adapter3.setTextSize(16);
		wheelViewRight.setViewAdapter(adapter3);
		wheelViewRight.setCurrentItem(wheelItemIndex);

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WeekWheel.this.dismiss();

			}
		});

	}

	// 判断提示按钮

	public int getItemID(int i) {
		int count = 0;

		if (i == 0) {
			count = wheelViewLeft.getCurrentItem();

		} else if (i == 1) {
			count = wheelViewRight.getCurrentItem();
		}

		return count;
	}

	public void setPositiveButton(String text,
			final View.OnClickListener listener) {
		OK.setText(text);
		OK.setOnClickListener(listener);
	}

	public void setNegativeButton(String text,
			final View.OnClickListener listener) {
		cancel.setText(text);
		cancel.setOnClickListener(listener);
	}

	public void setTitle(String tit) {
		title.setText(tit);
	}
}

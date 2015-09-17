package com.example.dialog;

import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schedule.R;
import com.example.wheelview.WheelView;

public class OneWheel extends Dialog{
	private Context context;
	private TextView title;
	private Button OK;
	private Button cancel;
	// WheelView wheelView;
	private int wheelItemIndex;
	private String[] terms = null;
	private WheelView wheelView;


	public OneWheel(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public OneWheel(Context context, int theme, String[] terms,
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
		setContentView(R.layout.one_wheel);
		
		title = (TextView) findViewById(R.id.wheel_view_txv_title);
		title.setGravity(Gravity.CENTER_HORIZONTAL);
		
		 wheelView = (WheelView) findViewById(R.id.wheel_view_left);
		 
		 
		title.setText("提示");
		OK = (Button) findViewById(R.id.wheel_view_btn_submit);
		OK.setText("确定");
		cancel = (Button) findViewById(R.id.wheel_view_btn_cancel);
		cancel.setText("取消");
		
		 // 为左边的wheelView设置适配器
		ArrayWheelAdapter<String> adapter=new ArrayWheelAdapter<String>(context, terms);  
		adapter.setTextSize(16);
		  wheelView.setViewAdapter(adapter);
		 wheelView.setCurrentItem(wheelItemIndex);
		  
		 

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OneWheel.this.dismiss();

			}
		});

	}

	// 判断提示按钮

	public int getItemID(int i){
				
		return	wheelView.getCurrentItem();
			
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

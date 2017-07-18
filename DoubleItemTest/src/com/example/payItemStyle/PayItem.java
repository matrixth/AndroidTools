package com.example.payItemStyle;

import com.example.doubleitemtest.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PayItem extends RelativeLayout {
	private RelativeLayout relativeLayout1;// 容器包含项目
	private TextView tv_pay_charge;// 收费金额
	private TextView tv_detail;// 详情描述
	private TextView tv_description;// 标题栏
	private Boolean isSelected = false;// 是否被选中

	private String charge;
	private String detail;
	private String description;
	private MyItemClicked myItemClicked;

	public PayItem(Context context) {
		this(context, null);
	}

	public PayItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.pay_item_layout, this);
		relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
		tv_pay_charge = (TextView) findViewById(R.id.tv_pay_charge);
		tv_description = (TextView) findViewById(R.id.tv_description);
		tv_detail = (TextView) findViewById(R.id.tv_detail);
		relativeLayout1.setOnClickListener(new MyOnClick());
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
		tv_pay_charge.setText("￥" + charge);
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
		tv_detail.setText(detail);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		tv_description.setText(description);
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
		if (isSelected) {
			relativeLayout1.setBackgroundResource(R.drawable.selectedborder);
		} else {
			relativeLayout1.setBackgroundResource(R.drawable.border);
		}
	}

	private class MyOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
		/*	if (!isSelected) {
				relativeLayout1.setBackgroundResource(R.drawable.selectedborder);
				myItemClicked.myItemClicked();
			} else {
				relativeLayout1.setBackgroundResource(R.drawable.border);
				myItemClicked.myItemClicked();
			}*/
			myItemClicked.myItemClicked();
		}
	}

	public interface MyItemClicked {
		public void myItemClicked();
	}

	public void setMyItemClickedListener(MyItemClicked myItemClicked) {
		this.myItemClicked = myItemClicked;
	}
}

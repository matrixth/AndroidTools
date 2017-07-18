package com.example.doubleitemtest;

import java.util.List;

import com.example.bean.Payment;
import com.example.payItemStyle.PayItem;
import com.example.payItemStyle.PayItem.MyItemClicked;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private List<Payment> mList;
	private Context mContext;
	private int sumCount;
	private ChangeTextView changeTextView;

	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:

				changeTextView.changeTheText(msg.arg1);
				break;

			default:
				break;
			}
		};
	};

	public MyAdapter(List<Payment> List, Context context, ChangeTextView changeTextView) {
		this.mList = List;
		this.mContext = context;
		this.changeTextView = changeTextView;
	}

	@Override
	public int getCount() {
		int count = mList.size();
		if (count % 2 == 0) {
			sumCount = count / 2; // 如果是双数直接减半
		} else {
			sumCount = (int) Math.floor((double) count / 2) + 1;
		}
		return sumCount;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		PayItem payitem1;
		PayItem payitem2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_pay_item, null);
			holder.payitem1 = (PayItem) convertView.findViewById(R.id.payItem1);
			holder.payitem2 = (PayItem) convertView.findViewById(R.id.payItem2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.payitem1.setDescription(mList.get(position * 2).getDescription());
		holder.payitem1.setDetail(mList.get(position * 2).getDetail());
		holder.payitem1.setCharge(mList.get(position * 2).getCharge());
		holder.payitem1.setIsSelected(mList.get(position * 2).getStatus());
		if (position * 2 + 1 == mList.size()) {
			holder.payitem2.setVisibility(View.INVISIBLE); // 如果是单数的话，那么最后一个item，右侧内容为空
		} else {
			holder.payitem2.setVisibility(View.VISIBLE); // 必须进行设置，负责存在复用holder的时候，会出现右侧的出现留白，跟最后一个一样，这个也是我写这篇文章最想锁的
			holder.payitem2.setDescription(mList.get(position * 2 + 1).getDescription());
			holder.payitem2.setDetail(mList.get(position * 2 + 1).getDetail());
			holder.payitem2.setCharge(mList.get(position * 2 + 1).getCharge());
			holder.payitem2.setIsSelected(mList.get(position * 2+1).getStatus());
		}

		holder.payitem1.setMyItemClickedListener(new MyOnEvenClick(position));
		holder.payitem2.setMyItemClickedListener(new MyOnOddClick(position));
		return convertView;
	}

	private class MyOnEvenClick implements MyItemClicked {
		int pos = 0;

		public MyOnEvenClick(int position) {
			this.pos = position * 2;
		}

		@Override
		public void myItemClicked() {
			Message message = new Message();
			message.what = 1;
			message.arg1 = pos;
			handler.sendMessage(message);
		}

	}

	private class MyOnOddClick implements MyItemClicked {
		int pos = 0;

		public MyOnOddClick(int position) {
			this.pos = position * 2 + 1;
		}

		@Override
		public void myItemClicked() {
			Message message = new Message();
			message.what = 1;
			message.arg1 = pos;
			handler.sendMessage(message);
		}

	}

	// 设置监听
	interface ChangeTextView {
		void changeTheText(int position);
	}

}

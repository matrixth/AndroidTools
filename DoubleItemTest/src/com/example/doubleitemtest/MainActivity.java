package com.example.doubleitemtest;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.Payment;
import com.example.doubleitemtest.MyAdapter.ChangeTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lv_doubleList;
	private MyAdapter myAdapter;
	private List<Payment> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_doubleList = (ListView) findViewById(R.id.lv_doubleList);
		initData();
		myAdapter = new MyAdapter(dataList, getApplicationContext(), new ChangeTextView() {
			
			@Override
			public void changeTheText(int position) {
				String showText = dataList.get(position).getCharge();
				Toast.makeText(getApplicationContext(), showText, 1).show();
				for (Payment payment : dataList) {
					payment.setStatus(false);
				}
				dataList.get(position).setStatus(true);
				myAdapter.notifyDataSetChanged();
			}
		});
		lv_doubleList.setAdapter(myAdapter);
	}

	private void initData() {
		dataList = new ArrayList<Payment>();
		for (int i = 0; i <= 10; i++) {
			Payment payment = new Payment();
			payment.setCharge("6"+i);
			payment.setDescription("XX·Ñ"+i);
			payment.setDetail("ÕâÊÇ"+i);
			dataList.add(payment);
		}
	}
}

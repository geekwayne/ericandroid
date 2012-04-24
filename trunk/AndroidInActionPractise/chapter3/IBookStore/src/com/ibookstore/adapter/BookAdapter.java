package com.ibookstore.adapter;

import java.util.List;

import com.ibookstore.data.Book;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

	private Context context;
	private List<Book> books;

	public BookAdapter(Context context, List<Book> books) {
		this.context = context;
		this.books = books;
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book book = books.get(position);
		return new BookListView(context, book); 
	}

	private final class BookListView extends LinearLayout {
		
		private TextView lbl_name;
		private TextView lbl_tip;
		
		public BookListView(Context context, Book book) {
			super(context);
			setOrientation(LinearLayout.VERTICAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 3, 5, 0);
			
			this.lbl_name = new TextView(context);
			this.lbl_name.setText(book.getName());
			this.lbl_name.setTextSize(16f);
			this.lbl_name.setTextColor(Color.BLACK);
			addView(lbl_name, params);
			
			this.lbl_tip  = new TextView(context);
			this.lbl_tip.setText("作者："+book.getAuthor()+" 价格："+book.getPrice());
			this.lbl_tip.setTextSize(12f);
			this.lbl_tip.setTextColor(Color.GRAY);
			
			addView(lbl_tip, params);
		}

	}
}

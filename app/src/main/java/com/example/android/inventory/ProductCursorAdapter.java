package com.example.android.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.inventory.data.productContract;

import static android.R.attr.id;

/**
 * Created by cheny on 2016/11/26.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c)  {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context,  Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.main_name);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.main_quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.main_price);
        final TextView sell_timeTextView = (TextView) view.findViewById(R.id.main_sell_times);


        int nameColumnIndex = cursor.getColumnIndex(productContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(productContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(productContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        int sell_timeColumnIndex = cursor.getColumnIndex(productContract.ProductEntry.COLUMN_PRODUCT_SELL_TIMES);

        String productName = cursor.getString(nameColumnIndex);
        int productQuantity = cursor.getInt(quantityColumnIndex );
        float productPrice = cursor.getFloat(priceColumnIndex);
        int productSell_time = cursor.getInt( sell_timeColumnIndex);

        int id = cursor.getInt(cursor.getColumnIndex(productContract.ProductEntry._ID));
        final Uri currentProductUri = ContentUris.withAppendedId(productContract.ProductEntry.CONTENT_URI, id);

        Button sell_1_item = (Button) view.findViewById(R.id.main_sell);
        sell_1_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity;
                int sell_times;
                if (quantityTextView.getText().toString().trim() == null) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(quantityTextView.getText().toString().trim());
                }

                if (sell_timeTextView.getText().toString().trim() == null) {
                    sell_times = 0;
                } else {
                    sell_times = Integer.parseInt(sell_timeTextView.getText().toString().trim());
                }

                quantity--;
                if (quantity < 0) {
                    quantity = 0;
                } else {
                    sell_times++;
                }

                quantityTextView.setText(String.valueOf(quantity));
                sell_timeTextView.setText(String.valueOf(sell_times));

                //update in database
                ContentValues values = new ContentValues();
                values.put(productContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
                values.put(productContract.ProductEntry.COLUMN_PRODUCT_SELL_TIMES, sell_times);

                context.getContentResolver().update(currentProductUri, values, null, null);
            }
        });


        if (TextUtils.isEmpty(productName)) {
            productName = "Unknown";
        }
        nameTextView.setText(productName);

        if(productQuantity < 0 ) {
            productQuantity = 0;
        }
        quantityTextView.setText(String.valueOf(productQuantity));

        if(productPrice < 0) {
            productPrice = 0;
        }
        priceTextView.setText(String.valueOf(productPrice));

        if(productSell_time < 0) {
            productSell_time = 0;
        }
        sell_timeTextView.setText(String.valueOf(productSell_time));


    }


}

/*
 * Copyright (C) 2015 Randomly Typing LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.randomlytyping.lovingleanlayouts.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.Example;
import com.randomlytyping.lovingleanlayouts.Intents;
import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatActivitySetup;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AppCompatActivitySetup.with(this)
                .supportAppBar(R.id.app_bar);

        final RecyclerView exampleList = ButterKnife.findById(this, R.id.list);
        exampleList.setLayoutManager(new LinearLayoutManager(this));
        exampleList.setAdapter(new ExampleAdapter(this));
    }

    //
    // Activity callbacks
    //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(Intents.newAboutIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //
    // Inner classes
    //

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} subclass that holds references to
     * views for each layout example.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //
        // Fields
        //

        private TextView mTextView;
        private Example mExample;

        //
        // Constructors
        //

        /**
         * Constructor
         *
         * @param itemView View for the item.
         */
        ViewHolder(View itemView) {
            super(itemView);

            mExample = Example.HIERARCHY_VIEW;

            if (itemView instanceof TextView) {
                mTextView = (TextView) itemView;
                mTextView.setOnClickListener(this);
            }
        }

        void setExample(Example example) {
            this.mExample = example;
            mTextView.setCompoundDrawablesWithIntrinsicBounds(example.iconResId, 0, 0, 0);
            mTextView.setText(example.stringResId);
        }

        //
        // View.OnClickListener implementation
        //

        @Override
        public void onClick(View v) {
            final Intent intent = Intents.newExampleIntent(MainActivity.this, mExample);
            if (intent != null) {
                startActivity(intent);
            }
        }
    }

    class ExampleAdapter extends RecyclerView.Adapter<ViewHolder> {
        //
        // Fields
        //
        private final LayoutInflater mInflater;

        //
        // Constructors
        //

        /**
         * Constructor.
         *
         * @param context The current context.
         */
        ExampleAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        //
        // RecyclerView.Adapter<ViewHolder> implementation
        //

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return R.id.item_type_subheader;
            } else {
                return R.id.item_type_default;
            }
        }

        @Override
        public int getItemCount() {
            return 1 + Example.getExampleCount();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = viewType == R.id.item_type_subheader
                    ? mInflater.inflate(R.layout.list_subheader, parent, false)
                    : mInflater.inflate(R.layout.list_item_example, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == R.id.item_type_default) {
                holder.setExample(Example.getExample(position - 1));
            }
        }
    }
}

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
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.Example;
import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatActivitySetup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @since 2015.10.22
 */
public class CustomViewsActivity extends BaseActivity {
    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AppCompatActivitySetup.with(this)
                .supportAppBar(R.id.app_bar)
                .upButton();

        final RecyclerView list = ButterKnife.findById(this, R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ExampleAdapter(this));
    }

    //
    // Inner classes
    //

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} subclass that holds references to
     * views for each layout example.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        //
        // Fields
        //

        @Bind(R.id.icon)
        ImageView mIconView;

        @Bind(R.id.title)
        TextView mTitleView;

        @Bind(R.id.subtitle)
        TextView mSubtitleView;

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

            ButterKnife.bind(this, itemView);

        }

        //
        // Getters/Setters
        //

        void setExample(Example example) {
            mIconView.setImageResource(example.iconResId);
            mTitleView.setText(example.stringResId);
            final String resourceEntryName = getResources().getResourceEntryName(example.resId);
            mSubtitleView.setText(String.format(getString(R.string.custom_view_resource_id_format),
                    resourceEntryName));
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
        public int getItemCount() {
            return Example.getExampleCount();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.list_item_custom, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setExample(Example.getExample(position));
        }
    }
}

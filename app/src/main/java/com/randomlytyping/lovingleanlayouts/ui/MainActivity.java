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
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.Intents;
import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatUtil;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatUtil.setToolbarAppBar(this, R.id.app_bar);

        final RecyclerView exampleList = ButterKnife.findById(this, R.id.example_list);
        exampleList.setLayoutManager(new LinearLayoutManager(this));
        exampleList.setAdapter(new ExampleAdapter(this));
    }

    //
    // Inner classes
    //

    enum Example {
        HIERARCHY_VIEW(R.id.example_hierarchy_viewer, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_hierarchy_viewer),
        ATTRIBUTES(R.id.example_attributes, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_attributes),
        SIMPLE_VIEWS(R.id.example_simple_views, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_simple_views);

        //
        // Fields
        //

        final int resId;
        final int iconResId;
        final int stringResId;

        //
        // Constructors
        //

        Example(@IdRes int resId, @DrawableRes int iconResId, @StringRes int stringResId) {
            this.resId = resId;
            this.iconResId = iconResId;
            this.stringResId = stringResId;
        }

        //
        // Example list helpers
        //

        static int getExampleCount() {
            return 3;
        }

        static Example getExample(int position) {
            switch (position) {
                case 1:
                    return ATTRIBUTES;
                case 2:
                    return SIMPLE_VIEWS;
                default:
                    return HIERARCHY_VIEW;
            }
        }
    }

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} subclass that holds references to
     * views for each layout example.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //
        // Fields
        //

        Example example;
        TextView textView;

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

            example = Example.HIERARCHY_VIEW;

            if (itemView instanceof TextView) {
                textView = (TextView) itemView;
                textView.setOnClickListener(this);
            }
        }

        void setExample(Example example) {
            textView.setCompoundDrawablesWithIntrinsicBounds(example.iconResId, 0, 0, 0);
            textView.setText(example.stringResId);
        }

        //
        // View.OnClickListener implementation
        //

        @Override
        public void onClick(View v) {
            final Context context = MainActivity.this;
            switch (example) {
                case HIERARCHY_VIEW:
                    startActivity(Intents.newHierarchyViewerIntent(context));
                    break;
                case ATTRIBUTES:
                    startActivity(Intents.newAttributeExamplesIntent(context));
                    break;
                case SIMPLE_VIEWS:
                    startActivity(Intents.newSimpleViewExamplesIntent(context));
                    break;
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
        public ExampleAdapter(Context context) {
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

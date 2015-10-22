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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        AppCompatActivitySetup.with(this).supportAppBar(R.id.app_bar);

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

    enum Example {
        HIERARCHY_VIEW(R.id.example_hierarchy_viewer, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_hierarchy_viewer),
        LINT_WARNINGS(R.id.example_lint_warnings, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_lint_warnings),
        ATTRIBUTES(R.id.example_attributes, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_attributes),
        SIMPLER_VIEWS(R.id.example_simpler_views, R.drawable.ic_view_quilt_white_36dp,
                R.string.example_simpler_views);

        private static final Example[] EXAMPLES_ALL = {HIERARCHY_VIEW, LINT_WARNINGS, ATTRIBUTES, SIMPLER_VIEWS};

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
            return EXAMPLES_ALL.length;
        }

        static Example getExample(int position) {
            return position >= 0 || position < EXAMPLES_ALL.length
                    ? EXAMPLES_ALL[position]
                    : HIERARCHY_VIEW;
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
            final Context context = MainActivity.this;
            switch (mExample) {
                case HIERARCHY_VIEW:
                    startActivity(Intents.newHierarchyViewerIntent(context));
                    break;
                case LINT_WARNINGS:
                    startActivity(Intents.newLintWarningsIntent(context));
                    break;
                case ATTRIBUTES:
                    startActivity(Intents.newAttributesIntent(context));
                    break;
                case SIMPLER_VIEWS:
                    startActivity(Intents.newSimplerViewsExamplesIntent(context));
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

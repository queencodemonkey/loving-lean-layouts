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

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatActivitySetup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @since 2015.10.22
 */
public class OnTheFlyActivity extends BaseActivity {
    //
    // Fields
    //

    @BindView(R.id.inflate_container)
    ViewGroup inflateContainer;

    private LayoutInflater mInflater;
    private boolean mInflateSecond;

    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_the_fly);
        AppCompatActivitySetup.with(this)
                .supportAppBar(R.id.app_bar)
                .upButton();
        ButterKnife.bind(this);

        mInflater = LayoutInflater.from(this);
    }

    //
    // Listeners
    //

    @OnClick(R.id.inflate_button)
    void onInflateClick() {
        inflateContainer.removeAllViewsInLayout();
        if (mInflateSecond) {
            mInflater.inflate(R.layout.view_inflate_merge_example, inflateContainer, true);
        } else {
            mInflater.inflate(R.layout.view_inflate_example, inflateContainer, true);
        }
        mInflateSecond = !mInflateSecond;
    }

    @OnClick(R.id.stub_button)
    void onStubClick() {
        final ViewStub stub;
        stub = ButterKnife.findById(this, R.id.stub_01);

        if (stub != null) {
            stub.inflate();
            mInflateSecond = true;
        }
    }
}

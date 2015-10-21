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

package com.randomlytyping.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.randomlytyping.lovingleanlayouts.R;

/**
 * @since 2015.10.20
 */
public class AppCompatActivitySetup {
    //
    // Fields
    //

    private final AppCompatActivity mActivity;
    private Toolbar mToolbar;

    /**
     * Returns a new ActivitySetup instance.
     *
     * @return A new ActivitySetup instance.
     */
    public static AppCompatActivitySetup with(@NonNull AppCompatActivity activity) {
        return new AppCompatActivitySetup(activity);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public AppCompatActivitySetup supportAppBar(@IdRes int toolbarId) {
        mToolbar = (Toolbar) mActivity.findViewById(toolbarId);
        if (mToolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
        }
        return this;
    }

    public AppCompatActivitySetup upButton() {
        final ActionBar supportActionBar = mActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        return this;
    }

    public AppCompatActivitySetup drawerButton() {
        final ActionBar supportActionBar = mActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDefaultDisplayHomeAsUpEnabled(false);
        }
        return this;
    }

    public AppCompatActivitySetup hideHomeButton() {
        final ActionBar supportActionBar = mActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(false);
        }
        return this;
    }

    //
    // Constructors
    //

    /**
     * Private constructor to prevent instantiation.
     */
    private AppCompatActivitySetup(AppCompatActivity activity) {
        mActivity = activity;
    }
}

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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @since 2015.10.20
 */
public class AppCompatUtil {
    /**
     * Locates a {@link Toolbar} with a particular view ID in an {@link AppCompatActivity}'s layout
     * and if found, uses it as the activity's action bar.
     *
     * @param activity  An activity.
     * @param toolbarId The view ID of the {@link Toolbar} to use as {@code activity}'s app bar.
     *
     * @return {@code true} if {@code activity} has a {@link Toolbar} with {@code toolbarId} as its
     * view ID; {@code false} otherwise.
     */
    public static boolean setToolbarAppBar(AppCompatActivity activity, @IdRes int toolbarId) {
        final Toolbar toolbar = (Toolbar) activity.findViewById(toolbarId);
        if (toolbar == null) {
            return false;
        }
        activity.setSupportActionBar(toolbar);
        return true;
    }

    public static void enableUpButton(AppCompatActivity activity) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}

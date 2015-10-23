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

package com.randomlytyping.lovingleanlayouts;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

/**
 * @since 2015.10.22
 */
public enum Example {
    HIERARCHY_VIEW(R.id.example_hierarchy_viewer, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_hierarchy_viewer),
    LINT_WARNINGS(R.id.example_lint_warnings, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_lint_warnings),
    ATTRIBUTES(R.id.example_attributes, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_attributes),
    SIMPLER_VIEWS(R.id.example_simpler_views, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_simpler_views),
    ON_THE_FLY(R.id.example_on_the_fly, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_on_the_fly),
    FIXED_IT(R.id.example_fixed_it, R.drawable.ic_view_quilt_white_36dp,
            R.string.example_fixed_it);

    private static final Example[] EXAMPLES_ALL = {
            HIERARCHY_VIEW, LINT_WARNINGS, ATTRIBUTES, SIMPLER_VIEWS, ON_THE_FLY};

    //
    // Fields
    //

    public final int resId;
    public final int iconResId;
    public final int stringResId;

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

    public static int getExampleCount() {
        return EXAMPLES_ALL.length;
    }

    public static Example getExample(int position) {
        return position >= 0 || position < EXAMPLES_ALL.length
                ? EXAMPLES_ALL[position]
                : HIERARCHY_VIEW;
    }
}

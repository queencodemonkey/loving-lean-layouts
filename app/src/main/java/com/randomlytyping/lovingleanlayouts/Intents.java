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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.randomlytyping.lovingleanlayouts.ui.AboutActivity;
import com.randomlytyping.lovingleanlayouts.ui.AttributesActivity;
import com.randomlytyping.lovingleanlayouts.ui.HierarchyViewerActivity;
import com.randomlytyping.lovingleanlayouts.ui.LintWarningsActivity;
import com.randomlytyping.lovingleanlayouts.ui.OnTheFlyActivity;
import com.randomlytyping.lovingleanlayouts.ui.SimplerViewsActivity;

/**
 * @since 2015.10.19
 */
public class Intents {
    //
    // Intent factory methods
    //

    public static Intent newExampleIntent(Context context, Example example) {
        switch (example) {
            case HIERARCHY_VIEW:
                return new Intent(context, HierarchyViewerActivity.class);
            case ATTRIBUTES:
                return new Intent(context, AttributesActivity.class);
            case LINT_WARNINGS:
                return new Intent(context, LintWarningsActivity.class);
            case SIMPLER_VIEWS:
                return new Intent(context, SimplerViewsActivity.class);
            case ON_THE_FLY:
                return new Intent(context, OnTheFlyActivity.class);
            default:
                return null;
        }
    }

    public static Intent newAboutIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    public static Intent newUrlIntent(String url) {
        final Uri uri = Uri.parse(url);
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        return intent;
    }
}
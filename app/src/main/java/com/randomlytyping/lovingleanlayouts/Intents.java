package com.randomlytyping.lovingleanlayouts;

import android.content.Context;
import android.content.Intent;

import com.randomlytyping.lovingleanlayouts.ui.AttributesActivity;
import com.randomlytyping.lovingleanlayouts.ui.HierarchyViewerActivity;
import com.randomlytyping.lovingleanlayouts.ui.SimpleViewsActivity;

/**
 * @since 2015.10.19
 */
public class Intents {
    //
    // Intent factory methods
    //

    public static Intent newHierarchyViewerIntent(Context context) {
        return new Intent(context, HierarchyViewerActivity.class);
    }

    public static Intent newAttributeExamplesIntent(Context context) {
        return new Intent(context, AttributesActivity.class);
    }

    public static Intent newSimpleViewExamplesIntent(Context context) {
        return new Intent(context, SimpleViewsActivity.class);
    }
}

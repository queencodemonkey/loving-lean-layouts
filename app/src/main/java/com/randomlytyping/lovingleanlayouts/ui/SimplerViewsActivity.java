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

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatActivitySetup;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * @since 2015.10.21
 */
public class SimplerViewsActivity extends BaseActivity {
    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpler_views);
        AppCompatActivitySetup.with(this)
                .supportAppBar(R.id.app_bar)
                .upButton();

        //<editor-fold desc="Some Typeface Loading">
        final AssetManager assets = getAssets();
        final Typeface typefaceA =
                TypefaceUtils.load(assets, getString(R.string.font_path_merriweather_regular));
        final Typeface typefaceB =
                TypefaceUtils.load(assets, getString(R.string.font_path_clear_sans));
        //</editor-fold>

        final SpannableStringBuilder builder =
                new SpannableStringBuilder(getString(R.string.simpler_views_text_B02));
        int start = 0;
        int end = builder.length();
        builder.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance_LLL_DialogueB),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new CalligraphyTypefaceSpan(typefaceB),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //<editor-fold desc="More Spannable Stuff…">
        start = end;
        builder.append("\n\n").append(getString(R.string.simpler_views_text_A03));
        end = builder.length();
        builder.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance_LLL_DialogueA),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new CalligraphyTypefaceSpan(typefaceA),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        start = end;
        builder.append("\n\n").append(getString(R.string.simpler_views_text_B03));
        end = builder.length();
        builder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance_LLL_DialogueB),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new CalligraphyTypefaceSpan(typefaceB),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //</editor-fold>

        final TextView textView = ButterKnife.findById(this, R.id.simple_text);
        textView.setText(builder, TextView.BufferType.SPANNABLE);
    }
}

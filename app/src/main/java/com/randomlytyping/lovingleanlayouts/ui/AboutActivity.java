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
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.Intents;
import com.randomlytyping.lovingleanlayouts.R;
import com.randomlytyping.util.AppCompatActivitySetup;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * @since 2015.10.21
 */
public class AboutActivity extends BaseActivity {
    //
    // Activity lifecycle
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        AppCompatActivitySetup.with(this).supportAppBar(R.id.app_bar).upButton();

        final RecyclerView attributionList = ButterKnife.findById(this, R.id.list);
        attributionList.setLayoutManager(new LinearLayoutManager(this));
        attributionList.setAdapter(new AboutAdapter(this));
    }

    //
    // Enums
    //

    enum Attribution {

        ICON_TARDIS("Tardis by Arancha R from the Noun Project",
                "https://thenounproject.com/term/tardis/55171/",
                R.drawable.ic_tardis_white_48dp,
                0),
        ICON_THREE_D_GLASSES("3D Glasses by Rigo Peter from the Noun Project",
                "https://thenounproject.com/rigo/uploads/?i=33105",
                R.drawable.ic_3d_glasses_white_48dp,
                0),
        ICON_BOWTIE("Bow Tie by Phil Goodwin from the Noun Project",
                "https://thenounproject.com/term/bowtie/7311/",
                R.drawable.ic_bowtie_white_48dp,
                0),
        ICON_SONIC_SCREWDRIVER("Tardis by Arancha R from the Noun Project",
                "https://thenounproject.com/term/tens-sonic-screwdriver/67747/",
                R.drawable.ic_tardis_white_48dp,
                0),
        FONT_ACTOR("Actor by Thomas Junold, SIL Open Font License, 1.1",
                "https://www.google.com/fonts/specimen/Actor",
                0,
                R.string.font_path_actor),
        FONT_ALEGREYA("Alegreya by Huerta Tipográfica, SIL Open Font License, 1.1",
                "https://www.google.com/fonts/specimen/Alegreya",
                0,
                R.string.font_path_alegreya),
        FONT_FIRA("Fira, Firefox OS Typeface, SIL Open Font License, 1.1",
                "https://www.mozilla.org/en-US/styleguide/products/firefox-os/typeface/",
                0,
                R.string.font_path_fira_mono_bold),
        FONT_MERRIWEATHER("Merriweather by Eben Sorkin",
                "https://www.google.com/fonts/specimen/Merriweather",
                0,
                R.string.font_path_merriweather_black);

        private static final Attribution[] ICON_ATTRIBUTIONS = {
                ICON_TARDIS, ICON_THREE_D_GLASSES, ICON_BOWTIE, ICON_SONIC_SCREWDRIVER
        };

        private static final Attribution[] FONT_ATTRIBUTIONS = {
                FONT_ACTOR, FONT_ALEGREYA, FONT_FIRA, FONT_MERRIWEATHER
        };

        private final String text;
        @DrawableRes
        private final int drawableResId;
        @StringRes
        private final int fontPath;
        private final String url;

        Attribution(String text, String url, @DrawableRes int drawableResId, @StringRes int fontPath) {
            this.text = text;
            this.drawableResId = drawableResId;
            this.fontPath = fontPath;
            this.url = url;
        }
    }

    //
    // Inner classes
    //

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Attribution mAttribution;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            if (itemView instanceof TextView) {
                mTextView = (TextView) itemView;
                mTextView.setOnClickListener(this);
            }
        }

        private void setTitle(@StringRes int titleResId) {
            mAttribution = null;
            mTextView.setText(titleResId);
        }

        private void setAttribution(Attribution attribution) {
            if (attribution == null) {
                throw new IllegalArgumentException("Cannot set " + getClass().getName()
                        + " with a null attribution.");
            }
            mAttribution = attribution;
            if (attribution.drawableResId != 0) {
                mTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        attribution.drawableResId, 0, 0, 0);
                mTextView.setText(attribution.text);
            } else {
                mTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_font_download_white_36dp, 0, 0, 0);
                final SpannableString text = new SpannableString(attribution.text);
                final Context context = mTextView.getContext();
                final Typeface typeface = TypefaceUtils.load(context.getAssets(),
                        context.getString(mAttribution.fontPath));
                text.setSpan(new CalligraphyTypefaceSpan(typeface),
                        0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                mTextView.setText(text, TextView.BufferType.SPANNABLE);
            }
        }

        @Override
        public void onClick(View v) {
            if (mAttribution == null) {
                return;
            }
            v.getContext().startActivity(Intents.newUrlIntent(mAttribution.url));
        }
    }

    private static class AboutAdapter extends RecyclerView.Adapter<ViewHolder> {
        //
        // Constants
        //
        private final static int SUBHEADER_ICON_POSITION = 0;
        private final static int SUBHEADER_FONT_POSITION = Attribution.ICON_ATTRIBUTIONS.length + 1;

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
        public AboutAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        //
        // RecyclerView.Adapter<ViewHolder> implementation
        //


        @Override
        public int getItemViewType(int position) {
            if (position == SUBHEADER_ICON_POSITION || position == SUBHEADER_FONT_POSITION) {
                return R.id.item_type_subheader;
            } else {
                return R.id.item_type_default;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = viewType == R.id.item_type_subheader
                    ? mInflater.inflate(R.layout.list_subheader, parent, false)
                    : mInflater.inflate(R.layout.list_item_default, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position == SUBHEADER_ICON_POSITION) {
                holder.setTitle(R.string.about_subheader_icons);
                return;
            }

            if (position == SUBHEADER_FONT_POSITION) {
                holder.setTitle(R.string.about_subheader_fonts);
                return;
            }

            final Attribution attribution;
            if (position > SUBHEADER_ICON_POSITION && position < SUBHEADER_FONT_POSITION) {
                attribution = Attribution.ICON_ATTRIBUTIONS[position - 1];
            } else if (position > SUBHEADER_FONT_POSITION) {
                attribution = Attribution.FONT_ATTRIBUTIONS[position - SUBHEADER_FONT_POSITION - 1];
            } else {
                attribution = null;
            }
            holder.setAttribution(attribution);
        }

        @Override
        public int getItemCount() {
            return 1 + Attribution.ICON_ATTRIBUTIONS.length
                    + 1 + Attribution.FONT_ATTRIBUTIONS.length;
        }
    }
}

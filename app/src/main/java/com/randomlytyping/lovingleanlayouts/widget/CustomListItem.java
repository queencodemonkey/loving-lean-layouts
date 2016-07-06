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

package com.randomlytyping.lovingleanlayouts.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.randomlytyping.lovingleanlayouts.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Custom {@link ViewGroup} for displaying the example list items from the launch screen but
 * without the extra measure/layout pass from the {@link android.widget.RelativeLayout}.
 *
 * The {@link #measure(int, int)} and {@link #measureChildWithMargins(View, int, int, int, int)}
 * logic was taken directly from Sriram Ramani's post on Custom ViewGroups:
 * https://sriramramani.wordpress.com/2015/05/06/custom-viewgroups/
 *
 * @since 2015.10.23
 */
public class CustomListItem extends ViewGroup {
    //
    // Fields
    //

    @Bind(R.id.icon)
    ImageView icon;

    @Bind(R.id.title)
    TextView titleView;

    @Bind(R.id.subtitle)
    TextView subtitleView;

    //
    // Constructors
    //

    /**
     * Constructor.
     *
     * @param context The current context.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public CustomListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    //
    // ViewGroup implementation
    //

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //<editor-fold desc="~30 Lines of Measure Code…">
        int widthUsed = getPaddingLeft() + getPaddingRight();
        int heightUsed = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;

        // Measure icon
        measureChildWithMargins(icon, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        widthUsed += icon.getMeasuredWidth();
        width += icon.getMeasuredWidth();
        height = Math.max(icon.getMeasuredHeight(), height);

        final int textWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(widthMeasureSpec) - widthUsed,
                MeasureSpec.getMode(widthMeasureSpec));
        final int textHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec) - heightUsed,
                MeasureSpec.getMode(heightMeasureSpec));

        // Measure title
        measureChildWithMargins(titleView, textWidthMeasureSpec, 0, textHeightMeasureSpec, 0);

        // Measure the Subtitle.
        measureChildWithMargins(subtitleView, textWidthMeasureSpec, 0,
                textHeightMeasureSpec, titleView.getMeasuredHeight());

        // Calculate measured sizes.
        width += Math.max(titleView.getMeasuredWidth(), subtitleView.getMeasuredWidth());
        height = Math.max(titleView.getMeasuredHeight() + subtitleView.getMeasuredHeight(), height);

        setMeasuredDimension(
                resolveSize(width + getPaddingLeft() + getPaddingRight(), widthMeasureSpec),
                resolveSize(height + getPaddingTop() + getPaddingBottom(), heightMeasureSpec));
        //</editor-fold>
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed,
                                           int parentHeightMeasureSpec, int heightUsed) {
        //<editor-fold desc="More Measure Stuff…">
        final MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

        int childWidthMeasureSpec = getChildMeasureSpec(
                parentWidthMeasureSpec,
                widthUsed + layoutParams.leftMargin + layoutParams.rightMargin,
                layoutParams.width);

        int childHeightMeasureSpec = getChildMeasureSpec(
                parentHeightMeasureSpec,
                heightUsed + layoutParams.topMargin + layoutParams.bottomMargin,
                layoutParams.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        //</editor-fold>
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //<editor-fold desc="~21 Lines of Layout Code…">
        MarginLayoutParams layoutParams = (MarginLayoutParams) icon.getLayoutParams();

        int x = getPaddingLeft() + layoutParams.leftMargin;
        int y = getPaddingTop() + layoutParams.topMargin;

        icon.layout(x, y, x + icon.getMeasuredWidth(), y + icon.getMeasuredHeight());

        x +=  icon.getMeasuredWidth() + layoutParams.leftMargin;
        layoutParams = (MarginLayoutParams) titleView.getLayoutParams();
        x += layoutParams.leftMargin;
        y = getPaddingTop() + layoutParams.topMargin;

        titleView.layout(x, y, x + titleView.getMeasuredWidth(), y + titleView.getMeasuredHeight());

        y += titleView.getMeasuredHeight() + layoutParams.bottomMargin;
        layoutParams = (MarginLayoutParams) subtitleView.getLayoutParams();
        y += layoutParams.topMargin;

        subtitleView.layout(x, y,
                x + subtitleView.getMeasuredWidth(), y + subtitleView.getMeasuredHeight());
        //</editor-fold>
    }
}

package com.ibrahim.widgets;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import io.codetail.widget.RevealFrameLayout;

import com.ibrahim.reactandroidcircularreveal.R;

public class CircularRevealLayout extends RevealFrameLayout {
    private RevealFrameLayout circularRevealViewContainer;

    public CircularRevealLayout(Context context) {
        super(context);

        circularRevealViewContainer = this;
    }

    public void reveal (float positionFromRight, int animationDuration) {
        View revealingView = circularRevealViewContainer.getChildAt(0);

        // start x-index for circular animation
        int cx = revealingView.getWidth() - (int) (positionFromRight);
        // start y-index for circular animation
        int cy = (revealingView.getTop() + revealingView.getBottom()) / 2;

        // calculate max radius
        int dx = Math.max(cx, revealingView.getWidth() - cx);
        int dy = Math.max(cy, revealingView.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Circular animation declaration begin
        final Animator animator;

        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(revealingView, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(animationDuration);
        revealingView.setVisibility(View.VISIBLE);
        animator.start();
    }

    public void hide (float positionFromRight, int animationDuration) {
        View revealingView = circularRevealViewContainer.getChildAt(0);

        int cx = revealingView.getWidth() - (int) (positionFromRight);

        int cy = (revealingView.getTop() + revealingView.getBottom()) / 2;

        int dx = Math.max(cx, revealingView.getWidth() - cx);
        int dy = Math.max(cy, revealingView.getHeight() - cy);

        float finalRadius = (float) Math.hypot(dx, dy);

        Animator animator;
        animator = io.codetail.animation.ViewAnimationUtils
                .createCircularReveal(revealingView, cx, cy, finalRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(animationDuration);
        revealingView.setVisibility(View.GONE);
        animator.start();
    }

    public void addRevealView (View child, int index) {
        child.setVisibility(View.GONE);

        circularRevealViewContainer.addView(child, index);
    }
}

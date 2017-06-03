package com.ibrahim.managers;

import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.ibrahim.widgets.CircularRevealLayout;

import java.util.Map;

public class CircularRevealViewManager extends ViewGroupManager<CircularRevealLayout> {
  private static final String REACT_CLASS = "ReactNativeAndroidCircularReveal";

  private static final int COMMAND_REVEAL = 1;
  private static final int COMMAND_HIDE = 2;

  private int animationDuration = 200;

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected CircularRevealLayout createViewInstance(ThemedReactContext reactContext) {
    CircularRevealLayout container = new CircularRevealLayout(reactContext);

    return container;
  }

  @ReactProp(name = "animationDuration")
  public void setAnimationDuration(CircularRevealLayout shimmerLayout, @Nullable int animationDuration) {
    this.animationDuration = animationDuration;
  }

  @Override
  public Map<String,Integer> getCommandsMap() {
    return MapBuilder.of(
            "REVEAL",
            COMMAND_REVEAL,
            "HIDE",
            COMMAND_HIDE);
  }

  @Override
  public void receiveCommand(CircularRevealLayout circularRevealLayoutLayout, int commandType, @Nullable ReadableArray args) {
    Assertions.assertNotNull(circularRevealLayoutLayout);
    Assertions.assertNotNull(args);

    switch (commandType) {
      case COMMAND_REVEAL: {
        circularRevealLayoutLayout.reveal(((float) args.getDouble(0)), this.animationDuration);
        return;
      }
      case COMMAND_HIDE: {
        circularRevealLayoutLayout.hide(((float) args.getDouble(0)), this.animationDuration);
        return;
      }
      default:
        throw new IllegalArgumentException(String.format(
                "Unsupported command %d received by %s.",
                commandType,
                getClass().getSimpleName()));
    }
  }

  @Override
  public void addView(CircularRevealLayout parent, View child, int index) {
    parent.addRevealView(child, index);
  }
}
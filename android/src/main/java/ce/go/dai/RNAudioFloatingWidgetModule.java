
package ce.go.dai;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cleveroad.audiowidget.AudioWidget;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNAudioFloatingWidgetModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  public AudioWidget widget;
  public boolean isPlaying = false;

  public RNAudioFloatingWidgetModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAudioFloatingWidget";
  }

  @ReactMethod
  public void show(ReadableMap options){

      //if(!widget.isShown()){

          //validate options
          if(options.hasKey("withOptions")){

            //all fields are mandatory, while we investigate how can we validate methods

            widget = new AudioWidget.Builder(reactContext)
                  .bubblesMinSize(options.getInt("bubblesMinSize"))
                    .bubblesMaxSize(options.getInt("bubblesMaxSize"))
                    .buttonPadding(options.getInt("buttonPadding"))
                    .darkColor(Color.parseColor(options.getString("darkColor")))
                    .lightColor(Color.parseColor(options.getString("lightColor")))
                    .crossColor(Color.parseColor(options.getString("crossColor")))
                    .crossStrokeWidth(options.getInt("crossStrokeWidth"))
                    .crossOverlappedColor(Color.parseColor(options.getString("crossOverlappedColor")))
                    .progressColor(Color.parseColor(options.getString("progressColor")))
                    .shadowColor(Color.parseColor(options.getString("shadowColor")))
                    .shadowRadius(options.getInt("shadowRadius"))
                    .shadowDx(options.getInt("shadowDx"))
                    .shadowDy(options.getInt("shadowDy"))
                    .expandWidgetColor(Color.parseColor(options.getString("expandWidgetColor")))
                    .build();
          }else{

            widget = new AudioWidget.Builder(reactContext).build();

          }

          widget.show(100,100); // Top Left Corner

          // media buttons' click listener
          widget.controller().onControlsClickListener(new AudioWidget.OnControlsClickListener() {

            @Override
            public boolean onPlaylistClicked() {
              // playlist icon clicked
              // return false to collapse widget, true to stay in expanded state
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onPlaylistClicked", params);
              return true;
            }

            @Override
            public void onPreviousClicked() {
              // previous track button clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onPreviousClicked", params);
            }

            @Override
            public boolean onPlayPauseClicked() {
              // return true to change playback state of widget and play button click animation (in collapsed state)
              WritableMap params = Arguments.createMap();
              //Log.i("isPlaying", Boolean.toString(isPlaying));
              if(!isPlaying){
                isPlaying = true;
                params.putBoolean("isPlaying",isPlaying);
                sendEvent(reactContext, "onPlayPauseClicked", params);
              }else{
                isPlaying = false;
                params.putBoolean("isPlaying",isPlaying);
                sendEvent(reactContext, "onPlayPauseClicked", params);
              }

              return false;
            }

            @Override
            public void onNextClicked() {
              // next track button clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onNextClicked", params);
            }

            @Override
            public void onAlbumClicked() {
              // album cover clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onAlbumClicked", params);
            }

            @Override
            public void onPlaylistLongClicked() {
              // playlist button long clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onPlayListLongClicked", params);
            }

            @Override
            public void onPreviousLongClicked() {
              // previous track button long clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onPreviousLongClicked", params);
            }

            @Override
            public void onPlayPauseLongClicked() {
              // play/pause button long clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onPlayPauseClicked", params);
            }

            @Override
            public void onNextLongClicked() {
              // next track button long clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onNextLongClicked", params);
            }

            @Override
            public void onAlbumLongClicked() {
              // album cover long clicked
              WritableMap params = Arguments.createMap();
              sendEvent(reactContext, "onAlbumLongClicked", params);
            }

          });

  }

  @ReactMethod
  public void isShown(Callback callback){
    if(widget.isShown()){
      callback.invoke(true);
    }else{
      callback.invoke(false);
    }
  }

  @ReactMethod
  public void hide(){
    widget.hide();
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }

}
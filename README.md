
# react-native-audio-floating-widget

This repo is like a "Chat Head" but for audio :), i hope it helps somebody.

Thanks to `Cleveroad` for `https://android-arsenal.com/details/1/3494#`

![](https://github.com/Cleveroad/MusicBobber/raw/master/images/demo.gif?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=3494)

## Getting started

`$ npm install react-native-audio-floating-widget --save`

### Mostly automatic installation

`$ react-native link react-native-audio-floating-widget`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import ce.go.dai.RNAudioFloatingWidgetPackage;` to the imports at the top of the file
  - Add `new RNAudioFloatingWidgetPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-audio-floating-widget'
  	project(':react-native-audio-floating-widget').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-audio-floating-widget/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-audio-floating-widget')
  	```
4. Add the following lines in your MainActivity.java, are mandatory in order to request permission for overlay apps in Android 6+:

```
   private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                // now you can show audio widget
            }
        }
    }
```

## Usage
```javascript
import AudioFloatingWidget from 'react-native-audio-floating-widget';

componentDidMount(){
        //use just .show(), for default colors
        AudioFloatingWidget.show({
            withOptions : true,
            bubblesMinSize : 50,
            bubblesMaxSize: 80,
            buttonPadding : 20,
            darkColor : "#000000",
            lightColor : "#ffffff",
            crossColor : "#eeeeee",
            crossStrokeWidth : 4,
            crossOverlappedColor : "#000000",
            progressColor : "#ffffff",
            shadowColor : "#cccccc",
            shadowRadius : 5,
            shadowDx : 5,
            shadowDy : 8,
            expandWidgetColor : "#f1f1f1"
        });
}

componentWillMount() {
     // this package has eventListeners that you can manage via DeviceEventEmitter;  
 
     DeviceEventEmitter.addListener(
         'onPlayPauseClicked',
         (params) => {
                alert(params.isPlaying)
         }
     );
     
     //please view available methods in docs
}
```
## Available Methods
| Name | Description |
| ----- | ----------  |
| isShown | Return true if is shown, false if not |
| hide | Destroy the widget |
| show(options || {}) | Show the widget |

## Available Listeners
| Name | Description |
| ---- | ----------- |
| onPlaylistClicked | Respond to playlist button click |
| onPreviousClicked | Respond to previous button click |
| onPlayPauseClicked | Return true or false |
| onNextClicked | Respond to next button click |
| onAlbumClicked | Respond to album button click |
| onPlaylistLongClicked | Respond to a long click playlist button |
| onPreviousLongClicked | Respond to a long click previous button |
| onPlayPauseLongClicked | Respond to a long click play/pause button |
| onNextLongClicked | Respond to a long click next button |
| onAlbumLongClicked | Respond to a long click album button |

## Available Options
| Option | Description |
| ------ | ----------- |
| withOptions | Set true for enable options |
| bubblesMinSize | Minimum size of bubbles animation |
| bubblesMaxSize | Maximim size of bubbles animation |
| buttonPadding | Padding between widget buttons |
| darkColor | Dark color of widget (String) |
| lightColor | Light color of widget (String) |
| crossColor | - String |
| crossStrokeWidth | - String |
| progressColor | - String |
| shadowColor | - String |
| shadowRadius | int |
| shadowDx | Int |
| shadowDy | Int |
| expandWidgetColor | String |

## Methods not implemented (PR)

```
.playDrawable(...)
.pauseDrawable(...)
.playlistDrawable(...)
.prevTrackDrawale(...)
.nextTrackDrawable(...)
.defaultAlbumDrawable(...)
.edgeOffsetXCollapsed(...)
.edgeOffsetYCollapsed(...)
.edgeOffsetXExpanded(...)
.edgeOffsetYExpanded(...)
```

## Of course, PR are welcome :)
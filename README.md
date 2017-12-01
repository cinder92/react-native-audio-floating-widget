
# react-native-audio-floating-widget

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


## Usage
```javascript
import RNAudioFloatingWidget from 'react-native-audio-floating-widget';

// TODO: What to do with the module?
RNAudioFloatingWidget;
```
  

import { NativeModules } from 'react-native';

const { RNAudioFloatingWidget } = NativeModules;

const AudioFloatingWidget = {
    isShown(){
        return new Promise((resolve) => {
            RNAudioFloatingWidget.isShown((response) => {
                resolve(response); 
            });
        });
    },

    show(options){
        RNAudioFloatingWidget.show(options || {});
    },

    hide(){
        RNAudioFloatingWidget.hide();
    }
}

export default AudioFloatingWidget;

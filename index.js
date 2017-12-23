
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
    },

    pause(){
        RNAudioFloatingWidget.togglepause();
    },

    play(){
        RNAudioFloatingWidget.toggleplay();
    }
}

export default AudioFloatingWidget;

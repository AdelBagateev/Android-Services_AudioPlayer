// PlayerAidlInterface.aidl
package com.example.service_audioplayer;
// Declare any non-default types here with import statements
import com.example.service_audioplayer.Music;
interface PlayerAidlInterface {
    void playMusic();

    void pauseMusic();

    void playNextMusic(in Music music);

    void playPreviousMusic(in Music music);

    void stopMusic();

    void setMusic(in Music music);
}
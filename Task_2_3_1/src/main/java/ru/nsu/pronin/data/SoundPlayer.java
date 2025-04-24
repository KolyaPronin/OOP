package ru.nsu.pronin.data;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * A utility class for managing background music playback in the application.
 * <p>
 * The SoundPlayer class provides methods
 * to play and stop background music
 * in a loop using the MediaPlayer API.
 * The class is designed to ensure that
 * only one MediaPlayer instance operates at
 * a time, avoiding potential resource
 * conflicts. It serves as an integral part
 * of the application's audio functionality.
 */
public final class SoundPlayer {
    /**
     * A static MediaPlayer instance used to manage background music playback.
     * <p>
     * This variable serves as the centralized
     * MediaPlayer object for the
     * application, enabling continuous music
     * playback in a loop. It is
     * initialized and controlled within the
     * methods of the SoundPlayer
     * class to ensure only one MediaPlayer
     * instance is active at any time.
     * <p>
     * The MediaPlayer is instantiated with
     * a predefined media file and
     * configured for indefinite looping.
     * Its lifecycle is managed entirely
     * through the class's static methods
     * , ensuring proper initialization
     * and cleanup.
     */
    private static MediaPlayer mediaPlayer;

    /**
     * A private constructor to prevent
     * instantiation of the SoundPlayer class.
     * <p>
     * This class contains only static
     * utility methods and is not meant to
     * be instantiated. Any attempt to
     * instantiate this class will result
     * in an UnsupportedOperationException.
     */
    private SoundPlayer() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Plays background music for the
     * application in a continuous loop.
     * If the music is already playing,
     * invoking this method does nothing.
     * <p>
     * The method initializes a MediaPlayer
     * instance with a predefined media
     * file and sets it to loop indefinitely.
     * It ensures that the MediaPlayer
     * instance is created and only one
     * instance is active at a time.
     * <p>
     * Note: The music file is expected to
     * be located at "/music.mp3" within
     * the application's resources.
     */
    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            // Убедитесь, что путь к файлу правильный
            Media media = new Media(SoundPlayer.class.getResource(
                    "/music.mp3").toExternalForm());


            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }

    /**
     * Stops the background music playback and
     * releases the MediaPlayer instance.
     * <p>
     * This method stops the currently playing
     * background music by calling the
     * MediaPlayer's stop method and sets the
     * MediaPlayer instance to null to
     * release resources. If no MediaPlayer
     * instance exists or music is not
     * playing, the method does nothing.
     * <p>
     * Ensures that the application's
     * background music is properly halted and
     * the MediaPlayer resources are
     * freed for proper memory management.
     */
    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }
}

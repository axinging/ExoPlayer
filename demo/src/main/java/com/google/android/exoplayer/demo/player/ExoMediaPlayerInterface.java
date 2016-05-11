package com.google.android.exoplayer.demo.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer.ExoPlayer;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xxu42 on 16-5-11.
 */
public abstract class  ExoMediaPlayerInterface {

    //public void ExoMediaPlayerInterface(RendererBuilder rendererBuilder) {
         //TODO
    //}

    public ExoMediaPlayerInterface( ) {
         //TODO
    }
    //MediaPlayer like API
    /*
    public void setSurface(Surface surface) {
    public void prepare() {
    */

    public abstract void prepareAsync() throws IllegalStateException;
    public abstract boolean isPlaying() ;
        //TODO
    /*
    private Vector<Pair<Integer, SubtitleTrack>> mIndexTrackPairs = new Vector<>();
    private BitSet mInbandTrackIndices = new BitSet();

    public MediaPlayer.TrackInfo[] getTrackInfo() throws IllegalStateException {
        MediaPlayer.TrackInfo trackInfo[] = getInbandTrackInfo();
        // add out-of-band tracks
        synchronized (mIndexTrackPairs) {
            MediaPlayer.TrackInfo allTrackInfo[] = new MediaPlayer.TrackInfo[mIndexTrackPairs.size()];
            for (int i = 0; i < allTrackInfo.length; i++) {
                Pair<Integer, SubtitleTrack> p = mIndexTrackPairs.get(i);
                if (p.first != null) {
                    // inband track
                    allTrackInfo[i] = trackInfo[p.first];
                } else {
                    SubtitleTrack track = p.second;
                    allTrackInfo[i] = new MediaPlayer.TrackInfo(track.getTrackType(), track.getFormat());
                }
            }
            return allTrackInfo;
        }
    }

    private MediaPlayer.TrackInfo[] getInbandTrackInfo() throws IllegalStateException {
        Parcel request = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            request.writeInterfaceToken(IMEDIA_PLAYER);
            request.writeInt(INVOKE_ID_GET_TRACK_INFO);
            invoke(request, reply);
            TrackInfo trackInfo[] = reply.createTypedArray(TrackInfo.CREATOR);
            return trackInfo;
        } finally {
            request.recycle();
            reply.recycle();
        }
    }
    */


    public abstract int getVideoWidth();

    /**
     * Returns the height of the video.
     *
     * @return the height of the video, or 0 if there is no video,
     * no display surface was set, or the height has not been determined
     * yet. The OnVideoSizeChangedListener can be registered via
     * {@link #setOnVideoSizeChangedListener(OnVideoSizeChangedListener)}
     * to provide a notification when the height is available.
     */
    public abstract int getVideoHeight();

    public abstract int getCurrentPosition2();

    /**
     * Gets the duration of the file.
     *
     * @return the duration in milliseconds, if no duration is available
     * (for example, if streaming live content), -1 is returned.
     */
    public abstract int getDuration2();

    public abstract void release();
    public abstract void setVolume(float leftVolume, float rightVolume);

    /**
     * Similar, excepts sets volume of all channels to same value.
     *
     * @hide
     */
    public abstract void setVolume(float volume);


    public abstract void start() throws IllegalStateException;
    public abstract void pause() throws IllegalStateException;
    public abstract void seekTo(int msec) throws IllegalStateException;



    public abstract void setDataSource(Context context, Uri uri, Map<String, String> headers)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    public abstract void setDataSource(Context context, Uri uri)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;
    public abstract void setDataSource(FileDescriptor fd, long offset, long length)
            throws IOException, IllegalArgumentException, IllegalStateException;

    /**
     * Interface definition of a callback to be invoked indicating buffering
     * status of a media resource being streamed over the network.
     */
    public interface OnBufferingUpdateListener {
        /**
         * Called to update status in buffering a media stream received through
         * progressive HTTP download. The received buffering percentage
         * indicates how much of the content has been buffered or played.
         * For example a buffering update of 80 percent when half the content
         * has already been played indicates that the next 30 percent of the
         * content to play has been buffered.
         *
         * @param mp      the MediaPlayer the update pertains to
         * @param percent the percentage (0-100) of the content
         *                that has been buffered or played thus far
         */
        void onBufferingUpdate(ExoMediaPlayer mp, int percent);
    }

    /**
     * Register a callback to be invoked when the status of a network
     * stream's buffer has changed.
     *
     * @param listener the callback that will be run.
     */
    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        mOnBufferingUpdateListener = listener;
    }

    private OnBufferingUpdateListener mOnBufferingUpdateListener;

    /**
     * Interface definition for a callback to be invoked when playback of
     * a media source has completed.
     */
    public interface OnCompletionListener {
        /**
         * Called when the end of a media source is reached during playback.
         *
         * @param mp the ExoMediaPlayer that reached the end of the file
         */
        void onCompletion(ExoMediaPlayer mp);
    }

    /**
     * Register a callback to be invoked when the end of a media source
     * has been reached during playback.
     *
     * @param listener the callback that will be run
     */
    public void setOnCompletionListener(OnCompletionListener listener) {
        mOnCompletionListener = listener;
    }


    private OnCompletionListener mOnCompletionListener;

    /**
     * Interface definition of a callback to be invoked when there
     * has been an error during an asynchronous operation (other errors
     * will throw exceptions at method call time).
     */
    public interface OnErrorListener {
        /**
         * Called to indicate an error.
         *

         * @return True if the method handled the error, false if it didn't.
         * Returning false, or not having an OnErrorListener at all, will
         * cause the OnCompletionListener to be called.
         */
        boolean onError(ExoMediaPlayer mp, int what, int extra);
    }

    /**
     * Register a callback to be invoked when an error has happened
     * during an asynchronous operation.
     *
     * @param listener the callback that will be run
     */
    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }

    private OnErrorListener mOnErrorListener;


    /**
     * Interface definition for a callback to be invoked when the media
     * source is ready for playback.
     */
    public interface OnPreparedListener {
        /**
         * Called when the media file is ready for playback.
         *
         * @param mp the ExoMediaPlayer that is ready for playback
         */
        void onPrepared(ExoMediaPlayer mp);
    }

    /**
     * Register a callback to be invoked when the media source is ready
     * for playback.
     *
     * @param listener the callback that will be run
     */
    public void setOnPreparedListener(OnPreparedListener listener) {
        mOnPreparedListener = listener;
    }

    private OnPreparedListener mOnPreparedListener;

    /**
     * Interface definition of a callback to be invoked indicating
     * the completion of a seek operation.
     */
    public interface OnSeekCompleteListener {
        /**
         * Called to indicate the completion of a seek operation.
         *
         * @param mp the ExoMediaPlayer that issued the seek operation
         */
        public void onSeekComplete(ExoMediaPlayer mp);
    }

    /**
     * Register a callback to be invoked when a seek operation has been
     * completed.
     *
     * @param listener the callback that will be run
     */
    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        mOnSeekCompleteListener = listener;
    }

    private OnSeekCompleteListener mOnSeekCompleteListener;

    /**
     * Interface definition of a callback to be invoked when the
     * video size is first known or updated
     */
    public interface OnVideoSizeChangedListener {
        /**
         * Called to indicate the video size
         * <p/>
         * The video size (width and height) could be 0 if there was no video,
         * no display surface was set, or the value was not determined yet.
         *
         * @param mp     the ExoMediaPlayer associated with this callback
         * @param width  the width of the video
         * @param height the height of the video
         */
        public void onVideoSizeChanged(ExoMediaPlayerInterface mp, int width, int height);
    }

    /**
     * Register a callback to be invoked when the video size is
     * known or updated.
     *
     * @param listener the callback that will be run
     */
    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener listener) {
        mOnVideoSizeChangedListener = listener;
    }

    private OnVideoSizeChangedListener mOnVideoSizeChangedListener;

    //@Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees,
                                   float pixelWidthHeightRatio) {
        //for (Listener listener : listeners) {
        //listener.onVideoSizeChanged(width, height, unappliedRotationDegrees, pixelWidthHeightRatio);
        //}
        if (mOnVideoSizeChangedListener == null) {
            return;
        }
        mOnVideoSizeChangedListener.onVideoSizeChanged(this, width, height);
    }


    /*
	@Override
	public void onDownstreamFormatChanged(int sourceId, Format format, int trigger,
		long mediaTimeMs) {
	  if (mOnVideoSizeChangedListener == null) {
		return;
	  }
	  if (sourceId == TYPE_VIDEO) {
		mOnVideoSizeChangedListener(this, format.width, format.height);
	  }

	}
	*/



    /**
     * Gets the media metadata.
     *
     * @param update_only  controls whether the full set of available
     *                     metadata is returned or just the set that changed since the
     *                     last call. See {@see #METADATA_UPDATE_ONLY} and {@see
     *                     #METADATA_ALL}.
     * @param apply_filter if true only metadata that matches the
     *                     filter is returned. See {@see #APPLY_METADATA_FILTER} and {@see
     *                     #BYPASS_METADATA_FILTER}.
     * @return The metadata, possibly empty. null if an error occured.
     * // FIXME: unhide.
     * {@hide}
     */
    /*
    public Metadata getMetadata(final boolean update_only,
                                final boolean apply_filter) {
        Parcel reply = Parcel.obtain();
        Metadata data = new Metadata();
        //TODO

        return data;
    }
    */
}

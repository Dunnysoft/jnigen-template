package com.dunnysoft.libxmpwrapper;

public class Xmp {
	// @off
	/* JNI
	#include <stdlib.h>
	#include <string.h>
	#include <errno.h>
	#include <jni.h>
	#include <xmp.h>

	static struct test_info {
		jclass class;
		jfieldID name;
		jfieldID type;
	} test_info;

	static struct frame_info {
		jclass class;
		jfieldID position;
		jfieldID pattern;
		jfieldID row;
		jfieldID numRows;
		jfieldID frame;
		jfieldID speed;
		jfieldID bpm;
		jfieldID time;
		jfieldID totalTime;
		jfieldID frameTime;
		jfieldID buffer;
		jfieldID bufferSize;
		jfieldID totalSize;
		jfieldID volume;
		jfieldID loopCount;
		jfieldID virtChannels;
		jfieldID virtUsed;
		jfieldID sequence;
		jfieldID channelInfo;
	} frame_info;

	static struct channel_info {
		jclass class;
		jfieldID period;
		jfieldID position;
		jfieldID pitchbend;
		jfieldID note;
		jfieldID instrument;
		jfieldID sample;
		jfieldID volume;
		jfieldID pan;
		jfieldID event;
	} channel_info;

	static struct event_data {
		jclass class;
		jfieldID note;
		jfieldID ins;
		jfieldID vol;
		jfieldID fxt;
		jfieldID fxp;
		jfieldID f2t;
		jfieldID f2p;
	} event_data;

	static struct mod_data {
		jclass class;
		jfieldID name;
		jfieldID type;
		jfieldID numPatterns;
		jfieldID numChannels;
		jfieldID numInstruments;
		jfieldID numSamples;
		jfieldID initialSpeed;
		jfieldID initialBpm;
		jfieldID length;
	} mod_data;

	static struct pattern_data {
		jclass class;
		jfieldID ctx;
		jfieldID num;
		jfieldID numRows;
	} pattern_data;

	static struct instrument_data {
		jclass class;
		jfieldID name;
		jfieldID numSamples;
		jfieldID sampleID;
	} instrument_data;

	static struct sample_data {
		jclass class;
		jfieldID name;
		jfieldID length;
		jfieldID loopStart;
		jfieldID loopEnd;
		jfieldID flags;
		jfieldID data;
	} sample_data;

	#define GET_CLASS(a,b)   a.class = (*env)->FindClass(env, b)
	#define GET_FIELD(a,b,c) a.b = (*env)->GetFieldID(env, a.class, #b, c)

	static void init_test_info_fields(JNIEnv *env)
	{
		if (test_info.class != NULL)
			return;

		GET_CLASS(test_info, "org/helllabs/libxmp/Module$TestInfo");

		GET_FIELD(test_info, name, "Ljava/lang/String;");
		GET_FIELD(test_info, type, "Ljava/lang/String;");
	}

	static void init_frame_info_fields(JNIEnv *env)
	{
		if (frame_info.class != NULL)
			return;

		GET_CLASS(frame_info, "org/helllabs/libxmp/FrameInfo");

		GET_FIELD(frame_info, position, "I");
		GET_FIELD(frame_info, pattern, "I");
		GET_FIELD(frame_info, row, "I");
		GET_FIELD(frame_info, numRows, "I");
		GET_FIELD(frame_info, frame, "I");
		GET_FIELD(frame_info, speed, "I");
		GET_FIELD(frame_info, bpm, "I");
		GET_FIELD(frame_info, time, "I");
		GET_FIELD(frame_info, totalTime, "I");
		GET_FIELD(frame_info, frameTime, "I");
		GET_FIELD(frame_info, buffer, "Ljava/nio/ByteBuffer;");
		GET_FIELD(frame_info, bufferSize, "I");
		GET_FIELD(frame_info, totalSize, "I");
		GET_FIELD(frame_info, volume, "I");
		GET_FIELD(frame_info, loopCount, "I");
		GET_FIELD(frame_info, virtChannels, "I");
		GET_FIELD(frame_info, virtUsed, "I");
		GET_FIELD(frame_info, sequence, "I");
		GET_FIELD(frame_info, channelInfo,
				"[Lorg/helllabs/libxmp/FrameInfo$ChannelInfo;");
	}

	static void init_channel_info_fields(JNIEnv *env)
	{
		if (channel_info.class != NULL)
			return;

		GET_CLASS(channel_info, "org/helllabs/libxmp/FrameInfo$ChannelInfo");

		GET_FIELD(channel_info, period, "I");
		GET_FIELD(channel_info, position, "I");
		GET_FIELD(channel_info, pitchbend, "S");
		GET_FIELD(channel_info, note, "B");
		GET_FIELD(channel_info, instrument, "B");
		GET_FIELD(channel_info, sample, "B");
		GET_FIELD(channel_info, volume, "B");
		GET_FIELD(channel_info, pan, "B");
		GET_FIELD(channel_info, event, "Lorg/helllabs/libxmp/Module$Event;");
	}

	static void init_event_data_fields(JNIEnv *env)
	{
		if (event_data.class != NULL)
			return;

		GET_CLASS(event_data, "org/helllabs/libxmp/Module$Event");

		GET_FIELD(event_data, note, "I");
		GET_FIELD(event_data, ins, "I");
		GET_FIELD(event_data, vol, "I");
		GET_FIELD(event_data, fxt, "I");
		GET_FIELD(event_data, fxp, "I");
		GET_FIELD(event_data, f2t, "I");
		GET_FIELD(event_data, f2p, "I");
	}

	static void init_mod_data_fields(JNIEnv *env)
	{
		if (mod_data.class != NULL)
			return;

		GET_CLASS(mod_data, "org/helllabs/libxmp/Module");

		GET_FIELD(mod_data, name, "Ljava/lang/String;");
		GET_FIELD(mod_data, type, "Ljava/lang/String;");
		GET_FIELD(mod_data, numPatterns, "I");
		GET_FIELD(mod_data, numChannels, "I");
		GET_FIELD(mod_data, numInstruments, "I");
		GET_FIELD(mod_data, numSamples, "I");
		GET_FIELD(mod_data, initialSpeed, "I");
		GET_FIELD(mod_data, initialBpm, "I");
		GET_FIELD(mod_data, length, "I");
	}

	static void init_pattern_data_fields(JNIEnv *env)
	{
		if (pattern_data.class != NULL)
			return;

		GET_CLASS(pattern_data, "org/helllabs/libxmp/Module$Pattern");

		GET_FIELD(pattern_data, ctx, "J");
		GET_FIELD(pattern_data, num, "I");
		GET_FIELD(pattern_data, numRows, "I");
	}

	static void init_instrument_data_fields(JNIEnv *env)
	{
		if (instrument_data.class != NULL)
			return;

		GET_CLASS(instrument_data, "org/helllabs/libxmp/Module$Instrument");

		GET_FIELD(instrument_data, name, "Ljava/lang/String;");
		GET_FIELD(instrument_data, numSamples, "I");
		GET_FIELD(instrument_data, sampleID, "[I");
	}

	static void init_sample_data_fields(JNIEnv *env)
	{
		if (sample_data.class != NULL)
			return;

		GET_CLASS(sample_data, "org/helllabs/libxmp/Module$Sample");

		GET_FIELD(sample_data, name, "Ljava/lang/String;");
		GET_FIELD(sample_data, length, "I");
		GET_FIELD(sample_data, loopStart, "I");
		GET_FIELD(sample_data, loopEnd, "I");
		GET_FIELD(sample_data, flags, "I");
		GET_FIELD(sample_data, data, "Ljava/nio/ByteBuffer;");
	}


	 */

	public static final int KEY_OFF = 0x81;                // Note number for key off event
	public static final int KEY_CUT = 0x82;                // Note number for key cut event
	public static final int KEY_FADE = 0x83;            // Note number for fade event

	// mixer parameter macros

	// sample format flags
	public static final int FORMAT_8BIT = 1 << 0;        // Mix to 8-bit instead of 16
	public static final int FORMAT_UNSIGNED = 1 << 1;    // Mix to unsigned samples
	public static final int FORMAT_MONO = 1 << 2;        // Mix to mono instead of stereo

	// mixer paramters for xmp_set_player()
	static final int PLAYER_AMP = 0;                    // Amplification factor
	static final int PLAYER_MIX = 1;                    // Stereo mixing
	static final int PLAYER_INTERP = 2;                    // Interpolation type
	static final int PLAYER_DSP = 3;                    // DSP effect flags
	static final int PLAYER_FLAGS = 4;                    // Player flags
	static final int PLAYER_CFLAGS = 5;                    // Player flags for current module
	static final int PLAYER_SMPCTL = 6;                    // Sample control flags
	static final int PLAYER_VOLUME = 7;                    // Player module volume
	static final int PLAYER_STATE = 8;                    // Internal player state
	static final int PLAYER_SMIX_VOLUME = 9;            // SMIX volume

	// interpolation types
	public static final int INTERP_NEAREST = 0;            // Nearest neighbor
	public static final int INTERP_LINEAR = 1;            // Linear (default)
	public static final int INTERP_SPLINE = 2;            // Cubic spline

	// dsp effect types
	public static final int DSP_LOWPASS = 1 << 0;        // Lowpass filter effect
	public static final int DSP_ALL = DSP_LOWPASS;

	// player state
	public static final int STATE_UNLOADED = 0;            // Context created
	public static final int STATE_LOADED = 1;            // Module loaded
	public static final int STATE_PLAYING = 2;            // Module playing

	// player flags
	public static final int FLAGS_VBLANK = 1 << 0;        // Use vblank timing
	public static final int FLAGS_FX9BUG = 1 << 1;        // Emulate FX9 bug
	public static final int FLAGS_FIXLOOP = 1 << 2;        // Emulate sample loop bug

	// sample flags
	public static final int SMPCTL_SKIP = 1 << 0;        // Don't load samples

	// limits
	public static final int MAX_KEYS = 121;                // Number of valid keys
	public static final int MAX_ENV_POINTS = 32;        // Max number of envelope points
	public static final int MAX_MOD_LENGTH = 256;        // Max number of patterns in module
	public static final int MAX_CHANNELS = 64;            // Max number of channels in module
	public static final int MAX_SRATE = 49170;            // max sampling rate (Hz)
	public static final int MIN_SRATE = 4000;            // min sampling rate (Hz)
	public static final int MIN_BPM = 20;                // min BPM
	// frame rate = (50 * bpm / 125) Hz
	// frame size = (sampling rate * channels * size) / frame rate
	public static final int MAX_FRAMESIZE = 5 * MAX_SRATE * 2 / MIN_BPM;

	// error codes
	public static final int ERROR_INTERNAL = 2;
	public static final int ERROR_FORMAT = 3;
	public static final int ERROR_LOAD = 4;
	public static final int ERROR_SYSTEM = 6;
	public static final int ERROR_INVALID = 7;
	public static final int ERROR_STATE = 8;

	public static final int PERIOD_BASE = 6847;            // C4 period

	public static final int SAMPLE_16BIT = 1 << 0;        // 16bit sample
	public static final int SAMPLE_LOOP = 1 << 1;        // Sample is looped
	public static final int SAMPLE_LOOP_BIDIR = 1 << 2;    // Bidirectional sample loop
	public static final int SAMPLE_LOOP_FULL = 1 << 4;    // Play full sample before looping


	static final String[] ERROR_STRING = {
			"No error",
			"End of module",
			"Internal error",
			"Unknown module format",
			"Can't load module",
			"Can't decompress module",
			"System error",
			"Invalid parameter"
	};

	private Xmp() {
		System.loadLibrary("libxmp");

	}


	// native API methods
	native static long createContext(); /*
		// Cache class field IDs
		init_frame_info_fields(env);
		init_channel_info_fields(env);
		init_event_data_fields(env);
		init_mod_data_fields(env);
		init_pattern_data_fields(env);
		init_instrument_data_fields(env);
		init_sample_data_fields(env);

        return (jlong)xmp_create_context();
     */

	native static void freeContext(long ctx); /*
        xmp_free_context((xmp_context)ctx);
	*/

	native static int loadModule(long ctx, String path); /*
		const char *filename;
		int res;

		if (path == NULL) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/NullPointerException");
			(*env)->ThrowNew(env, ex, "File name is null");
			return -XMP_ERROR_LOAD;
		}

		if (path == NULL) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/NullPointerException");
			(*env)->ThrowNew(env, ex, "File name is null");
			return -XMP_ERROR_LOAD;
		}

		filename = (*env)->GetStringUTFChars(env, path, NULL);
		res = xmp_load_module((xmp_context)ctx, (char *)filename);
		(*env)->ReleaseStringUTFChars(env, path, filename);

		return res;
	*/

	native static int testModule(String path, Module.TestInfo info);	/*
	const char *filename;
	int res;
	struct xmp_test_info ti;

	// Cache class field IDs
	init_test_info_fields(env);

	filename = (*env)->GetStringUTFChars(env, path, NULL);
	res = xmp_test_module((char *)filename, &ti);
	(*env)->ReleaseStringUTFChars(env, path, filename);

	if (res == 0) {
		(*env)->SetObjectField(env, info, test_info.name,
				(*env)->NewStringUTF(env, ti.name));

		(*env)->SetObjectField(env, info, test_info.type,
				(*env)->NewStringUTF(env, ti.type));
	}

	return res;
	 */


	native static void releaseModule(long ctx); /*
		xmp_release_module((xmp_context)ctx);
		*/


	native static int startPlayer(long ctx, int freq, int mode); /*
		return xmp_start_player((xmp_context)ctx, rate, flags);
		*/

	native static int playFrame(long ctx); /*
		return xmp_play_frame((xmp_context)ctx);
	*/

	native static void getFrameInfo(long ctx, FrameInfo info); /*
	struct xmp_frame_info fi;
	jobjectArray array;
	jobject event;
	int i, len;

	xmp_get_frame_info((xmp_context)ctx, &fi);

	(*env)->SetIntField(env, info, frame_info.position, fi.pos);
	(*env)->SetIntField(env, info, frame_info.pattern, fi.pattern);
	(*env)->SetIntField(env, info, frame_info.row, fi.row);
	(*env)->SetIntField(env, info, frame_info.numRows, fi.num_rows);
	(*env)->SetIntField(env, info, frame_info.frame, fi.frame);
	(*env)->SetIntField(env, info, frame_info.speed, fi.speed);
	(*env)->SetIntField(env, info, frame_info.bpm, fi.bpm);
	(*env)->SetIntField(env, info, frame_info.time, fi.time);
	(*env)->SetIntField(env, info, frame_info.totalTime, fi.total_time);
	(*env)->SetIntField(env, info, frame_info.frameTime, fi.frame_time);

	if ((*env)->GetObjectField(env, info, frame_info.buffer) == NULL) {
		jobject buf = (*env)->NewDirectByteBuffer(env, fi.buffer,
						XMP_MAX_FRAMESIZE);
		(*env)->SetObjectField(env, info, frame_info.buffer, buf);
	}

	(*env)->SetIntField(env, info, frame_info.bufferSize, fi.buffer_size);
	(*env)->SetIntField(env, info, frame_info.totalSize, fi.total_size);
	(*env)->SetIntField(env, info, frame_info.volume, fi.volume);
	(*env)->SetIntField(env, info, frame_info.loopCount, fi.loop_count);
	(*env)->SetIntField(env, info, frame_info.virtChannels, fi.virt_channels);
	(*env)->SetIntField(env, info, frame_info.virtUsed, fi.virt_used);
	(*env)->SetIntField(env, info, frame_info.sequence, fi.sequence);

	// Channel info

	array = (*env)->GetObjectField(env, info, frame_info.channelInfo);
	len = (*env)->GetArrayLength(env, array);

	for (i = 0; i < len; i++) {
		struct xmp_channel_info *ci = &fi.channel_info[i];
		jobject obj = (*env)->GetObjectArrayElement(env, array, i);

		(*env)->SetIntField(env, obj, channel_info.period, ci->period);
		(*env)->SetIntField(env, obj, channel_info.position, ci->position);
		(*env)->SetShortField(env, obj, channel_info.pitchbend, ci->pitchbend);
		(*env)->SetByteField(env, obj, channel_info.note, ci->note);
		(*env)->SetByteField(env, obj, channel_info.instrument, ci->instrument);
		(*env)->SetByteField(env, obj, channel_info.sample, ci->sample);
		(*env)->SetByteField(env, obj, channel_info.volume, ci->volume);
		(*env)->SetByteField(env, obj, channel_info.pan, ci->pan);

		// Event

		event = (*env)->GetObjectField(env, obj, channel_info.event);

		(*env)->SetIntField(env, event, event_data.note, ci->event.note);
		(*env)->SetIntField(env, event, event_data.ins, ci->event.ins);
		(*env)->SetIntField(env, event, event_data.vol, ci->event.vol);
		(*env)->SetIntField(env, event, event_data.fxt, ci->event.fxt);
		(*env)->SetIntField(env, event, event_data.fxp, ci->event.fxp);
		(*env)->SetIntField(env, event, event_data.f2t, ci->event.f2t);
		(*env)->SetIntField(env, event, event_data.f2p, ci->event.f2p);

		(*env)->DeleteLocalRef(env, obj);
	}

	return info;

	*/

	native static void endPlayer(long ctx); /*
		xmp_end_player((xmp_context)ctx);
	*/

	native static int setPlayer(long ctx, int param, int value); /*
		return xmp_set_player((xmp_context)ctx, param, value);
	*/

	native static int getPlayer(long ctx, int param); /*
		return xmp_get_player((xmp_context)ctx, param);
	*/

	native static int injectEvent(long ctx, int chn, Module.Event event); /*

	*/

	native static String[] getFormatList(); /*
		jobjectArray ret;
		char **list;
		int i;

		list = xmp_get_format_list();
		for (i = 0; list[i]; i++);

		ret = (jobjectArray)(*env)->NewObjectArray(env, i,
					(*env)->FindClass(env, "java/lang/String"),
					(*env)->NewStringUTF(env, ""));

		while (i--) {
			(*env)->SetObjectArrayElement(env, ret, i,
					(*env)->NewStringUTF(env, list[i]));
		}

		return ret;
	*/

	native static int nextPosition(long ctx); /*
		return xmp_next_position((xmp_context)ctx);
	*/

	native static int prevPosition(long ctx); /*
		return xmp_prev_position((xmp_context)ctx);
	*/

	native static int setPosition(long ctx, int num); /*
		return xmp_set_position((xmp_context)ctx, num);
	*/

	native static void scanModule(long ctx); /*
		xmp_scan_module((xmp_context)ctx);
	*/

	native static void stopModule(long ctx); /*
		return xmp_stop_module((xmp_context)ctx);
	*/

	native static void restartModule(long ctx); /*
		return xmp_restart_module((xmp_context)ctx);
	*/

	native static int seekTime(long ctx, int time); /*
		return xmp_seek_time((xmp_context)ctx, time);
	*/

	native static int channelMute(long ctx, int chn, int val); /*
		return xmp_channel_mute((xmp_context)ctx, chn, val);
	*/

	native static int channelVol(long ctx, int chn, int val); /*
		return xmp_channel_vol((xmp_context)ctx, chn, val);
	*/

	// sample mixer API
	native static int startSmix(long ctx, int chn, int smp); /*
		return xmp_start_smix((xmp_context)ctx, chn, smp);
	*/

	native static void endSmix(long ctx); /*
		xmp_end_smix((xmp_context)ctx);
	*/

	native static int smixPlayInstrument(long ctx, int ins, int note, int vol, int chn); /*
		return xmp_smix_play_instrument((xmp_context)ctx, ins, note, vol, chn);
	*/

	native static int smixPlaySample(long ctx, int ins, int note, int vol, int chn); /*
		return xmp_smix_play_sample((xmp_context)ctx, ins, note, vol, chn);
	*/

	native static int smixChannelPan(long ctx, int chn, int pan); /*
		return xmp_smix_channel_pan((xmp_context)ctx, chn, pan);
	*/

	native static int smixLoadSample(long ctx, int num, String path); /*
		const char *filename;
		int res;

		filename = (*env)->GetStringUTFChars(env, path, NULL);
		res = xmp_smix_load_sample((xmp_context)ctx, num, (char *)filename);
		(*env)->ReleaseStringUTFChars(env, path, filename);

		return res;
	*/

	native static int smixReleaseSample(long ctx, int num); /*
		return xmp_smix_release_sample((xmp_context)ctx, num);
	*/

	// native helpers
	native static int getErrno(); /*
		return errno;
	*/

	native static String getStrError(int err); /*
		char c[128];
		strerror_r(err, c, 128);
		return (*env)->NewStringUTF(env, c);
	*/

	native static void getModData(long ctx, Module mod); /*
		struct xmp_module_info mi;

		xmp_get_module_info((xmp_context)ctx, &mi);

		(*env)->SetObjectField(env, mod, mod_data.name,
					(*env)->NewStringUTF(env, mi.mod->name));
		(*env)->SetObjectField(env, mod, mod_data.type,
					(*env)->NewStringUTF(env, mi.mod->type));
		(*env)->SetIntField(env, mod, mod_data.numPatterns, mi.mod->pat);
		(*env)->SetIntField(env, mod, mod_data.numChannels, mi.mod->chn);
		(*env)->SetIntField(env, mod, mod_data.numInstruments, mi.mod->ins);
		(*env)->SetIntField(env, mod, mod_data.numSamples, mi.mod->smp);
		(*env)->SetIntField(env, mod, mod_data.initialSpeed, mi.mod->spd);
		(*env)->SetIntField(env, mod, mod_data.initialBpm, mi.mod->bpm);
		(*env)->SetIntField(env, mod, mod_data.length, mi.mod->len);
	*/

	native static void getEventData(long ctx, int pat, int row, int chn, Module.Event pattern); /*
		struct xmp_module_info mi;
		struct xmp_pattern *xxp;
		struct xmp_event *e;

		xmp_get_module_info((xmp_context)ctx, &mi);

		if (pat >= mi.mod->pat) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid pattern number");
			return;
		}

		xxp = mi.mod->xxp[pat];

		if (row >= xxp->rows) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid Row number");
			return;
		}

		if (chn >= mi.mod->chn) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid channel number");
			return;
		}

		e = &mi.mod->xxt[mi.mod->xxp[pat]->index[chn]]->event[row];

		(*env)->SetIntField(env, event, event_data.note, e->note);
		(*env)->SetIntField(env, event, event_data.ins, e->ins);
		(*env)->SetIntField(env, event, event_data.vol, e->vol);
		(*env)->SetIntField(env, event, event_data.fxt, e->fxt);
		(*env)->SetIntField(env, event, event_data.fxp, e->fxp);
		(*env)->SetIntField(env, event, event_data.f2t, e->f2t);
		(*env)->SetIntField(env, event, event_data.f2p, e->f2p);
	*/

	native static void getPatternData(long ctx, int num, Module.Pattern pattern); /*
		struct xmp_module_info mi;
		struct xmp_pattern *xxp;

		xmp_get_module_info((xmp_context)ctx, &mi);

		if (num >= mi.mod->pat) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid pattern number");
			return;
		}

		xxp = mi.mod->xxp[num];

		(*env)->SetLongField(env, pattern, pattern_data.ctx, ctx);
		(*env)->SetIntField(env, pattern, pattern_data.num, num);
		(*env)->SetIntField(env, pattern, pattern_data.numRows, xxp->rows);
	*/

	native static void getInstrumentData(long ctx, int num, Module.Instrument instrument); /*
		struct xmp_module_info mi;
		struct xmp_instrument *xxi;
		jint *region;
		jobject sid;
		int i;

		xmp_get_module_info((xmp_context)ctx, &mi);

		if (num >= mi.mod->ins) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid instrument number");
			return;
		}

		xxi = &mi.mod->xxi[num];

		(*env)->SetObjectField(env, instrument, instrument_data.name,
					(*env)->NewStringUTF(env, xxi->name));
		(*env)->SetIntField(env, instrument, instrument_data.numSamples,
					xxi->nsm);

		sid = (jintArray)(*env)->NewIntArray(env, xxi->nsm);

		if ((region = malloc(xxi->nsm * sizeof(jint))) == NULL)
			return;

		for (i = 0; i < xxi->nsm; i++) {
			region[i] = xxi->sub[i].sid;
		}

		(*env)->SetIntArrayRegion(env, sid, 0, xxi->nsm, region);
		(*env)->SetObjectField(env, instrument, instrument_data.sampleID, sid);

		free(region);
	*/

	native static void getSampleData(long ctx, int num, Module.Sample sample); /*
		struct xmp_module_info mi;
		struct xmp_sample *xxs;
		jobject data;
		int size;

		xmp_get_module_info((xmp_context)ctx, &mi);

		if (num >= mi.mod->smp) {
			jclass ex = (*env)->FindClass(env,
					"java/lang/IndexOutOfBoundsException");
			(*env)->ThrowNew(env, ex, "Invalid sample number");
			return;
		}

		xxs = &mi.mod->xxs[num];

		(*env)->SetObjectField(env, sample, sample_data.name,
					(*env)->NewStringUTF(env, xxs->name));
		(*env)->SetIntField(env, sample, sample_data.length, xxs->len);
		(*env)->SetIntField(env, sample, sample_data.loopStart, xxs->lps);
		(*env)->SetIntField(env, sample, sample_data.loopEnd, xxs->lpe);
		(*env)->SetIntField(env, sample, sample_data.flags, xxs->flg);

		size = xxs->len;
		if (xxs->flg & XMP_SAMPLE_16BIT)
			size <<= 1;

		data = (*env)->NewDirectByteBuffer(env, xxs->data, size);
		(*env)->SetObjectField(env, sample, sample_data.data, data);
	*/


	static {
		System.loadLibrary("xmp-jni");
	}
}
package main.java.Model;

import javax.sound.midi.*;
import main.java.Observer.BPMObserver;
import main.java.Observer.BeatObserver;
import java.util.*;

public class BeatModel implements BeatModelInterface, MetaEventListener {
  Sequencer sequencer;
  ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
  ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
  int bpm = 90;
  Sequence sequence;
  Track track;

  public void initialize() {
    setUpMidi();
    buildTrackAndStart();
  }

  public void on() {
    sequencer.start();
    setBPm(getBPm());
  }

  public void off() {
    setBPm(0);
    sequencer.stop();
  }

  /**
   * Descripcion.
   */
  public void setBPm(int bpm) {
    this.bpm = bpm;

    sequencer.setMicrosecondPosition(0);
    sequencer.setTickPosition(0);

    sequencer.setTempoInBPM(getBPm());
    notifyBPmobservers();
  }

  public int getBPm() {
    return bpm;
  }

  void beatEvent() {
    notifyBeatObservers();
  }

  public void registerObserver(BeatObserver o) {
    beatObservers.add(o);
  }

  /**
   * Descripcion.
   */
  public void notifyBeatObservers() {
    for (int i = 0; i < beatObservers.size(); i++) {
      BeatObserver observer = (BeatObserver) beatObservers.get(i);
      observer.updateBeat();
    }
  }

  public void registerObserver(BPMObserver o) {
    bpmObservers.add(o);
  }

  /**
   * Descripcion.
   */
  public void notifyBPmobservers() {
    for (int i = 0; i < bpmObservers.size(); i++) {
      BPMObserver observer = (BPMObserver) bpmObservers.get(i);
      observer.updateBPm();
    }
  }

  /**
   * Descripcion.
   */
  public void removeObserver(BeatObserver o) {
    int i = beatObservers.indexOf(o);
    if (i >= 0) {
      beatObservers.remove(i);
    }
  }

  /**
   * Descripcion.
   */
  public void removeObserver(BPMObserver o) {
    int i = bpmObservers.indexOf(o);
    if (i >= 0) {
      bpmObservers.remove(i);
    }
  }

  /**
   * Descripcion.
   */
  public void meta(MetaMessage message) {
    if (message.getType() == 0x2F) {
      beatEvent();
      sequencer.setMicrosecondPosition(0);
      sequencer.setTickPosition(0);
      sequencer.start();
      setBPm(getBPm());
    }
  }

  /**
   * Descripcion.
   */
  public void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequencer.addMetaEventListener(this);

      // Fix
      sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(getBPm());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Descripcion.
   */
  public void buildTrackAndStart() {
    int[] trackList = { 35, 0, 46, 0 };

    sequence.deleteTrack(null);
    track = sequence.createTrack();

    makeTracks(trackList);
    track.add(makeEvent(192, 9, 1, 0, 4));
    try {
      sequencer.setSequence(sequence);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Descripcion.
   * 
   * @param list
   *          Descripcion.
   */
  public void makeTracks(int[] list) {

    for (int i = 0; i < list.length; i++) {
      int key = list[i];

      if (key != 0) {
        track.add(makeEvent(144, 9, key, 100, i));
        track.add(makeEvent(128, 9, key, 100, i + 1));
      }
    }
  }

  /**
   * Descripcion.
   * 
   * @param comd
   *          Descripcion.
   * @param chan
   *          Descripcion.
   * @param one
   *          Descripcion.
   * @param two
   *          Descripcion.
   * @param tick
   *          Descripcion.
   * @return Descripcion.
   */
  public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage a = new ShortMessage();
      a.setMessage(comd, chan, one, two);
      event = new MidiEvent(a, tick);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }
}

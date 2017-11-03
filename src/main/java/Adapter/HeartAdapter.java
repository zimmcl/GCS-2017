package main.java.Adapter;

import main.java.Model.BeatModelInterface;
import main.java.Model.HeartModelInterface;
import main.java.Observer.BPMObserver;
import main.java.Observer.BeatObserver;

public class HeartAdapter implements BeatModelInterface {
  HeartModelInterface heart;

  public HeartAdapter(HeartModelInterface heart) {
    this.heart = heart;
  }

  public void initialize() {
  }

  public void on() {
  }

  public void off() {
  }

  public int getBPm() {
    return heart.getHeartRate();
  }

  public void setBPm(int bpm) {
  }

  public void registerObserver(BeatObserver o) {
    heart.registerObserver(o);
  }

  public void removeObserver(BeatObserver o) {
    heart.removeObserver(o);
  }

  public void registerObserver(BPMObserver o) {
    heart.registerObserver(o);
  }

  public void removeObserver(BPMObserver o) {
    heart.removeObserver(o);
  }
}

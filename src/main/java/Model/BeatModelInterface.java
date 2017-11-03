package main.java.Model;

import main.java.Observer.BPMObserver;
import main.java.Observer.BeatObserver;

public interface BeatModelInterface {
  void initialize();

  void on();

  void off();

  void setBPm(int bpm);

  int getBPm();

  void registerObserver(BeatObserver o);

  void removeObserver(BeatObserver o);

  void registerObserver(BPMObserver o);

  void removeObserver(BPMObserver o);
}

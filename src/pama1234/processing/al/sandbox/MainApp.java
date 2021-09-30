package pama1234.processing.al.sandbox;

import pama1234.processing.al.autometa.particle.util.ParticleAutomata;
import pama1234.processing.al.corewars.util.MemoryArrayNeuronSimulator;
import pama1234.processing.util.app.UtilApp;

public class MainApp extends UtilApp{
  ParticleAutomata pa;
  MemoryArrayNeuronSimulator mans;
  @Override
  public void settings() {
    super.settings();
    noSmooth();
    background=color(0);
  }
  @Override
  public void init() {
    strokeWeight(1/2f);
    noFill();
    textSize(4);
    textAlign(LEFT,BOTTOM);
    pa=new ParticleAutomata(this);
    mans=new MemoryArrayNeuronSimulator(this,512,16,330/8,-330/8,2,9,3);
    center.add.add(pa);
    center.add.add(mans);
  }
  @Override
  public void display() {}
  @Override
  public void update() {}
  @Override
  public void exitActual() {
    super.exitActual();
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new MainApp().runSketch();
  }
}

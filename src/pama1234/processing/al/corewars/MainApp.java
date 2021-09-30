package pama1234.processing.al.corewars;

import pama1234.processing.al.corewars.util.MemoryArrayNeuronSimulator;
import pama1234.processing.util.app.UtilApp;

public class MainApp extends UtilApp{
  MemoryArrayNeuronSimulator mans;
  @Override
  public void settings() {
    super.settings();
    noSmooth();
    background=color(0);
  }
  @Override
  public void init() {
    mans=new MemoryArrayNeuronSimulator(this,256,16,0,0,1,4,4);
    center.add.add(mans);
    strokeWeight(1/2f);
    noFill();
    textSize(4);
    textAlign(LEFT,BOTTOM);
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

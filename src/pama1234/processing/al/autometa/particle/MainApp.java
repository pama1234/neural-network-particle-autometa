package pama1234.processing.al.autometa.particle;

import pama1234.processing.al.autometa.particle.util.ParticleAutomata;
import pama1234.processing.util.app.UtilApp;

public class MainApp extends UtilApp{
  public static final int cam_box_r=720;
  @Override
  public void settings() {
    super.settings();
    noSmooth();
  }
  @Override
  public void init() {
    background=color(0);
    center.add.add(new ParticleAutomata(this));
  }
  @Override
  public void update() {
    cam.point.des.setInBox(-cam_box_r,-cam_box_r,cam_box_r,cam_box_r);
  }
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale","1");
    new MainApp().runSketch();
  }
}

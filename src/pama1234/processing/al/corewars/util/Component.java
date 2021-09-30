package pama1234.processing.al.corewars.util;

import pama1234.math.physics.PathPoint;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Component{
  public PApplet p;
  public PGraphics g;
  public PathPoint point;
  public Component(PApplet p,int x,int y,int w,int h) {
    this.p=p;
    g=p.createGraphics(w,h);
    point=new PathPoint(0,0,x,y);
  }
  public void display() {
    p.image(g,point.pos.x,point.pos.y);
  }
  public void resize(int w,int h) {
    g=p.createGraphics(w,h);
    initGraphics();
  }
  public void initGraphics() {
    g.noSmooth();
    g.beginDraw();
    g.endDraw();
  }
}

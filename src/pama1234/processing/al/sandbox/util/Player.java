package pama1234.processing.al.sandbox.util;

import pama1234.processing.al.autometa.particle.util.Cell;
import pama1234.processing.al.autometa.particle.util.CellCenter;
import pama1234.processing.al.corewars.util.Component;
import pama1234.processing.util.Entity;
import pama1234.processing.util.app.UtilApp;

public class Player extends Entity{
  public Component c;
  //---
  public CellCenter cellCenter;
  public Cell cell;
  //---
  public static final int up=0,down=1,left=2,right=3;
  public final boolean[] keys=new boolean[4];
  public int type;
  //---
  public int[] image;
  public Player(UtilApp p,int x,int y) {
    super(p);
    c=new Component(p,0,0,32,32);
    final int l=(int)Math.ceil(Cell.dist*12);
    image=new int[l*l];
  }
  @Override
  public void mousePressed(int button) {}
  @Override
  public void mouseReleased(int button) {}
  @Override
  public void mouseClicked(int button) {}
  @Override
  public void mouseMoved() {}
  @Override
  public void mouseDragged() {}
  @Override
  public void mouseWheel(int c) {}
  @Override
  public void keyPressed(char key,int keyCode) {}
  @Override
  public void keyReleased(char key,int keyCode) {}
  @Override
  public void keyTyped(char key) {}
  @Override
  public void frameResized(int w,int h) {}
  @Override
  public void frameMoved(int x,int y) {}
  @Override
  public void init() {}
  @Override
  public void update() {}
  @Override
  public void display() {}
  @Override
  public void pause() {}
  @Override
  public void resume() {}
  @Override
  public void dispose() {}
}

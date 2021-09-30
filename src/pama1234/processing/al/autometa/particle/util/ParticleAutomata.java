package pama1234.processing.al.autometa.particle.util;

import pama1234.processing.util.Entity;
import pama1234.processing.util.app.UtilApp;
import pama1234.processing.util.center.EntityCenter;
import processing.core.PConstants;

public class ParticleAutomata extends EntityCenter<Entity>{
  public MetaInfoCenter metaList;
  public CellCenter cellList;
  public ParticleAutomata(UtilApp p) {
    super(p);
    MetaInfo[][] core=initCore();
    //---
    initColors(p,core);
    //--
    initCellCenter(p,core,1<<6);
  }
  public MetaInfo[][] initCore() {
    final float[][] rules=new float[][] {
      {0,1,-1,-1,0,0,0,0,0,0,0,1},
      {1,0,1,-1,-1,0,0,0,0,0,0,0},
      {0,1,0,1,-1,-1,0,0,0,0,0,0},
      {0,0,1,0,1,-1,-1,0,0,0,0,0},
      {0,0,0,1,0,1,-1,-1,0,0,0,0},
      {0,0,0,0,1,0,1,-1,-1,0,0,0},
      {0,0,0,0,0,1,0,1,-1,-1,0,0},
      {0,0,0,0,0,0,1,0,1,-1,-1,0},
      {0,0,0,0,0,0,0,1,0,1,-1,-1},
      {-1,0,0,0,0,0,0,0,1,0,1,-1},
      {-1,-1,0,0,0,0,0,0,0,1,0,1},
      {1,-1,-1,0,0,0,0,0,0,0,1,0}};
    for(float[] fs:rules) for(int i=0;i<fs.length;i++) fs[i]*=Cell.size/6;
    MetaInfo[][] core=new MetaInfo[rules.length][];
    for(int i=0;i<core.length;i++) {
      core[i]=new MetaInfo[rules[i].length];
      for(int j=0;j<core[i].length;j++) core[i][j]=new MetaInfo(rules[i][j]);
    }
    return core;
  }
  public void initColors(UtilApp p,MetaInfo[][] core) {
    int[] color=new int[core.length];
    p.colorMode(PConstants.HSB);
    for(int i=0;i<core.length;i++) color[i]=p.color(255f/core.length*i,255,255);
    p.colorMode(PConstants.RGB);
    metaList=new MetaInfoCenter(core,color);
  }
  public void initCellCenter(UtilApp p,MetaInfo[][] core,final int size) {
    cellList=new CellCenter(p,metaList);
    for(int i=0;i<core.length;i++) for(int j=0;j<size;j++) cellList.add.add(new Cell(p,cellList,i,p.random(-320,320),p.random(-320,320)));
    add.add(cellList);
  }
}

package pama1234.processing.al.corewars.util;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;

import pama1234.math.Tools;
import pama1234.processing.util.Entity;
import pama1234.processing.util.app.UtilApp;

public abstract class MemoryArray extends Entity{
  public static final int FLOAT_SIZE=4,INT_SIZE=4;
  public static final int CELL_SIZE=FLOAT_SIZE+INT_SIZE;
  public static final int MAX_PARENT_LINKS=4;
  public static final int NEURON_SIZE=MAX_PARENT_LINKS*CELL_SIZE+CELL_SIZE;
  public static int scale=8,range=4;
  public static float pixelSize=scale/(float)range;
  public final DecimalFormat floatFormatter=new DecimalFormat(
    "####,"+
      "0000."+
      "0000"),
    intFormatter=new DecimalFormat(
      "####,"+
        "0000");
  public ByteBuffer data;
  public Component c;
  public int minSize;
  public int mousePos,mouseX,mouseY,bytePos;
  public int w,h;
  public boolean stop,focus;
  private StringBuilder sb=new StringBuilder();
  public MemoryArray(UtilApp p,int size,int x,int y) {
    this(p,size,x,y,1,size+2);
  }
  public MemoryArray(UtilApp p,int size,int x,int y,int w,int h) {
    super(p);
    data=ByteBuffer.allocate(size);
    c=new Component(p,x*scale,y*scale,w*range,h+2);
    this.w=w;
    this.h=h;
    c.g.beginDraw();
    c.g.fill(0xff006799);
    c.g.stroke(0xff0099B6);
    c.g.endDraw();
    updateSize();
  }
  @Override
  public void update() {
    c.point.update();
    if(!stop) {
      preUpdateArray();
      updateArray();
      postUpdateArray();
    }
    if((focus=Tools.inBox(p.cam.mouseX,p.cam.mouseY,c.point.pos.x,c.point.pos.y,w*scale,h*scale/range))) {
      mousePos=(mouseY=(int)(Math.floor((p.cam.mouseY-c.point.pos.y)*range)/scale))*w+(mouseX=(int)(Math.floor(p.cam.mouseX-c.point.pos.x)/scale));
      bytePos=(int)(Math.floor((p.cam.mouseX-c.point.pos.x)*range)/scale)%4;
      if(mousePos>=data.capacity()) mousePos=-1;
    }
    mousePos/=4;
    c.g.beginDraw();
    c.g.clear();
    c.g.rect(0,0,c.g.width-1,c.g.height-1);
    c.g.loadPixels();
    drawBuffer();
    c.g.updatePixels();
    c.g.endDraw();
  }
  public abstract void updateArray();
  public abstract void preUpdateArray();
  public abstract void postUpdateArray();
  public void drawBuffer() {
    for(int i=0;i<minSize;i++) {
      c.g.pixels[i*range]=Tools.color(data.get(i)&0xc0);
      c.g.pixels[i*range+1]=Tools.color((data.get(i)<<2)&0xc0);
      c.g.pixels[i*range+2]=Tools.color((data.get(i)<<4)&0xc0);
      c.g.pixels[i*range+3]=Tools.color((data.get(i)<<6)&0xc0);
    }
  }
  public void updateSize() {
    minSize=Math.min(c.g.pixels.length/range,data.capacity());
  }
  @Override
  public void display() {
    int x=(int)(Math.ceil(c.point.pos.x/scale)*scale),
      y=(int)(Math.ceil(c.point.pos.y/scale)*scale),
      w=c.g.width*scale/range,
      h=c.g.height*scale/range;
    p.image(c.g,x,y,w,h);
    p.stroke(0xffDDF4C4);
    for(int i=1;i<w/(INT_SIZE*scale);i++) p.line(x+scale*INT_SIZE*i,y,x+scale*INT_SIZE*i,y+h);
    p.stroke(0xff389CA3);
    p.rect(x,y,w,h);
    if(focus&&mousePos>=0) drawBar();
  }
  public void drawBar() {
    p.stroke(0xffDDF4C4);
    float tx=c.point.pos.x+(mouseX/4*4)*scale;
    float ty=c.point.pos.y+mouseY*pixelSize;
    p.rect(tx,ty,
      scale*4,pixelSize);
    p.stroke(0xff006799);
    p.rect(
      c.point.pos.x+mouseX*scale,
      ty,
      scale,pixelSize);
    p.stroke(0xffFB6104);
    p.rect(
      c.point.pos.x+mouseX*scale+bytePos*pixelSize,
      ty,
      pixelSize,pixelSize);
    sb.append("mousePos  <[");
    sb.append(mouseX);
    sb.append(" ");
    sb.append(bytePos);
    sb.append("]->");
    sb.append(mouseX/4);
    sb.append(",");
    sb.append(mouseY);
    sb.append(">\n");
    sb.append("value at  <");
    sb.append(mousePos+(mouseX%4)/4f);
    sb.append(">:");
    sb.append("\nDecfloat  ");
    sb.append(floatFormatter.format(data.getFloat(mousePos*4)));
    sb.append("\nDecInt    ");
    sb.append(intFormatter.format(data.getInt(mousePos*4)));
    sb.append("\nHexInt    ");
    sb.append(Integer.toHexString(data.getInt(mousePos*4)).toUpperCase());
    sb.append("\nOctInt    ");
    sb.append(Integer.toOctalString(data.getInt(mousePos*4)).toUpperCase());
    sb.append("\nDecByte   ");
    sb.append((data.get(mousePos*4+mouseX%4)&0xff));
    sb.append("\nHexByte   ");
    sb.append(Integer.toHexString(data.get(mousePos*4+mouseX%4)&0xff).toUpperCase());
    sb.append("\nOctByte   ");
    sb.append(Integer.toOctalString(data.get(mousePos*4+mouseX%4)&0xff).toUpperCase());
    sb.append("\nBinByte   ");
    sb.append(Integer.toBinaryString(data.get(mousePos*4+mouseX%4)&0xff).toUpperCase());
    String string=sb.toString();
    float tw=p.textWidth(string);
    float th=p.g.textSize*10.5f;
    p.stroke(0x80006799);
    p.fill(0x80000000);
    p.rect(tx,ty-th,tw,th);
    p.fill(0xffffffff);
    p.noFill();
    p.text(string,tx,ty);
    sb.setLength(0);
  }
  @Override
  public void keyPressed(char key,int keyCode) {
    if(!focus) return;
    switch(key) {
      case ' ': {
        stop=!stop;
      }
        break;
      case 'w': {
        int i=mousePos*4+mouseX%4;
        data.put(i,(byte)(data.get(i)+(1<<((3-bytePos)*2))));
      }
        break;
      case 's': {
        int i=mousePos*4+mouseX%4;
        data.put(i,(byte)(data.get(i)-(1<<((3-bytePos)*2))));
      }
        break;
    }
    if(stop) {
      c.g.beginDraw();
      c.g.fill(0xc0);
      c.g.stroke(0x80);
      c.g.endDraw();
    }else {
      c.g.beginDraw();
      c.g.fill(0xff006799);
      c.g.stroke(0xff0099B6);
      c.g.endDraw();
    }
  }
}
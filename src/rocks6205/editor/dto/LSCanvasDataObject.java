package rocks6205.editor.dto;

import rocks6205.editor.model.adt.LSLength;

/**
 * 
 * @author Cheow Yeong Chi
 */
public class LSCanvasDataObject {

   private LSLength width;
   private LSLength height;
   private String title;

   public LSCanvasDataObject() {
      width = new LSLength(0);
      height = new LSLength(0);
      title = "";
   }

   public LSCanvasDataObject(LSLength w, LSLength h, String t) {
      width = w;
      height = h;
      title = t;
   }
   
   public LSLength getHeight() {
      return height;
   }

   public void setHeight(LSLength height) {
      this.height = height;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public LSLength getWidth() {
      return width;
   }

   public void setWidth(LSLength width) {
      this.width = width;
   }
}

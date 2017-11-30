package no.nials.selfieapp.selfieapp;


public class ImagesURL {

    String imageString;
  //  int birdListImage;

    public ImagesURL(String birdName)
    {
        //this.birdListImage=birdImage;
        this.imageString=birdName;
    }
    public String getbirdName()
    {
        return imageString;
    }
   /** public int getbirdImage()
    {
        return birdListImage;
    }**/
}

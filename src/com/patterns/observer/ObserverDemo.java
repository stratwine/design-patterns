package com.patterns.observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/*
  This is the Subject
 */
class GuitarNews extends Observable
{
  private List<String> news = new ArrayList<String>();

   public void addNewsEntry(String newsEntry)
   {
     news.add(newsEntry);
     this.setChanged(); //the notifier
     this.notifyObservers(newsEntry);
   }



}

class NewsEmailSender implements Observer
{

    @Override
    public void update(Observable observable, Object newsEntry) {
        //received the update
        System.out.println("Sending Email Notification: "+ newsEntry.toString());
    }
}

class NewsSmsSender implements Observer
{

    @Override
    public void update(Observable observable, Object newsEntry) {
        //received the update
        System.out.println("Sending SMS Notification: "+ newsEntry.toString());

    }
}


public class ObserverDemo {

    public static void main(String[] args)
    {
         GuitarNews guitarNews = new GuitarNews();
         guitarNews.addObserver(new NewsEmailSender());
         guitarNews.addObserver(new NewsSmsSender());
         guitarNews.addNewsEntry("Discount Sale for fender strats at ABC");

    }


}

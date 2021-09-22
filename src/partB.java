package src;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     * 
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     * 
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     * 
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     * 
     * @param updatedNumVisits int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class partB {

    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();

    public static void sort(Queue<SiteStats> sites){
        if(sites.isEmpty()){
            return;
        }
        SiteStats stat = sites.remove();
        sort(sites);


        int count = 0;
        int size  = sites.size();
        while(count<size && !sites.isEmpty() && sites.peek().getNumVisits() > stat.getNumVisits()){
            sites.add(sites.remove());
            count++;
        }


        sites.add(stat);

        while (count<size){
            sites.add(sites.remove());
            count++;
        }

    }

    // Main method to list top n visited sites
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        sort(sites);
        while(!sites.isEmpty() && n>0){
            System.out.println(sites.remove());
            n--;
        }
    }

    // Method to find the website in the queue and increment the visited count by 1, adding new node in case website is not found
    public static void updateCount(String url) {
        // WRITE CODE HERE
        int n = sites.size();
        int count = n;
        boolean found = false;
        while(count>0){
            if(sites.peek().getUrl()==url) {
                found = true;
                break;
            } else {
                sites.add(sites.remove());
                count--;
            }
        }

        if(found){
            SiteStats stat = sites.remove();
            stat.setNumVisits(stat.getNumVisits()+1);
            sites.add(stat);
            count--;
            while(count>0){
                sites.add(sites.remove());
                count--;
            }
        } else {
            SiteStats stat = new SiteStats(url, 1);
            sites.add(stat);
        }

    }

    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);

    }

}

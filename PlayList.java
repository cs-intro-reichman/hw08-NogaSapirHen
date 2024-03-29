/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (this.getSize() == this.getMaxSize())
        {
            return false;
        }
       else {
           this.tracks[this.getSize()] = track;
           this.size ++;
            return true;
        }
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        //// replace the following statement with your code
        String track = "";
        for (int i = 0; i < this.getSize(); i ++)
        {
            if (this.tracks[i] != null)
            {
                track += this.tracks[i];
                track += "\n" ;
            }
        }
        return track;
    }


    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        //// replace this comment with your code
        if (this.getSize() < 0 || this.getSize() == 0 )
        {
        return;
        }
        else {
        this.tracks[this.getSize()-1] = null;
        this.size --;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        //// replace the following statement with your code
        int sumDuration = 0;
        for (int i = 0; i < this.getSize(); i ++)
        {
            sumDuration += this.tracks[i].getDuration();
        }
        return sumDuration;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        //// replace the following statement with your code
        int count = 0;
        title = title.toLowerCase();
        for (int i = 0; i < this.getSize(); i ++)
        {
            for (int j = 0; j < this.tracks[i].getTitle().length(); j ++)
            { 
                if (this.tracks[i].getTitle().length() != title.length())
                break;
                String temp = this.tracks[i].getTitle().toLowerCase();
                if (temp.charAt(j) == title.charAt(j))
                {
                count ++;
                }
            }
            if (count == this.tracks[i].getTitle().length()){
                return i;
            }
            count = 0;
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        //// replace the following statement with your code
        if ( i < 0 || i > this.getMaxSize() || this.getMaxSize() == this.getSize() )
        {
            return false;
        }
       if (this.tracks[i]== null)
       {
        this.tracks [i] = track;
       }
       for (int j = this.getSize() ; j > i; j--)
       {
        this.tracks[j] = this.tracks[j-1];
       }
       this.tracks[i] = track;
       this.size ++;
        return true;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        //// replace this comment with your code
        if ( i < 0 || i > this.getMaxSize() )
        {
            return;
        }
        for (int j = i; j < this.getSize(); j ++)
        {
            this.tracks[j] = this.tracks [j+1];
        }
        this.size --;

    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        //// replace this comment with your code
        int count = 0;
        for (int i = 0; i < this.getSize(); i ++)
        {
            for (int j = 0; j < this.tracks[i].getTitle().length(); j ++)
            { 
                if (this.tracks[i].getTitle().length() != title.length())
                break;
                if (this.tracks[i].getTitle().charAt(j) == title.charAt(j))
                count ++;
            }
            if (count == this.tracks[i].getTitle().length()){
                remove(i);
                break;
            }
            count = 0;
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        //// replace this comment with your code
        for (int i = 0; i < this.getSize(); i ++)
        {
           this.tracks [i] = this.tracks [i+1];
        }
        this.size --;
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        //// replace this comment with your code
        if (this.getSize() + other.getSize() > this.getMaxSize())
        return;
        else {
            for (int i = 0; i < other.getSize(); i ++)
            {
                this.tracks[this.getSize()] = other.getTrack(i);
                this.size ++;
            }
        }

    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        //// replace the following statement with your code
        if (start < 0 || start > this.getSize() )
        return -1;
        int minIndex = start+1;
        for (int i = start; i < this.getSize()-1; i ++)
        {
             if (this.tracks[i].getDuration() < this.tracks[minIndex].getDuration()){
                minIndex = i;
             }
        }
        return minIndex;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code
        for (int i = 0; i < this.getSize()-1; i ++)
        {
            int min = i;
            for (int j = i+1; j < this.getSize(); j ++)
            {
                if (this.tracks[j].getDuration() < this.tracks[min].getDuration())
                {
                min = j; 
            }
        }
            Track temp = this.tracks[i];
        this.tracks[i] = this.tracks[min];
        this.tracks[min] = temp;
        }
    }
   
}

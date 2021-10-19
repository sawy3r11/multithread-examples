public class MinMaxMetrics {
    private boolean init;
    private volatile long min;
    private volatile long max;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        init=true;
        min=0;
        max=0;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        if(init){
            this.min = newSample;
            this.max = newSample;
            this.init = false;
            return;
        }

        if(min > newSample){
            min = newSample;
        }

        if(max<newSample){
            max = newSample;
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return max;
    }
}

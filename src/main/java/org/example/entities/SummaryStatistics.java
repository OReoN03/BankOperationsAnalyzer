package org.example.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SummaryStatistics {
    public double sum;
    public double max;
    public double min;
    public double average;

    public SummaryStatistics() {
    }

    public SummaryStatistics(double sum, double max, double min, double average) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAverage() {
        return average;
    }
}

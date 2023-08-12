package org.example.exporters;

import org.example.entities.SummaryStatistics;

public class HtmlExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summaryStatistics) {
        StringBuilder sb = new StringBuilder("<!doctype html>");
        sb.append("<html lang='en'>");
        sb.append("<head><title>Bank Transaction Report</title></head>");
        sb.append("<body>");
        sb.append("<ul>");
        sb.append("<li><strong>The sum is</strong>: ").append(summaryStatistics.getSum()).append("</li>");
        sb.append("<li><strong>The average is</strong>: ").append(summaryStatistics.getAverage()).append("</li>");
        sb.append("<li><strong>The max is</strong>: ").append(summaryStatistics.getMax()).append("</li>");
        sb.append("<li><strong>The min is</strong>: ").append(summaryStatistics.getMin()).append("</li>");
        sb.append("</ul>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}

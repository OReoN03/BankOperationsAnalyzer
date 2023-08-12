package org.example.exporters;

import org.example.entities.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}

package org.example.exporters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.SummaryStatistics;

public class JsonExporter implements Exporter {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(summaryStatistics);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

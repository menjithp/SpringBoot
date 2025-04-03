package org.example.models;
import lombok.Data;
import org.example.models.recipe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Data
public class recipes {
    private List<recipe> recipes;
    @JsonIgnoreProperties
    private int total;
    @JsonIgnoreProperties
    private int skip;
    @JsonIgnoreProperties
    private int limit;
}

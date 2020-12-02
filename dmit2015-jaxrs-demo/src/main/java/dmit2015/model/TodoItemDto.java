package dmit2015.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoItemDto implements Serializable {

    private Long id;

    @NotBlank(message = "Name field value is required.")
    private String name;

    private boolean complete;

}


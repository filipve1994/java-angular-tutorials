package be.fve.javaspringtutorials.modules.jpatutorials.jpaonetomany;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentToPostDto {

    @NotNull
    @Lob
    private String text;
}
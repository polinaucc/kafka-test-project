package ua.polina.person.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    @NotBlank(message = "First name should not be blank")
    @Size(max=20, message = "First name string length should not be more than 20 symbols")
    private String firstName;

    @NotBlank(message = "First name should not be blank")
    @Size(max=20, message = "Middle name string length should not be more than 20 symbols")
    private String middleName;

    @NotBlank(message = "Last name should not be blank")
    @Size(max=20, message = "Last name string length should not be more than 20 symbols")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[А-Я]{2}[0-9]{6}$", message = "Illegal passport!")
    private String passport;
}

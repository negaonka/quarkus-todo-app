package com.quarkus.todo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO implements Serializable {

	private static final long serialVersionUID = -9067592608684415105L;

	private Long id;

	@NotNull(message = "Name is mandatory")
	@NotBlank(message = "Empty name is not allowed")
	@Length(min = 3, max = 250, message = "Names shorter than 3 characters or longer than 250 are not allowed")
	private String name;

	@JsonbDateFormat("dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dateCreation;

	private String status;

}

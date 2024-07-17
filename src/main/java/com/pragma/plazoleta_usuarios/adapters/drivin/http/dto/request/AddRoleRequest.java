package com.pragma.plazoleta_usuarios.adapters.drivin.http.dto.request;


import com.pragma.plazoleta_usuarios.adapters.drivin.http.exceptions.DtoConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AddRoleRequest {

    @NotBlank(message = DtoConstants.FIELD_NAME_NULL_MESSAGE)
    private String name;
    private String description;


}

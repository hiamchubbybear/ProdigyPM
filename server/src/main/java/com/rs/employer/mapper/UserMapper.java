

package com.rs.employer.mapper;

import com.rs.employer.dto.UserDTO;
import com.rs.employer.model.sale.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}

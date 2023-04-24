package com.example.Unityville.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MapperTest {
    Mapper mapper;
//    ModelMapper modelMapper;
//    @Test
//    void convertToDTO() {
//        UserEntity userEntity = new UserEntity(1L, "namenameTest", "nametest", "mail@mail.com", new LinkedList<>());
//        UserDTO actualOutput = modelMapper.map(userEntity, UserDTO.class);
//        UserDTO expectedOutput = UserDTO.builder()
//                .email("mail@mail.com")
//                .name("nametest")
//                .username("namenameTest")
//                .build();
//        assertTrue(expectedOutput.getEmail().equals(actualOutput.getEmail())
//        && expectedOutput.getUsername().equals(actualOutput.getUsername())
//        && expectedOutput.getName().equals(actualOutput.getName()));
//    }

    @Test
    void convertToDTO() {
//        User user = new User(1L, "namenameTest", "nametest", "mail@mail.com", new LinkedList<>());
//        UserDTO actualOutput = mapper.convertToDTO(user);
////        UserDTO actualOutput = modelMapper.map(userEntity, UserDTO.class);
//        UserDTO expectedOutput = UserDTO.builder()
//                .email("mail@mail.com")
//                .name("nametest")
//                .username("namenameTest")
//                .build();
//        assertTrue(expectedOutput.getEmail().equals(actualOutput.getEmail())
//                && expectedOutput.getUsername().equals(actualOutput.getUsername())
//                && expectedOutput.getName().equals(actualOutput.getName()));
    }
}
package com.example.lifesync.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO {
    private Integer id;


    private String title;
    private String description;

}

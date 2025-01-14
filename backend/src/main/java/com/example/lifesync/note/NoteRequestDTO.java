package com.example.lifesync.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequestDTO {

    private Integer id;
    private String title;
    private String content;

}
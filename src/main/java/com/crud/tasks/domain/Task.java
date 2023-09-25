package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="tasks")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String title;

    @Column(name="description")
    private String content;
}

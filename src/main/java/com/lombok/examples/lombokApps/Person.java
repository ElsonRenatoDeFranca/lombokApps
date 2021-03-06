package com.lombok.examples.lombokApps;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Person {
    private String id;
    private String name;
    private Integer age;
    private String address;
}

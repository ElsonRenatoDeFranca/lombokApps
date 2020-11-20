package com.lombok.examples.lombokApps;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"language", "salary"})
@Builder
public class Developer {
    private String name;
    private String language;
    private int salary;
}

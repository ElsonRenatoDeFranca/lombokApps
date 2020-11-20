package com.lombok.examples.lombokApps;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor()
public class Customer {

    private String id;

    @NonNull private String name;
    @NonNull private Integer age;
    @NonNull private String address;
}

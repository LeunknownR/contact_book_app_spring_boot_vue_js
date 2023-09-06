package net.personalprojects.contactbook.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseAPI<D> {
    private String status;
    private D data;
}

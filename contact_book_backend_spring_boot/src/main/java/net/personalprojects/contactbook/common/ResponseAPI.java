package net.personalprojects.contactbook.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ResponseAPI<D> {
    private String status;
    private D data;
}

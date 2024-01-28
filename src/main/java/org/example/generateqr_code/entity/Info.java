package org.example.generateqr_code.entity;

import lombok.*;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private  long id= new Random().nextLong(-99999L,99999L);
    private  String  name;

    @Override
    public String toString() {
        return "Info" + '\n' + "name: " + name + '\n'+ "id: " + id ;
    }
}

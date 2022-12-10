package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    @Override
    public String toString() {
        return "Employee [firstName=" + firstName +
                ", lastName=" + lastName + "]";
    }
}

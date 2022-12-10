package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    private double salary;

    @Override
    public String toString() {
        return "Salary [salary=" + salary + "]";
    }
}

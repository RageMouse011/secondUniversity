package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String street;
    private String houseAddress;

    @Override
    public String toString() {
        return "Address [country=" + country +
                ", city=" + city +
                ", street=" + street +
                ", homeAddress=" + houseAddress + "]";
    }
}

package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departmens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "maxEmployeeNumber")

public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_id_gen"
    )
    @SequenceGenerator(
            name = "department_id_gen",
            sequenceName = "department_seq_name",
            allocationSize = 1
    )
    private Long id;

    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(max = 10, message = "There cannot be more than 10 employees in a department.")
    private List<Employee> maxEmployeeNumber = new ArrayList<>();

    public Department(String name, List<Employee> maxEmployeeNumber) {
        this.name = name;
        this.maxEmployeeNumber = maxEmployeeNumber;
    }

    public Department(String name) {
        this.name = name;
    }
}

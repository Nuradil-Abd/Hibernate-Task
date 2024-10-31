package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "department")

public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_id_gen"
    )
    @SequenceGenerator(
            name = "employee_id_gen",
            sequenceName = "employee_seq_name",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private int age;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(String firstName,double salary) {

        this.firstName = firstName;
        this.salary = salary;
    }
}

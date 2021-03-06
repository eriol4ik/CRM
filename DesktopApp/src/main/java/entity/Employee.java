package entity;

import enum_types.Position;
import enum_types.Sex;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "employees")
@Entity
public class Employee implements Serializable {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login")
    private User user;

    public Employee() {}

    public Employee(String name, String surname, Integer age, Sex sex, Position position, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.position = position;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        if (this.getUser() != null) {
            return "+";
        }
        return "-";
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", surname='" + surname + "'" +
                ", age=" + age +
                ", sex=" + sex +
                ", position=" + position +
                "}";
    }

    public String shortInfo() {
        return name + " " + surname;
    }
}

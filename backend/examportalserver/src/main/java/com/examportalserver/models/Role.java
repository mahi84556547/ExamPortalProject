package com.examportalserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles",schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserRole> userRoleSet=new HashSet<>();
}

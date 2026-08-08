package com.vms.vendorcontractmanagement.entity; import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="roles") @Getter @Setter @NoArgsConstructor public class Role { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="role_id") Long id; @Column(name="role_name") String name; }

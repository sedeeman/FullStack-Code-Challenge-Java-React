package com.sedeeman.citzimbproductcatalogservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedeeman.citzimbproductcatalogservice.validator.MethodologyConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(value = 9999999999L)
    @Column(name = "product_id")
    private Long productId;

    @NonNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "product_name")
    private String productName;

    @NonNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "product_owner_name")
    private String productOwnerName;

    @NonNull
    @ElementCollection
    @Size(min = 1, max = 5)
    @Column(name = "developer_name")
    private List<String> developers;

    @NonNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "scrum_master_name")
    private String scrumMasterName;

    @NonNull
    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    @NonNull
    @Column(name = "methodology")
    @MethodologyConstraint
    private String methodology;

}

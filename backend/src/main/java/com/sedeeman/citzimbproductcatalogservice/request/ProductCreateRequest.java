package com.sedeeman.citzimbproductcatalogservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedeeman.citzimbproductcatalogservice.validator.MethodologyConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @Max(value = 9999999999L)
    private Long productId;

    @NonNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String productName;

    @NonNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String productOwnerName;

    @NotNull
    @Size(min = 1, max = 5)
    private List<String> developers;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String scrumMasterName;

    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    @NotNull
    @MethodologyConstraint
    private String methodology;

}
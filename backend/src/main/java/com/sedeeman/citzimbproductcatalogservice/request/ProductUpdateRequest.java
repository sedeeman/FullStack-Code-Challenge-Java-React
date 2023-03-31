package com.sedeeman.citzimbproductcatalogservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    @Max(value = 9999999999L)
    private Long productId;

    @Size(min = 1, max = 100)
    private String productName;

    @Size(min = 1, max = 100)
    private String productOwnerName;

    @Size(min = 1, max = 5)
    private List<String> developers;

    @Size(min = 1, max = 100)
    private String scrumMasterName;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    private String methodology;

}

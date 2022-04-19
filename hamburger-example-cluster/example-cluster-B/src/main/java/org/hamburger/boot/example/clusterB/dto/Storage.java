package org.hamburger.boot.example.clusterB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Integer productId;

    private Long count;

}

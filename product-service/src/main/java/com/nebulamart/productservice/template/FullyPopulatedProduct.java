package com.nebulamart.productservice.template;

import com.nebulamart.productservice.entity.Contract;
import com.nebulamart.productservice.entity.Courier;
import com.nebulamart.productservice.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FullyPopulatedProduct {

    private String id;
    private String name;
    private String description;
    private String brand;
    private List<String> imageUrls;
    private String category;

    private int stock;
    private float basePrice;
    private float discount;
    private float rating;
    private int ratingCount;
    private Seller seller;
    private Courier courier;
    private Contract contract;
    private String status;
    private String createdDate;
    private String lastUpdatedDate;

}
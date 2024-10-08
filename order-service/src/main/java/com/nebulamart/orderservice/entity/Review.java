package com.nebulamart.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class Review {

    private String id;
    private String productId;
    private String sellerId;
    private String courierId;
    private String orderId;
    private String customerId;
    private String productReview;
    private String courierReview;
    private int productRating;
    private int courierRating;
    private String createdAt;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "product-index" })
    public String getProductId() {
        return productId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "seller-index" })
    public String getSellerId() {
        return sellerId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "courier-index" })
    public String getCourierId() {
        return courierId;
    }

}

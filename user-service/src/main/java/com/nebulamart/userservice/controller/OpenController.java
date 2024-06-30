package com.nebulamart.userservice.controller;

import com.nebulamart.userservice.entity.Courier;
import com.nebulamart.userservice.entity.Seller;
import com.nebulamart.userservice.service.CourierService;
import com.nebulamart.userservice.service.ImageUploadService;
import com.nebulamart.userservice.service.SellerService;
import com.nebulamart.userservice.template.GetUrlResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/open")
public class OpenController {
    private final ImageUploadService imageUploadService;
    private final SellerService sellerService;
    private final CourierService courierService;

    @Autowired
    public OpenController(ImageUploadService imageUploadService, SellerService sellerService, CourierService courierService) {
        this.imageUploadService = imageUploadService;
        this.sellerService = sellerService;
        this.courierService = courierService;
    }

    @GetMapping("/profile-image-upload-url")
    public ResponseEntity<GetUrlResponse> getPreSignedUrl(@PathParam("extension") String extension) {
        if (extension == null || extension.isEmpty()) {
            return ResponseEntity.status(400).body(new GetUrlResponse(null, "Missing or invalid extension"));
        }
        String preSignedUrl = imageUploadService.getPreSignedUrl("users" + "/" + UUID.randomUUID().toString() + "." + extension);
        if (preSignedUrl == null) {
            return ResponseEntity.status(400).body(new GetUrlResponse(null, "Failed to get pre-signed URL"));
        }
        return ResponseEntity.ok(new GetUrlResponse(preSignedUrl, "Pre-signed URL generated successfully"));
    }

    @RequestMapping("/sellers/{id}")
    public Seller getSellerDetails(@PathVariable("id") String id) {
        if (id == null) {
            return null;
        }
        return sellerService.getSeller(id);
    }

    @RequestMapping("/couriers/{id}")
    public Courier getCourierDetails(@PathVariable("id") String id) {
        if (id == null) {
            return null;
        }
        return courierService.getCourier(id);
    }

}

package com.esotiq.esotiq.controller;

import com.esotiq.esotiq.model.Product;
import com.esotiq.esotiq.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/esotiq")
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("/addproduct")
    public String addProduct() {
        return "Działa";
    }


    @GetMapping("/getAllProduct")
    public String getAllProduct(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList",productList);
        return "products";
    }

}

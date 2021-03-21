package cz.upce.eshop.controller;

import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import cz.upce.eshop.service.FileService;
import dto.AddOrEditProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileService fileService;

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "error";
    }

    @GetMapping("/")
    public String showAllProducts(Model model){
        model.addAttribute("productList", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/product-detail/{id}")
    public String showProductDetail(@PathVariable(required = false) Long id, Model model){
        model.addAttribute("product", productRepository.findById(id).get());
        return "product-detail";
    }

    @GetMapping(value = {"/product-form", "/product-form/{id}"})
    public String showProductForm(@PathVariable(required = false) Long id, Model model) {
        if(id != null) {
            Product byId = productRepository.findById(id).orElse(new Product());

            AddOrEditProductDto dto = new AddOrEditProductDto();
            dto.setId(byId.getId());
            dto.setName(byId.getName());
            dto.setDescription(byId.getDescription());

            model.addAttribute("product", dto);
        } else {
            model.addAttribute("product", new AddOrEditProductDto());
        }
        return "product-form";
    }

    @PostMapping("/product-form-process")
    public String addProductProcess(AddOrEditProductDto addOrEditProductDto, Model model) {

        Product product = new Product();
        product.setName(addOrEditProductDto.getName());
        product.setPrice(addOrEditProductDto.getPrice());
        product.setCount(addOrEditProductDto.getCount());
        product.setId(addOrEditProductDto.getId());
        product.setDescription(addOrEditProductDto.getDescription());
        product.setPathToImage(fileService.upload(addOrEditProductDto.getImage()));
        productRepository.save(product);
        return "redirect:/";
    }
}

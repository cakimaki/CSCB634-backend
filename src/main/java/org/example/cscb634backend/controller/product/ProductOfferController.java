package org.example.cscb634backend.controller.product;

import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.service.product.ProductOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user/product-offer")
public class ProductOfferController {
	private final ProductOfferService productOfferService;
	
	public ProductOfferController(ProductOfferService productOfferService) {
		this.productOfferService = productOfferService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createProductOffer(@RequestBody ProductOfferDto productOfferDto,@AuthenticationPrincipal Jwt jwt){
		try {
			ProductOfferDto returnDto = productOfferService.createProductOffer(productOfferDto,jwt.getClaim("sub"));
			return ResponseEntity.ok("Offer has been created.");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@GetMapping("/valid")
	public ResponseEntity<List<ProductOfferDto>> getAllValidOffers(){
		try{
			return ResponseEntity.ok(productOfferService.fetchAllValidOffers());
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Couldn't get all the valid offers");
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
		try {
			productOfferService.deleteOffer(id);
			return ResponseEntity.ok("It has been deleted.");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

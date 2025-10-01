package com.dicipline.SystemUser.Resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dicipline.SystemUser.Entities.User;
import com.dicipline.SystemUser.Services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users",description = "Endpoints to manager users")
public class UserResource {

	private final UserService repository;
	
	public UserResource(UserService repository) {
		this.repository = repository;
	}

	@GetMapping
	@Operation(summary = "get all users	" )
	@ApiResponses({
	  @ApiResponse(responseCode = "200",description = ""),
	  @ApiResponse(responseCode = "500",description = "Internal server error")
	})	
	public ResponseEntity<List<User>> findAll() {
		List<User> listUser = repository.findAll();
		return ResponseEntity.ok().body(listUser);
	}

	@GetMapping("/{id}")
	@Operation(summary = "get user by ID")
	@ApiResponses({
	  @ApiResponse(responseCode = "200",description = " user found with success"),
	  @ApiResponse(responseCode = "500",description = "Internal server error"),
      @ApiResponse(responseCode = "404", description = "user not found")
	  
	})
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = repository.findById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	@Operation(summary = "create user")
	@ApiResponses({
	  @ApiResponse(responseCode = "201",description = "created user with success"),
	  @ApiResponse(responseCode = "500",description = "Internal server error")
	})
	public ResponseEntity<User> insert(@RequestBody User user) {
		user = repository.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "delete user by ID")
	@ApiResponses({
	  @ApiResponse(responseCode = "204",description = "deleted user with success,without body"),
	  @ApiResponse(responseCode = "500",description = "Internal server error"),
      @ApiResponse(responseCode = "404", description = "user not found")
	})
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repository.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "update user by ID")
	@ApiResponses({
	  @ApiResponse(responseCode = "200",description = "updated user with success"),
	  @ApiResponse(responseCode = "500",description = "Internal server error"),
      @ApiResponse(responseCode = "404", description = "user not found")
	})
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		user = repository.update(id, user);
		return ResponseEntity.ok().body(user);
	}
}
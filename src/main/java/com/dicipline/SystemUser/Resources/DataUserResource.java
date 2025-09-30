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

import com.dicipline.SystemUser.Entities.DataUser;
import com.dicipline.SystemUser.Services.DataUserService;

@RestController
@RequestMapping("/datas")
public class DataUserResource {

	private final DataUserService repository;
	
	public DataUserResource(DataUserService repository) {
		this.repository = repository;
	}

	@GetMapping
	private ResponseEntity<List<DataUser>> findAll() {
		List<DataUser> dUsers = repository.findAll();
		return ResponseEntity.ok().body(dUsers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DataUser> findById(@PathVariable Long id) {
		DataUser du = repository.findById(id);
		return ResponseEntity.ok(du);
	}

	@PostMapping
	public ResponseEntity<DataUser> insert(@RequestBody DataUser dataU) {
		dataU = repository.insert(dataU);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dataU.getId()).toUri();
		return ResponseEntity.created(uri).body(dataU);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.repository.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<DataUser> update(@PathVariable Long id, @RequestBody DataUser du) {
		du = this.repository.update(id, du);
		return ResponseEntity.ok().body(du);
	}
}

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/datas")
@Tag(name = "DataUser", description = "Endpoints to manage data users")
public class DataUserResource {

    private final DataUserService repository;
	
    public DataUserResource(DataUserService repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "get all data users")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "list of data users returned with success"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<DataUser>> findAll() {
        List<DataUser> dUsers = repository.findAll();
        return ResponseEntity.ok().body(dUsers);
    }
	
    @GetMapping("/{id}")
    @Operation(summary = "get data user by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "data user found with success"),
        @ApiResponse(responseCode = "404", description = "data user not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DataUser> findById(@PathVariable Long id) {
        DataUser du = repository.findById(id);
        return ResponseEntity.ok(du);
    }

    @PostMapping
    @Operation(summary = "create data user")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "created data user with success"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DataUser> insert(@RequestBody DataUser dataU) {
        dataU = repository.insert(dataU);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                             .buildAndExpand(dataU.getId()).toUri();
        return ResponseEntity.created(uri).body(dataU);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete data user by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "deleted data user with success, without body"),
        @ApiResponse(responseCode = "404", description = "data user not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.repository.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update data user by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "updated data user with success"),
        @ApiResponse(responseCode = "404", description = "data user not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DataUser> update(@PathVariable Long id, @RequestBody DataUser du) {
        du = this.repository.update(id, du);
        return ResponseEntity.ok().body(du);
    }
}

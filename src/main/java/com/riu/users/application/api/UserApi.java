package com.riu.users.application.api;

import com.riu.users.application.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
public interface UserApi {

    @GetMapping("")
    @Operation(summary = "Get Users", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))})
    List<UserDto> getAllUsers();

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get User by username", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Username has invalid format", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    ResponseEntity<UserDto> getUserByUsername(
            @Parameter(description = "Username of the user to be obtained. Cannot be empty.", required = true)
            @PathVariable String username);

    @PostMapping("")
    @Operation(summary = "Create a user", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "booking created", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "User Example", summary = "user example", value = UserDto.EXAMPLE)
            }, schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "booking payload is not correct", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<UserDto> saveUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "booking resource to add. Cannot null or empty.", required = true, content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "Booking Example", summary = "booking example", value = UserDto.EXAMPLE)}, schema = @Schema(implementation = UserDto.class)))
            @RequestBody @Valid
            UserDto userDTO);
}


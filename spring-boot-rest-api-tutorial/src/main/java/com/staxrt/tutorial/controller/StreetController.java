/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.District;
import com.staxrt.tutorial.model.Street;
import com.staxrt.tutorial.repository.DistrictRepository;
import com.staxrt.tutorial.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Street controller.
 *
 * @author QuyetNK
 */
@RestController
@RequestMapping("/api/v1")
public class StreetController {

  @Autowired
  private StreetRepository streetRepository;
  private DistrictRepository districtRepository;

  /**
   * Get all streets list.
   *
   * @return the list
   */
  @GetMapping("/streets")
  public List<Street> getAllStreets(@RequestParam("name") String name) {
    List<Street> streets = streetRepository.findAll();
    if(name.isEmpty()) {
      return streets;
    } else {
      return (List<Street>) streets.stream()
              .filter(item -> item.getStreetName().contains(name))
              .findAny()
              .orElse(null);
    }
  }

  /**
   * Gets streets by id.
   *
   * @param streetId the street id
   * @return the streets by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/streets/{id}")
  public ResponseEntity<Street> getStreetsById(@PathVariable(value = "id") Long streetId)
      throws ResourceNotFoundException {
    Street street =
        streetRepository
            .findById(streetId)
            .orElseThrow(() -> new ResourceNotFoundException("Street not found on : " + streetId));
    return ResponseEntity.ok().body(street);
  }

  /**
   * Create street street.
   *
   * @param street the street
   * @return the streets
   */
  @PostMapping("/streets")
  public Street createStreet(@Valid @RequestBody Street street) {
    return streetRepository.save(street);
  }

  /**
   * Update street response entity.
   *
   * @param streetId the street id
   * @param streetDetails the street details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/streets/{id}")
  public ResponseEntity<Street> updateStreet(
      @PathVariable(value = "id") Long streetId, @Valid @RequestBody Street streetDetails)
      throws ResourceNotFoundException {

    Street street =
        streetRepository
            .findById(streetId)
            .orElseThrow(() -> new ResourceNotFoundException("Street not found on: " + streetId));

    street.setStreetName(streetDetails.getStreetName());
    street.setEstablishDate(streetDetails.getEstablishDate());
    street.setDescription(streetDetails.getDescription());
    street.setStatus(streetDetails.getStatus());
    street.setUpdatedAt(new Date());
    street.setDistrict(streetDetails.getDistrict());

    final Street updatedStreet = streetRepository.save(street);
    return ResponseEntity.ok(updatedStreet);
  }

  /**
   * Delete street map.
   *
   * @param streetId the street id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/street/{id}")
  public Map<String, Boolean> deleteStreet(@PathVariable(value = "id") Long streetId) throws Exception {
    Street street =
        streetRepository
            .findById(streetId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + streetId));

    streetRepository.delete(street);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}

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
import com.staxrt.tutorial.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type District controller.
 *
 * @author QuyetNK
 */
@RestController
@RequestMapping("/api/v1")
public class DistrictController {

  @Autowired
  private DistrictRepository districtRepository;

  /**
   * Get all districts list.
   *
   * @return the list
   */
  @GetMapping("/districts")
  public List<District> getAllDistricts() {
    return districtRepository.findAll();
  }

  /**
   * Gets districts by id.
   *
   * @param districtId the district id
   * @return the districts by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/districts/{id}")
  public ResponseEntity<District> getDistrictsById(@PathVariable(value = "id") Long districtId)
      throws ResourceNotFoundException {
    District district =
        districtRepository
            .findById(districtId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + districtId));
    return ResponseEntity.ok().body(district);
  }

  /**
   * Create district district.
   *
   * @param district the district
   * @return the district
   */
  @PostMapping("/districts")
  public District createDistrict(@Valid @RequestBody District district) {
    return districtRepository.save(district);
  }

  /**
   * Update district response entity.
   *
   * @param districtId the district id
   * @param districtDetails the district details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/districts/{id}")
  public ResponseEntity<District> updateDistrict(
      @PathVariable(value = "id") Long districtId, @Valid @RequestBody District districtDetails)
      throws ResourceNotFoundException {

    District district =
        districtRepository
            .findById(districtId)
            .orElseThrow(() -> new ResourceNotFoundException("District not found on :: " + districtId));

    district.setDistrictName(districtDetails.getDistrictName());
    district.setUpdatedAt(new Date());
    final District updatedDistrict = districtRepository.save(district);
    return ResponseEntity.ok(updatedDistrict);
  }

  /**
   * Delete district map.
   *
   * @param districtId the district id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/district/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long districtId) throws Exception {
    District district =
        districtRepository
            .findById(districtId)
            .orElseThrow(() -> new ResourceNotFoundException("District not found on : " + districtId));

    districtRepository.delete(district);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}

package com.infystore.ekart.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infystore.ekart.dto.RestErrorResponse;

@Controller
public class DefaultController {

  @RequestMapping
  public ResponseEntity<RestErrorResponse> handleUnmappedRequest(final HttpServletRequest request) {
    return ResponseEntity.status(NOT_FOUND).body(RestErrorResponse.of(NOT_FOUND));
  }

}
